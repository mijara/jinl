package com.mijara.ast;

import com.mijara.utils.Validate;

import java.util.ArrayList;

/**
 * A block of 0 or more statements. It's basically an array list
 * but provides contextual meaning.
 *
 * @author mijara
 */
public class Block
{
    /**
     * Statements of this statement block.
     */
    private ArrayList<Statement> statements = new ArrayList<>();

    /**
     * @param node new statement to add to the sequence.
     */
    public void addStatement(Statement node)
    {
        statements.add(Validate.notNull(node));
    }

    /**
     * @return statements of this block.
     */
    public ArrayList<Statement> getStatements()
    {
        return statements;
    }
}
