package com.mijara.parser;

import com.mijara.tokens.Token;

public class TokenNotExpectedException extends RuntimeException
{
    private TokenNotExpectedException(String current, String expected)
    {
        super("Expected " + expected + " but got " + current);
    }

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
