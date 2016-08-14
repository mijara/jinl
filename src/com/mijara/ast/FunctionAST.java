package com.mijara.ast;

import com.mijara.types.Type;

import java.util.ArrayList;

public class FunctionAST
{
    private String name;
    private ArrayList<ParameterAST> parameters;
    private Type returnType;

    public FunctionAST(String name, ArrayList<ParameterAST> parameters, Type returnType)
    {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
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
}
