package com.mijara.ast;

import java.util.ArrayList;

public class FunctionCallAST extends ExpressionAST
{
    String functionName;
    ArrayList<ExpressionAST> arguments;

    public FunctionCallAST(String functionName, ArrayList<ExpressionAST> arguments)
    {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public ArrayList<ExpressionAST> getArguments()
    {
        return arguments;
    }

    public String getFunctionName()
    {
        return functionName;
    }
}
