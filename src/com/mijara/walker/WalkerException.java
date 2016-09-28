package com.mijara.walker;

/**
 * Thrown when a walker encounters an exception.
 */
public class WalkerException extends RuntimeException
{
    /**
     * @param message the message to display.
     */
    public WalkerException(String message)
    {
        super(message);
    }
}
