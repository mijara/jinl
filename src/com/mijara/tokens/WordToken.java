package com.mijara.tokens;

public abstract class WordToken extends Token
{
    String value;

    public WordToken(int tag, String value)
    {
        super(tag);

        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
