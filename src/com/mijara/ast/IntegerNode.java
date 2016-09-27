package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

public class IntegerNode extends ValueExpression<Integer>
{
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
