package com.mijara.walkers;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.engine.Context;
import com.mijara.engine.Scope;
import com.mijara.engine.Value;

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
        Scope scope = getContext().getScope();

        for (Parameter parameter : node) {
            scope.create(parameter.getName(), new Value(parameter.getType(), null));
        }
    }
}
