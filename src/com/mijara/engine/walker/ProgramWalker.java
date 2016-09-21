package com.mijara.engine.walker;

import com.mijara.ast.Function;
import com.mijara.engine.Context;
import com.mijara.engine.Program;

import java.util.ArrayList;

public class ProgramWalker implements Walker<Program>
{
    private FunctionWalker functionWalker = new FunctionWalker();

    @Override
    public void walk(Context context, Program node)
    {
        ArrayList<Function> functions = node.getFunctions();

        for (Function function : functions) {
            functionWalker.walk(context, function);
        }
    }
}
