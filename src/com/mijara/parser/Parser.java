package com.mijara.parser;

import com.mijara.engine.Program;
import com.mijara.lexer.EndOfInputException;

public interface Parser
{
    /**
     * Parse the input code.
     */
    void parse() throws EndOfInputException;

    Program getProgram();
}
