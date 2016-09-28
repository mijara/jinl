package com.mijara.parser;

/**
 * Thrown when the parser encounters an irreversible error.
 */
public class ParserError extends Error
{
    /**
     * {@inheritDoc}
     */
    public ParserError(String message)
    {
        super(message);
    }
}
