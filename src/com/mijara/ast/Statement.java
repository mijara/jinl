package com.mijara.ast;

import com.mijara.walkers.StatementWalker;

/**
 * A statement is a construction that executes some feature of the language.
 * No statement should return a value.
 *
 * @author mijara
 */
public abstract class Statement
{
    /**
     * Part of the Visitor pattern for statements.
     *
     * @param statementWalker the walkers (visitor) to accept.
     */
    public abstract void accept(StatementWalker statementWalker);
}
