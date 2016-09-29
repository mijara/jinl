package com.mijara.walkers;

import com.mijara.ast.FloatNode;
import com.mijara.ast.FunctionCall;
import com.mijara.ast.Identifier;
import com.mijara.ast.IntegerNode;
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
     * Executes the walk steps for function calls.
     *
     * @param node the node to walk through
     * @return the value returned by the expression.
     */
    public Value walk(FunctionCall node)
    {
        return null;
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
