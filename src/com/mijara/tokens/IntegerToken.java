package com.mijara.tokens;

/**
 * Represents an Integer as in \d+.
 *
 * @author mijara
 */
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

    @Override
    public String toString()
    {
        return "[Integer] " + getValue();
    }
}
