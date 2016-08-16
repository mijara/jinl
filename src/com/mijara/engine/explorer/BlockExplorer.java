package com.mijara.engine.explorer;

import com.mijara.ast.BlockAST;
import com.mijara.ast.StatementAST;

public class BlockExplorer
{
    private BlockAST block;

    public BlockExplorer(BlockAST block)
    {
        this.block = block;
    }

    public StatementAST first()
    {
        return block.getStatements().get(0);
    }

    public <T extends StatementAST> T first(Class<T> clazz)
    {
        return clazz.cast(block.getStatements().get(0));
    }
}
