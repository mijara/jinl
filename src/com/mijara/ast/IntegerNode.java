package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

/**
 * An expression node that wraps an integer.
 */
public class IntegerNode extends ValueExpression<Integer>
{
    /**
     * Creates an integer node.
     *
     * @param value the value to wrap.
     */
    public IntegerNode(Integer value)
    {
        super(value);
    }

    @Override
    public Value accept(ExpressionWalker expressionWalker)
    {
        return expressionWalker.walk(this);
    }
}
