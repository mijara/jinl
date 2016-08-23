package com.mijara.lexer;

import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.Token;

import java.io.IOException;

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

    private Token process()
    {
        skipWhitespace();

        if (peek == '\0') {
            return Token.eofToken;
        }

        switch (peek) {
            case '@':
                return lexFunctionName();
        }

        return null;
    }

    private FunctionNameToken lexFunctionName()
    {
        StringBuffer buffer = new StringBuffer();

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
}
