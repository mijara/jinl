package com.mijara.lexer;

import com.mijara.tokens.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * Reads the input from STDIN as a stream of characters.
 *
 * This is the recommended implementation for production.
 *
 * A lexer should never be used twice.
 *
 * @author mijara
 */
public class StreamLexer implements Lexer
{
    /**
     * Last read character.
     */
    private char peek = ' ';

    /**
     * Current read line.
     */
    private int line = 0;

    /**
     * Storage of words that can be emitted without creating new objects.
     */
    private HashMap<String, Token> reserved = new HashMap<>();

    /**
     * Creates a new lexer.
     */
    public StreamLexer()
    {
        reserved.put("End", Token.endToken);
        reserved.put("var", Token.varToken);
        reserved.put("float", new IdToken("float"));
    }

    /**
     * @return the next token read.
     */
    private Token process()
    {
        skipWhitespace();

        if (peek == '\0') {
            return Token.eofToken;
        }

        // check for specific symbols.
        switch (peek) {
            case '@':
                return lexFunctionName();
            case '=':
                if (next('=')) {
                    return Token.equalsToken;
                } else {
                    return new Token('=');
                }
            case '<':
                if (next('=')) {
                    return Token.leqToken;
                } else {
                    return new Token('<');
                }
            case '>':
                if (next('=')) {
                    return Token.geqToken;
                } else {
                    return new Token('>');
                }
        }

        if (Character.isLetter(peek)) {
            // [a-zA-Z][a-zA-Z0-9]*
            // this could lead to an ID, or some keyword.
            String value = literal();

            // check against the reserved keywords, and add if it's not there.
            if (!reserved.containsKey(value)) {
                reserved.put(value, new IdToken(value));
            }

            return reserved.get(value);
        }

        if (Character.isDigit(peek)) {
            // [0-9]+ -> Integer
            // [0-9]+\.[0-9]+f -> Float
            // [0-9]+\.[0-9]+ -> Double

            StringBuilder builder = new StringBuilder();

            do {
                builder.append(peek);
            } while (Character.isDigit(next()));

            if (peek != '.') {
                return new IntegerToken(Integer.parseInt(builder.toString()));
            }

            do {
                builder.append(peek);
            } while (Character.isDigit(next()));

            if (peek == 'f') {
                return new FloatToken(Float.parseFloat(builder.toString()));
            }

            return new DoubleToken(Double.parseDouble(builder.toString()));
        }

        // this is a single character symbol, return it as is.
        Token singleChar = new Token(peek);
        peek = ' ';
        return singleChar;
    }

    /**
     * Reads a literal string (consisting of letters and digits).
     *
     * @return the read string.
     */
    private String literal()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(peek);

        while (Character.isLetterOrDigit(next())) {
            builder.append(peek);
        }

        return builder.toString();
    }

    /**
     * @return the read function name token.
     */
    private FunctionNameToken lexFunctionName()
    {
        StringBuilder buffer = new StringBuilder();

        if (Character.isUpperCase(next())) {
            buffer.append(peek);
        } else {
            throw new LexerError("Expected an uppercase letter after @, at line " + line);
        }

        while (Character.isLetterOrDigit(next())) {
            buffer.append(peek);
        }

        return new FunctionNameToken(buffer.toString());
    }

    /**
     * skips any whitespace while accumulating the line count.
     */
    private void skipWhitespace()
    {
        for (;;next()) {
            switch (peek) {
                case ' ':
                case '\t':
                    continue;
                case '\n':
                    line++;
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * Reads the next character or sets it to '\0' for when the input is over.
     *
     * @return the next character read.
     */
    private char next()
    {
        try {
            int val = System.in.read();

            if (val == -1) {
                peek = 0;
                return peek;
            }

            peek = (char) val;
        } catch (IOException e) {
            peek = '\0';
        }

        return peek;
    }

    /**
     * Matches the next peek with the argument given, if they are equal, then
     * return true and set the peek to ' ' (whitespace).
     *
     * @param c the character to match against.
     * @return true if they match.
     */
    private boolean next(char c)
    {
        next();

        if (peek != c) {
            return false;
        }

        peek = ' ';
        return true;
    }

    @Override
    public Token getNext()
    {
        return process();
    }

    @Override
    public char getPeek()
    {
        return peek;
    }
}
