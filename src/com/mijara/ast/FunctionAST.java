package com.mijara.ast;

import com.mijara.types.Type;

import java.util.ArrayList;

public class FunctionAST
{
    private String name;
    private ArrayList<ParameterAST> parameters;
    private Type returnType;
    private BlockAST block;

    public FunctionAST(String name, ArrayList<ParameterAST> parameters, Type returnType, BlockAST block)
    {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.block = block;
    }

    public ArrayList<ParameterAST> getParameters()
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

    public BlockAST getBlock()
    {
        return block;
    }
}
