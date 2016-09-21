package com.mijara.engine.walker;

import com.mijara.ast.Block;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

public class BlockWalker implements Walker<Block>
{
    @Override
    public void walk(Context context, Block node)
    {
        for (Statement statement : node.getStatements()) {

        }
    }
}
