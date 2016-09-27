package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.utils.Validate;
import com.mijara.walker.ExpressionWalker;

import java.util.ArrayList;

/**
 * A function call is an expression that yields a return value (which can be void).
 *
 * @author mijara
 */
public class FunctionCall extends Expression
{
    /**
     * Name of the function to call.
     */
    private String functionName;

    /**
     * Arguments for the function.
     */
    private ArrayList<Expression> arguments;

    /**
     * Creates a new function call.
     *
     * @param functionName name for the function, can be null for version invocation.
     * @param arguments arguments to pass, can be null for no arguments.
     */
    public FunctionCall(String functionName, ArrayList<Expression> arguments)
    {
        this.functionName = functionName;
        this.arguments = Validate.notNullOrDefault(arguments, new ArrayList<>());
    }

    /**
     * @return arguments of this call.
     */
    public ArrayList<Expression> getArguments()
    {
        return arguments;
    }

    /**
     * @return the name of the called function.
     */
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
