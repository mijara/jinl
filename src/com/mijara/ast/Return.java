package com.mijara.ast;

import com.mijara.exceptions.JinlInterpreterError;
import com.mijara.walkers.StatementWalker;

/**
 * Return statement node, should always be last in a block of statements.
 *
 * @author mijara
 */
public class Return extends Statement
{
    /**
     * The expression to return.
     */
    private Expression expression;

    /**
     * Creates a return statement.
     *
     * @param expression ehe expression to return.
     */
    public Return(Expression expression)
    {
        this.expression = expression;
    }

    /**
     * @return the expression to return.
     */
    public Expression getExpression()
    {
        return expression;
    }

    @Override
    public void accept(StatementWalker statementWalker)
    {
        throw new JinlInterpreterError("This method should not be called.");
    }
}
