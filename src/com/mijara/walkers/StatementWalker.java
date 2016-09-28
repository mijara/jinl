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

        Value value = scope.store(node.getName(), node.getInitial().accept(expressionWalker));
    }

    /**
     * Executes the walk steps for assignment.
     *
     * @param node the node to walk through.
     */
    public void walk(Assignment node)
    {

    }

    /**
     * Executes the walk steps for return.
     *
     * @param node the node to walk through.
     */
    public void walk(Return node)
    {

    }

    /**
     * Executes the walk steps for expression statements.
     *
     * @param node the node to walk through.
     */
    public void walk(ExpressionStatement node)
    {

    }
}
