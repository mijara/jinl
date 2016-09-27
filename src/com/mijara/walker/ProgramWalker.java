package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.engine.Context;
import com.mijara.engine.Program;

import java.util.ArrayList;

public class ProgramWalker implements Walker<Program>
{
    private FunctionWalker functionWalker;

    public ProgramWalker(Context context)
    {
        functionWalker = new FunctionWalker(context);
    }

    @Override
    public void walk(Program node)
    {
        ArrayList<Function> functions = node.getFunctions();

        for (Function function : functions) {
            functionWalker.walk(function);
        }
    }
}
