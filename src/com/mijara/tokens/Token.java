package com.mijara.tokens;

public class Token
{
    // Numeric.
    public static final int INTEGER = 256;
    public static final int FLOAT = 257;
    public static final int DOUBLE = 258;

    // Special naming.
    public static final int FUNCTION_NAME = 259;
    public static final int COMPLEXTYPE_NAME = 260;

    // Keywords.
    public static final int KW_END = 261;

    public static final int ID = 262;

    private int tag;

    public Token(int tag)
    {
        this.tag = tag;
    }

    public int getTag()
    {
        return tag;
    }

    public boolean is(int tag)
    {
        return this.tag == tag;
    }
}
