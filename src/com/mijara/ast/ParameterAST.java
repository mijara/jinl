package com.mijara.ast;

import com.mijara.types.Type;

public class ParameterAST
{
    private Type type;
    private String name;

    public ParameterAST(Type type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public Type getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }
}
