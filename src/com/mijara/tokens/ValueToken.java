package com.mijara.tokens;

/**
 * Token that can store some basic value, such as string, int, float.
 *
 * @param <T> the specific type of the token
 *
 * @author mijara
 */
public class ValueToken <T> extends Token
{
    private T value;

    /**
     * Constructs a value token.
     *
     * @param tag the tag of the token.
     * @param value the value to store.
     */
    public ValueToken(int tag, T value)
    {
        super(tag);

        this.value = value;
    }

    /**
     * @return the stored value.
     */
    public T getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj) && ((ValueToken) obj).value.equals(value);
    }
}
