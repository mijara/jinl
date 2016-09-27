package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

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

    @Override
    public Value accept(ExpressionWalker expressionWalker)
    {
        return expressionWalker.walk(this);
    }
}
