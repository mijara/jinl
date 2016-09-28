package com.mijara.ast;

import com.mijara.utils.Validate;
import com.mijara.walkers.StatementWalker;

/**
 *
 */
public class Assignment extends Statement
{
    /**
     * Variable to assign to.
     */
    private String variable;

    /**
     * Value assigned.
     */
    private Expression value;

    /**
     * @param variable the variable to assign to.
     * @param value value assigned.
     */
    public Assignment(String variable, Expression value)
    {
        this.variable = Validate.notNull(variable);
        this.value = Validate.notNull(value);
    }

    /**
     * @return the right-hand value of the assignment.
     */
    public Expression getValue()
    {
        return value;
    }

    /**
     * @return the variable to assign to.
     */
    public String getVariable()
    {
        return variable;
    }

    @Override
    public void accept(StatementWalker statementWalker)
    {
        statementWalker.walk(this);
    }
}
