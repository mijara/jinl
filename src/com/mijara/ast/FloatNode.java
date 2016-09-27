package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

public class FloatNode extends ValueExpression<Float>
{
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
