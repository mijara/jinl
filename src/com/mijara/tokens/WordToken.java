package com.mijara.tokens;

/**
 * The abstract word token should be used for string based tokens,
 * such as ids, strings, and names.
 *
 * @author mijara
 *
 * TODO: should this class extend ValueToken?
 */
public abstract class WordToken extends Token
{
    private String value;

    /**
     * @param tag the id to assign to the token
     * @param value the value to store
     */
    public WordToken(int tag, String value)
    {
        super(tag);

        this.value = value;
    }

    /**
     * @return the token string
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param obj the other object
     * @return true if the tokens are of the same type and have the same string value.
     */
    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj) && ((WordToken) obj).value.equals(value);
    }
}
