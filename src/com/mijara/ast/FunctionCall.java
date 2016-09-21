package com.mijara.ast;

import java.util.ArrayList;

public class FunctionCall extends Expression
{
    String functionName;
    ArrayList<Expression> arguments;

    public FunctionCall(String functionName, ArrayList<Expression> arguments)
    {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public ArrayList<Expression> getArguments()
    {
        return arguments;
    }

    public String getFunctionName()
    {
        return functionName;
    }
}
