package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walkers.ExpressionWalker;

/**
 * An expression is anything that can return some value (even void).
 *
 * @author mijara
 */
public abstract class Expression
{
    /**
     * Part of the Visitor Pattern implemented in Expression.
     *
     * @param expressionWalker the walkers (visitor) to accept.
     * @return some {@link Value}.
     */
    public abstract Value accept(ExpressionWalker expressionWalker);
}
