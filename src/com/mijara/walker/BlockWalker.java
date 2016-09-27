package com.mijara.walker;

import com.mijara.ast.Block;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

public class BlockWalker implements Walker<Block>
{
    private Context context;

    private StatementWalker statementWalker;

    public BlockWalker(Context context)
    {
        this.context = context;

        statementWalker = new StatementWalker(context);
    }

    @Override
    public void walk(Block node)
    {
        context.pushScope();

        for (Statement statement : node.getStatements()) {
            statement.accept(statementWalker);
        }

        context.popScope();
    }
}
