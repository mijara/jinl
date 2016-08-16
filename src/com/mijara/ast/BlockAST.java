package com.mijara.ast;

import com.mijara.utils.Validate;

import java.util.ArrayList;

/**
 * A block of 0 or more instructions.
 *
 * @author mijara
 */
public class BlockAST
{
    private ArrayList<StatementAST> statements = new ArrayList<>();

    public void addStatement(StatementAST node)
    {
        statements.add(Validate.notNull(node));
    }

    public ArrayList<StatementAST> getStatements()
    {
        return statements;
    }
}
