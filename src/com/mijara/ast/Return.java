package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.StatementWalker;

/**
 * Return node.
 *
 * Example:
 * RETURN expression
 *
 * @author mijara
 */
public class Return extends Statement
{
    private Expression expression;

    public Return(Expression expression)
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
