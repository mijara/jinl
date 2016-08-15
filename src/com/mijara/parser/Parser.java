package com.mijara.parser;

import com.mijara.engine.Program;
import com.mijara.lexer.EndOfInputException;

/**
 * Part of the parsing pipeline.
 *
 * @author mijara
 */
public interface Parser
{
    /**
     * Parse the input code.
     */
    void parse() throws EndOfInputException;

    /**
     * A parser should produce a {@link Program}.
     *
     * @return the parser generated program.
     */
    Program getProgram();
}
