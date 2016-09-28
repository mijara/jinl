package com.mijara.types;

/**
 * Thrown when there's no type by the name given.
 */
public class NoSuchTypeError extends Error
{
    /**
     * @param type type that provoke the error.
     */
    public NoSuchTypeError(String type)
    {
        super("No such type: " + type);
    }
}
