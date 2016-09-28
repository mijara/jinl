package com.mijara.walker;

import com.mijara.ast.FloatNode;
import com.mijara.ast.FunctionCall;
import com.mijara.ast.IntegerNode;
import com.mijara.ast.ValueExpression;
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
     * Executes the walk steps for floats.
     *
     * @param node the node to walk through
     * @return the value returned by the expression.
     */
    public Value walk(FloatNode node)
    {
        return null;
    }

    /**
     * Executes the walk steps for integers.
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
}
