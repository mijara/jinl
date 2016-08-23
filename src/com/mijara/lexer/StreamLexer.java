package com.mijara.lexer;

import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.Token;

import java.io.IOException;
import java.util.HashMap;

/**
 * Reads the input from STDIN as a stream of characters.
 *
 * This is the recommended implementation for production.
 *
 * @author mijara
 */
public class StreamLexer implements Lexer
{
    private char peek = '\0';
    private int line = 0;
    private HashMap<String, Token> reserved = new HashMap<>();

    public StreamLexer()
    {
        reserved.put("End", Token.endToken);
        reserved.put("var", Token.varToken);
    }

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
                if (next() == '=') {
                    return Token.equalsToken;
                } else {
                    return new Token('=');
                }
            case '<':
                if (next() == '=') {
                    return Token.leqToken;
                } else {
                    return new Token('<');
                }
            case '>':
                if (next() == '=') {
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

        // this is a single character symbol, return it as is.
        return new Token(peek);
    }

    private String literal()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(peek);

        while (Character.isLetterOrDigit(next())) {
            builder.append(peek);
        }

        return builder.toString();
    }

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

    private void skipWhitespace()
    {
        for (;;) {
            switch (next()) {
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
