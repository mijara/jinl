package com.mijara.tokens;

public class IntegerToken extends ValueToken<Integer>
{
    /**
     * Constructs an integer token.
     *
     * @param value the integer value to store.
     */
    public IntegerToken(int value)
    {
        super(Token.INTEGER, value);
    }
}
