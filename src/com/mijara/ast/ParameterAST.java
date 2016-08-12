package com.mijara.ast;

public class ParameterAST
{
    private String type;
    private String name;

    public ParameterAST(String type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }
}
