package com.mijara.parse;

import com.mijara.lexer.EndOfInputException;
import com.mijara.tokens.Token;

public interface Parser
{
    /**
     * @param visitorCallee the pass callee used by the parser.
     */
    void setVisitorCallee(VisitorCallee visitorCallee);

    /**
     * Parse the input code.
     */
    void parse() throws EndOfInputException;
}
