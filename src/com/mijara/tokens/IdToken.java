package com.mijara.tokens;

/**
 * Represents an ID, which can be a variable.
 *
 * @author mijara
 */
public class IdToken extends WordToken
{
    /**
     * Construct a new ID token.
     *
     * @param name the name of the id.
     */
    public IdToken(String name)
    {
        super(Token.ID, name);
    }
}
