package com.mijara.ast;

/**
 * An expression statement is any expression that doesn't belong to any
 * other statement, often used for function calls.
 *
 * @author mijara
 */
public class ExpressionStatementAST extends StatementAST
{
    private ExpressionAST expression;

    public ExpressionStatementAST(ExpressionAST expression)
    {
        this.expression = expression;
    }

    public ExpressionAST getExpression()
    {
        return expression;
    }
}
