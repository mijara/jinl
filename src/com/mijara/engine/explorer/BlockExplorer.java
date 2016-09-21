package com.mijara.engine.explorer;

import com.mijara.ast.Block;
import com.mijara.ast.Statement;

public class BlockExplorer
{
    private Block block;

    public BlockExplorer(Block block)
    {
        this.block = block;
    }

    public Statement first()
    {
        return block.getStatements().get(0);
    }

    public <T extends Statement> T first(Class<T> clazz)
    {
        return clazz.cast(block.getStatements().get(0));
    }
}
