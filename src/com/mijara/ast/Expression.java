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
    public ValueExpression toValue()
    {
        return (ValueExpression) this;
    }

    public abstract Value accept(ExpressionWalker expressionWalker);
}
