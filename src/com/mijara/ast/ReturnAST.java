package com.mijara.ast;

/**
 * Return node.
 *
 * Example:
 * RETURN expression
 *
 * @author mijara
 */
public class ReturnAST extends StatementAST
{
    private ExpressionAST expression;

    public ReturnAST(ExpressionAST expression)
    {
        this.expression = expression;
    }

    public ExpressionAST getExpression()
    {
        return expression;
    }
}
