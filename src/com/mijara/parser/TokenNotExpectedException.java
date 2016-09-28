package com.mijara.parser;

import com.mijara.tokens.Token;

/**
 * Thrown when a token was not expected in the stream.
 */
public class TokenNotExpectedException extends RuntimeException
{
    /**
     * @param current the read token.
     * @param expected the expected token.
     */
    private TokenNotExpectedException(String current, String expected)
    {
        super("Expected " + expected + " but got " + current);
    }

    /**
     * Builds a {@link TokenNotExpectedException}.
     *
     * @param current the current token read.
     * @param expected the code of the expected token.
     * @return the created exception.
     */
    public static TokenNotExpectedException build(Token current, int expected)
    {
        String expectedRef = String.valueOf(expected);
        String currentRef = String.valueOf(current.getTag());

        if (expected < 256) {
            expectedRef = String.valueOf((char) expected);
        }

        if (current.getTag() < 256) {
            currentRef = String.valueOf((char) current.getTag());
        }

        return new TokenNotExpectedException(currentRef, expectedRef);
    }
}
