package com.mijara.walker;

import com.mijara.ast.Block;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

public class BlockWalker extends Walker
{
    private StatementWalker statementWalker;

    public BlockWalker(Context context)
    {
        super(context);

        statementWalker = new StatementWalker(context);
    }

    public void walk(Block node)
    {
        for (Statement statement : node.getStatements()) {
            statement.accept(statementWalker);
        }
    }
}
