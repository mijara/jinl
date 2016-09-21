package com.mijara.tokens;

public class DoubleToken extends ValueToken<Double>
{
    /**
     * Constructs a double token.
     *
     * @param value the double value.
     */
    public DoubleToken(double value)
    {
        super(Token.DOUBLE, value);
    }
}
