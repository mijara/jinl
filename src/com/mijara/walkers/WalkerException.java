package com.mijara.walkers;

/**
 * Thrown when a walkers encounters an exception.
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
