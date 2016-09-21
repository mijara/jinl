package com.mijara.ast;

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
}
