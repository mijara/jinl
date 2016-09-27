package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

import java.util.ArrayList;

public class FunctionWalker extends Walker
{
    private BlockWalker blockWalker;

    public FunctionWalker(Context context)
    {
        super(context);

        blockWalker = new BlockWalker(context);
    }

    public void walk(Function node)
    {
        getContext().addFunction(node);

        getContext().pushScope();

        walk(node.getParameters());

        blockWalker.walk(node.getBlock());

        getContext().popScope();
    }

    public void walk(ArrayList<Parameter> parameters)
    {

    }
}
