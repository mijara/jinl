package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walkers.ExpressionWalker;

/**
 * An expression node that wraps an integer.
 */
public class StringNode extends ValueExpression<String>
{
    /**
     * Creates an integer node.
     *
     * @param value the value to wrap.
     */
    public StringNode(String value)
    {
        super(value);
    }

    @Override
    public Value accept(ExpressionWalker expressionWalker)
    {
        return expressionWalker.walk(this);
    }
}
