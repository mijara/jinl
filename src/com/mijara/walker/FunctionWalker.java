package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

public class FunctionWalker implements Walker<Function>
{
    private Context context;

    private BlockWalker blockWalker;

    public FunctionWalker(Context context)
    {
        this.context = context;

        blockWalker = new BlockWalker(context);
    }

    @Override
    public void walk(Function node)
    {
        context.addFunction(node);
        blockWalker.walk(node.getBlock());
    }
}
