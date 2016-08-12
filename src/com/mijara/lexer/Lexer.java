package com.mijara.lexer;

import com.mijara.tokens.Token;

public interface Lexer
{
    Token getNext() throws EndOfInputException;
}
