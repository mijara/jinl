package com.mijara.tokens;

/**
 * A token is the minimal piece of data that the parser needs to understand
 * the source code.
 *
 * @author mijara
 */
public class Token
{
    // Numeric.
    public static final int INTEGER = 256;
    public static final int FLOAT = 257;
    public static final int DOUBLE = 258;

    // Special naming.
    public static final int FUNCTION_NAME = 259;
    public static final int STRUCT_NAME = 260;

    // Keywords.
    public static final int END = 261;
    public static final int ID = 262;
    public static final int VAR = 263;

    public static final Token endToken = new Token(END);
    public static final Token varToken = new Token(VAR);

    private int tag;

    /**
     * Constructs a default token, used primarily for static tokens.
     *
     * @param tag the tag of the token.
     */
    public Token(int tag)
    {
        this.tag = tag;
    }

    /**
     * @return the token tag
     */
    public int getTag()
    {
        return tag;
    }

    /**
     * @return this token as an ID.
     */
    public IdToken toId()
    {
        return (IdToken) this;
    }

    /**
     * @return this token as an ID.
     */
    public FunctionNameToken toFunctionName()
    {
        return (FunctionNameToken) this;
    }

    /**
     * Checks if this token is of some specific type.
     *
     * @param tag the tag to compare
     * @return true if the tokens are of the same type
     */
    public boolean is(int tag)
    {
        return this.tag == tag;
    }

    /**
     * Checks that the tokens have the same value, but, more complex tokens
     * should override this in order to check that the content of the token
     * (if any) are equal.
     *
     * @param obj the other object
     * @return true if the tokens are equal in type and value
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Token)) {
            return false;
        }

        return ((Token) obj).getTag() == getTag();
    }
}
