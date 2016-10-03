package com.mijara.walkers;

import com.mijara.ast.Block;
import com.mijara.ast.Return;
import com.mijara.ast.Statement;
import com.mijara.engine.Context;
import com.mijara.engine.Value;
import com.mijara.types.Type;

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
     * @return the return value from the block.
     */
    public Value walk(Block node)
    {
        for (Statement statement : node.getStatements()) {
            // use the visitor pattern specified in the Walker class.

            if (statement instanceof Return) {
                // return the value.
                return statementWalker.walk((Return) statement);
            } else {
                statement.accept(statementWalker);
            }
        }

        // return void.
        return new Value(Type.getVoidType(), null);
    }
}
