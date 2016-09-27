package com.mijara.walker;

import com.mijara.ast.FloatNode;
import com.mijara.ast.FunctionCall;
import com.mijara.ast.IntegerNode;
import com.mijara.ast.ValueExpression;
import com.mijara.engine.Context;
import com.mijara.engine.Value;
import com.mijara.types.Type;

public class ExpressionWalker
{
    private final Context context;

    public ExpressionWalker(Context context)
    {
        this.context = context;
    }

    public Value walk(FloatNode node)
    {
        return null;
    }

    public Value walk(IntegerNode node)
    {
        return new Value(Type.getIntType(), node.getValue());
    }

    public Value walk(FunctionCall node)
    {
        return null;
    }
}
