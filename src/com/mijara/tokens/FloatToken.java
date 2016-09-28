package com.mijara.tokens;

/**
 * Represent a double value in the form: \d+\.\d+f
 *
 * @author mijara
 */
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
