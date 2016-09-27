package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.StatementWalker;

public class Assignment extends Statement
{
    String variable;
    Expression value;

    public Assignment(String variable, Expression value)
    {
        this.variable = variable;
        this.value = value;
    }

    public Expression getValue()
    {
        return value;
    }

    public String getVariable()
    {
        return variable;
    }

    @Override
    public Value accept(StatementWalker statementWalker)
    {
        return statementWalker.walk(this);
    }
}
