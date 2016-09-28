package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.engine.Context;
import com.mijara.engine.Program;

import java.util.ArrayList;

/**
 * See {@link Walker}.
 *
 * Walker for the program.
 *
 * @author mijara
 */
public class ProgramWalker extends Walker
{
    /**
     * Walker for function definitions.
     */
    private FunctionWalker functionWalker;

    /**
     * {@inheritDoc}
     */
    public ProgramWalker(Context context)
    {
        super(context);

        functionWalker = new FunctionWalker(context);
    }

    /**
     * Executes the walk steps for programs.
     *
     * @param node the node to walk through.
     */
    public void walk(Program node)
    {
        ArrayList<Function> functions = node.getFunctions();

        for (Function function : functions) {
            functionWalker.walk(function);
        }
    }
}
