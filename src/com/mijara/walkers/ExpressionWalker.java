package com.mijara.walkers;

import com.mijara.ast.*;
import com.mijara.engine.Context;
import com.mijara.engine.Value;
import com.mijara.types.Type;

/**
 * See {@link Walker}.
 *
 * Walker for expressions.
 *
 * @author mijara
 */
public class ExpressionWalker extends Walker
{
    /**
     * {@inheritDoc}
     */
    public ExpressionWalker(Context context)
    {
        super(context);
    }

    /**
     * Creates a new value from the float node.
     *
     * @param node the node to walk through
     * @return the value returned by the expression.
     */
    public Value walk(FloatNode node)
    {
        return null;
    }

    /**
     * Creates a new value from the integer node.
     *
     * @param node the node to walk through
     * @return the value returned by the expression.
     */
    public Value walk(IntegerNode node)
    {
        return new Value(Type.getIntType(), node.getValue());
    }

    /**
     * Calls a function given a {@link FunctionCall} value.
     *
     * @param node the node to walk through
     * @return the value returned by the function.
     */
    public Value walk(FunctionCall node)
    {
        Value[] values = new Value[node.getArguments().size()];

        // load values from arguments to pass to the function.
        for (int i = 0; i < node.getArguments().size(); i++) {
            values[i] = node.getArguments().get(i).accept(this);
        }

        return getContext().executeFunction(node.getFunctionName(), values);
    }

    /**
     * Takes the identifier value and returns it to be used.
     *
     * @param identifier the node to walk through
     * @return the value referenced by the identifier.
     */
    public Value walk(Identifier identifier)
    {
        return getContext().getScope().load(identifier.getValue()).copy();
    }
}
