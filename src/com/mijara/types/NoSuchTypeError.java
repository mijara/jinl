package com.mijara.types;

public class NoSuchTypeError extends Error
{
    public NoSuchTypeError(String type)
    {
        super("No such type: " + type);
    }
}
