package com.mijara.tokens;

public class IntegerToken extends ValueToken<Integer>
{
    /**
     * Constructs an integerVal token.
     *
     * @param value the integerVal value to store.
     */
    public IntegerToken(int value)
    {
        super(Token.INTEGER, value);
    }
}
