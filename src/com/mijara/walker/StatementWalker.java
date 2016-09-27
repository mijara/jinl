package com.mijara.walker;

import com.mijara.ast.Assignment;
import com.mijara.ast.ExpressionStatement;
import com.mijara.ast.Return;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Context;
import com.mijara.engine.Scope;
import com.mijara.engine.Value;

public class StatementWalker
{
    private Context context;

    private ExpressionWalker expressionWalker;

    public StatementWalker(Context context)
    {
        this.context = context;

        this.expressionWalker = new ExpressionWalker(context);
    }

    public Value walk(VarDecl node)
    {
        Scope scope = context.getScope();

        Value value = scope.store(node.getName(), node.getInitial().accept(expressionWalker));

        return null;
    }

    public Value walk(Assignment node)
    {
        return null;
    }

    public Value walk(Return node)
    {
        return null;
    }

    public Value walk(ExpressionStatement node)
    {
        return null;
    }
}
