package com.mijara.ast;

import com.mijara.utils.Validate;

import java.util.ArrayList;

/**
 * A block of 0 or more instructions.
 *
 * @author mijara
 */
public class Block
{
    private ArrayList<Statement> statements = new ArrayList<>();

    public void addStatement(Statement node)
    {
        statements.add(Validate.notNull(node));
    }

    public ArrayList<Statement> getStatements()
    {
        return statements;
    }
}
