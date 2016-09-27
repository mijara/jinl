package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

/**
 * Node that contains a float value.
 */
public class FloatNode extends ValueExpression<Float>
{
    /**
     * Creates a float node.
     *
     * @param value the value to wrap.
     */
    public FloatNode(Float value)
    {
        super(value);
    }

    @Override
    public Value accept(ExpressionWalker expressionWalker)
    {
        return expressionWalker.walk(this);
    }
}
