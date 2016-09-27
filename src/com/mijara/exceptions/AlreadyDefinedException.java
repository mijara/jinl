package com.mijara.exceptions;

public class AlreadyDefinedException extends RuntimeException
{
    public AlreadyDefinedException(String message)
    {
        super(message);
    }
}
