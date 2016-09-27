package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

/**
 * An expression is anything that can return some value (even void).
 *
 * @author mijara
 */
public abstract class Expression
{
    /**
     * @return this expression as a {@link ValueExpression}.
     */
    public ValueExpression toValue()
    {
        return (ValueExpression) this;
    }

    /**
     * Part of the Visitor Pattern implemented in Expression.
     *
     * @param expressionWalker the walker (visitor) to accept.
     * @return some {@link Value}.
     */
    public abstract Value accept(ExpressionWalker expressionWalker);
}
