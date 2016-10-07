package com.mijara.walkers;

import com.mijara.ast.BuiltInFunction;
import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.engine.Context;
import com.mijara.engine.Scope;
import com.mijara.engine.Value;
import com.mijara.exceptions.JinlInterpreterError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * See {@link Walker}.
 * <p>
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
     * Calls a builtin function passing some arguments to it.
     *
     * @param function the builtin function.
     * @param args     arguments to pass to the function.
     * @return the returned value from the function.
     */
    public Value walk(BuiltInFunction function, Value... args)
    {
        assert function.getParameters().size() == args.length :
                String.format("BuiltIn function receives %d parameters, but got %s",
                        function.getParameters().size(), args.length);

        // create the map with all values.
        Object[] values = new Object[function.getParameters().size()];
        for (int i = 0; i < args.length; i++) {
            assert args[i].getType().equals(function.getParameters().get(i).getType()) :
                    "Parameter type mismatch.";

            values[i] = args[i].getValue();
        }

        return new Value(function.getReturnType(), function.call(values));
    }

    /**
     * Walks through a function node, pushing parameters and blocks to a newly created scope,
     * it passes all values given to those parameters in scope.
     *
     * @param node the node to walk through.
     * @param args arguments to execute the function with.
     * @return value returned from function.
     */
    public Value walk(Function node, Value... args)
    {
        // call builtin in case.
        if (node instanceof BuiltInFunction) {
            return walk((BuiltInFunction) node, args);
        }

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

            Value value = scope.create(parameter.getName(), new Value(parameter.getType(), null));
            value.setValue(args[i]);
        }
    }
}
