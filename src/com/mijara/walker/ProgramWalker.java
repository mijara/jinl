package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.engine.Context;
import com.mijara.engine.Program;

import java.util.ArrayList;

public class ProgramWalker extends Walker
{
    private FunctionWalker functionWalker;

    public ProgramWalker(Context context)
    {
        super(context);

        functionWalker = new FunctionWalker(context);
    }

    public void walk(Program node)
    {
        ArrayList<Function> functions = node.getFunctions();

        for (Function function : functions) {
            functionWalker.walk(function);
        }
    }
}
