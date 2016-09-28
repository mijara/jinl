package com.mijara.tokens;

/**
 * Represent a function name without the '@' symbol, as in [A-Z][a-zA-Z0-9]*
 *
 * @author mijara
 */
public class FunctionNameToken extends WordToken
{
    /**
     * Construct a new token with some name. The name should not contain
     * the '@' value.
     *
     * @param name the name to assign, without '@'.
     */
    public FunctionNameToken(String name)
    {
        super(Token.FUNCTION_NAME, name);
    }
}
