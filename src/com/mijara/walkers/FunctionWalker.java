package com.mijara.walkers;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.engine.Context;
import com.mijara.engine.Scope;
import com.mijara.engine.Value;
import com.mijara.exceptions.JinlInterpreterError;
import org.omg.CORBA.Object;

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
     * @param args arguments to execute the function with.
     */
    public Value walk(Function node, Value... args)
    {
        getContext().pushScope();

        walk(node.getParameters(), args);

        Value returnValue = blockWalker.walk(node.getBlock());

        assert returnValue.getType().equals(node.getReturnType()) :
                "Return value is not valid for function: " + node.getName();

        getContext().popScope();

        return returnValue;
    }

    /**
     * Executes the walk steps for parameterLists.
     *
     * @param node the node to walk through.
     * @param args arguments to assign to parameters.
     */
    public void walk(ArrayList<Parameter> node, Value... args)
    {
        Scope scope = getContext().getScope();

        // check parameters length.
        assert node.size() == args.length :
                String.format("Function receives %d parameters, but got %s",
                        node.size(), args.length);

        for (int i = 0; i < node.size(); i++) {
            Parameter parameter = node.get(i);

            // check parameter type.
            /*
            TODO: this may not be necessary.
            assert parameter.getType().equals(args[i].getType()) :
                    "Parameter type mismatch for function [NAME].";
            */

            Value value = scope.create(parameter.getName(), new Value(parameter.getType(), null));
            value.setValue(args[i]);
        }
    }
}
