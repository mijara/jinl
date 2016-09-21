package com.mijara.ast;

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
}
