package com.mijara.walkers;

import com.mijara.ast.Function;
import com.mijara.engine.Context;
import com.mijara.engine.Program;

import java.util.ArrayList;

/**
 * See {@link Walker}.
 *
 * Walker for global program constructs (such as functions and structs).
 *
 * @author mijara
 */
public class ProgramWalker extends Walker
{
    /**
     * {@inheritDoc}
     */
    public ProgramWalker(Context context)
    {
        super(context);
    }

    /**
     * Walks through each function and defines it in the global context.
     *
     * @param node the node to walk through.
     */
    public void walk(Program node)
    {
        ArrayList<Function> functions = node.getFunctions();

        for (Function function : functions) {
            getContext().addFunction(function);
        }
    }
}
