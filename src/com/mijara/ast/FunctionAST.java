package com.mijara.ast;

import java.util.ArrayList;

public class FunctionAST
{
    private String name;
    private ArrayList<ParameterAST> parameters;

    public FunctionAST(String name, ArrayList<ParameterAST> parameters)
    {
        this.name = name;
        this.parameters = parameters;
    }

    public ArrayList<ParameterAST> getParameters()
    {
        return parameters;
    }

    public String getName()
    {
        return name;
    }
}
