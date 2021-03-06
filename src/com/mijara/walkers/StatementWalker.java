package com.mijara.walkers;

import com.mijara.ast.Assignment;
import com.mijara.ast.ExpressionStatement;
import com.mijara.ast.Return;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Context;
import com.mijara.engine.Scope;
import com.mijara.engine.Value;

/**
 * See {@link Walker}.
 *
 *
 */
public class StatementWalker extends Walker
{
    /**
     * Walker for expressions inside statements.
     */
    private ExpressionWalker expressionWalker;

    /**
     * {@inheritDoc}
     */
    public StatementWalker(Context context)
    {
        super(context);

        this.expressionWalker = new ExpressionWalker(context);
    }

    /**
     * Executes the walk steps for variable declarations.
     *
     * @param node the node to walk through.
     */
    public void walk(VarDecl node)
    {
        Scope scope = getContext().getScope();

        if (node.getInitial() == null) {
            // case when only the type is defined.
            scope.create(node.getName(), new Value(node.getType(), null));
        } else {
            Value initial = node.getInitial().accept(expressionWalker);

            if (node.getType() == null) {
                // type is inferred from the initial value.
                scope.create(node.getName(), initial);
            } else {
                // the case when the type is defined and the initial value is too, then
                // values can be coerced.
                Value value = scope.create(node.getName(), new Value(node.getType(), null));
                value.setValue(initial);
            }
        }
    }

    /**
     * Executes the walk steps for assignment.
     *
     * @param node the node to walk through.
     */
    public void walk(Assignment node)
    {
        Value value = getContext().getScope().load(node.getVariable());
        value.setValue(node.getValue().accept(expressionWalker));
    }

    /**
     * Returns the right-hand value.
     *
     * @param node the node to walk through.
     * @return return value, this is the only statement that can return something.
     */
    public Value walk(Return node)
    {
        return node.getExpression().accept(expressionWalker);
    }

    /**
     * Gets the underlying expression and uses the expression walker
     * to execute the steps for it.
     *
     * @param node the node to walk through.
     */
    public void walk(ExpressionStatement node)
    {
        node.getExpression().accept(expressionWalker);
    }
}
