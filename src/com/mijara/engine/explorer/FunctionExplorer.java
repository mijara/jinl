package com.mijara.engine.explorer;

import com.mijara.ast.Function;

public class FunctionExplorer
{
    private Function function;

    public FunctionExplorer(Function function)
    {
        this.function = function;
    }

    public BlockExplorer entry()
    {
        return new BlockExplorer(function.getBlock());
    }

    public Function get()
    {
        return function;
    }
}
