package com.mijara.engine.explorer;

import com.mijara.ast.BlockAST;
import com.mijara.ast.FunctionAST;

public class FunctionExplorer
{
    private FunctionAST function;

    public FunctionExplorer(FunctionAST function)
    {
        this.function = function;
    }

    public BlockExplorer entry()
    {
        return new BlockExplorer(function.getBlock());
    }

    public FunctionAST get()
    {
        return function;
    }
}
