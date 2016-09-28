package com.mijara.tokens;

/**
 * Represent a double value in the form: \d+\.\d+
 *
 * @author mijara
 */
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
