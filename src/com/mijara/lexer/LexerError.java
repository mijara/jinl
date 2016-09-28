package com.mijara.lexer;

/**
 * Thrown when the lexer encounters some error and can't decide which token to emit.
 */
public class LexerError extends Error
{
    /**
     * @param message {@inheritDoc}
     */
    public LexerError(String message)
    {
        super(message);
    }
}
