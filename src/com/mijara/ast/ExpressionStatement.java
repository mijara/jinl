package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.StatementWalker;

/**
 * An expression statement is any expression that doesn't belong to any
 * other statement, often used for function calls.
 *
 * @author mijara
 */
public class ExpressionStatement extends Statement
{
    private Expression expression;

    public ExpressionStatement(Expression expression)
    {
        this.expression = expression;
    }

    public Expression getExpression()
    {
        return expression;
    }

    @Override
    public Value accept(StatementWalker statementWalker)
    {
        return statementWalker.walk(this);
    }
}
