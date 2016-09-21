package com.mijara.ast;

import com.mijara.types.Type;

import java.util.ArrayList;

public class Function
{
    private String name;
    private ArrayList<Parameter> parameters;
    private Type returnType;
    private Block block;

    public Function(String name, ArrayList<Parameter> parameters, Type returnType, Block block)
    {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.block = block;
    }

    public ArrayList<Parameter> getParameters()
    {
        return parameters;
    }

    public String getName()
    {
        return name;
    }

    public Type getReturnType()
    {
        return returnType;
    }

    public Block getBlock()
    {
        return block;
    }
}
