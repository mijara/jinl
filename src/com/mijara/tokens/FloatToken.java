package com.mijara.tokens;

public class FloatToken extends ValueToken<Float>
{
    /**
     * Constructs a float token.
     *
     * @param value the float value.
     */
    public FloatToken(float value)
    {
        super(Token.FLOAT, value);
    }
}
