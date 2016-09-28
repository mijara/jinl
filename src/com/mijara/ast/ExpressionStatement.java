package com.mijara.ast;

import com.mijara.utils.Validate;
import com.mijara.walkers.StatementWalker;

/**
 * An expression statement is any expression that doesn't belong to any
 * other statement, often used for function calls.
 *
 * @author mijara
 */
public class ExpressionStatement extends Statement
{
    /**
     * Expression contained in the statement.
     */
    private Expression expression;

    /**
     * Creates an statement from and expression.
     *
     * @param expression the expression wrapped.
     */
    public ExpressionStatement(Expression expression)
    {
        this.expression = Validate.notNull(expression);
    }

    /**
     * @return the expression wrapped.
     */
    public Expression getExpression()
    {
        return expression;
    }

    @Override
    public void accept(StatementWalker statementWalker)
    {
        statementWalker.walk(this);
    }
}
