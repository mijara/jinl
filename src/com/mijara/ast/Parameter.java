package com.mijara.ast;

import com.mijara.types.Type;

public class Parameter
{
    private Type type;
    private String name;

    public Parameter(Type type, String name)
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
