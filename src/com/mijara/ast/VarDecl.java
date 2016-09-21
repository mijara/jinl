package com.mijara.ast;

import com.mijara.types.Type;

public class VarDecl extends Statement
{
    private Type type;
    private String name;
    private Expression initial;

    public VarDecl(String name, Type type, Expression initial)
    {
        this.type = type;
        this.name = name;
        this.initial = initial;
    }

    public Type getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public Expression getInitial()
    {
        return initial;
    }
}
