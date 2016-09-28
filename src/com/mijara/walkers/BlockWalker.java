package com.mijara.walkers;

import com.mijara.ast.Block;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;

/**
 * See {@link Walker}.
 *
 * Walker for the Block construct.
 *
 * @author mijara
 */
public class BlockWalker extends Walker
{
    /**
     * Walker for each statement in the block.
     */
    private StatementWalker statementWalker;

    /**
     * {@inheritDoc}
     */
    public BlockWalker(Context context)
    {
        super(context);

        statementWalker = new StatementWalker(context);
    }

    /**
     * Execute the walk steps for blocks.
     *
     * @param node block to walk through.
     */
    public void walk(Block node)
    {
        for (Statement statement : node.getStatements()) {
            // use the visitor pattern specified in the Walker class.
            statement.accept(statementWalker);
        }
    }
}
