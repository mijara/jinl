package com.mijara.engine.walker;

import com.mijara.ast.Function;
import com.mijara.engine.Context;

public class FunctionWalker implements Walker<Function>
{
    @Override
    public void walk(Context context, Function node)
    {
        context.addFunction(node);

        
    }
}
