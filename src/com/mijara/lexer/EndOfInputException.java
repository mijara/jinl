package com.mijara.lexer;

import java.io.IOException;

public class EndOfInputException extends IOException
{
    public EndOfInputException()
    {
        super("No more input to read.");
    }
}
