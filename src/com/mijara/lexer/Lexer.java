package com.mijara.lexer;

import com.mijara.tokens.Token;

/**
 * Part of the parsing pipeline.
 *
 * @author mijara
 */
public interface Lexer
{
    /**
     * Gets the next token from the stream.
     *
     * @return the next token.
     */
    Token getNext();
}
