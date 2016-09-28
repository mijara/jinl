package com.mijara.walker;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

import java.util.ArrayList;

/**
 * See {@link Walker}.
 *
 * Walker for function definitions.
 *
 * @author mijara
 */
public class FunctionWalker extends Walker
{
    /**
     * Used to walk through blocks.
     */
    private BlockWalker blockWalker;

    /**
     * {@inheritDoc}
     */
    public FunctionWalker(Context context)
    {
        super(context);

        blockWalker = new BlockWalker(context);
    }

    /**
     * Executes the walk steps for function definitions.
     *
     * @param node the node to walk through.
     */
    public void walk(Function node)
    {
        getContext().addFunction(node);

        getContext().pushScope();

        walk(node.getParameters());

        blockWalker.walk(node.getBlock());

        getContext().popScope();
    }

    /**
     * Executes the walk steps for parameterLists.
     *
     * @param node the node to walk through.
     */
    public void walk(ArrayList<Parameter> node)
    {

    }
}
