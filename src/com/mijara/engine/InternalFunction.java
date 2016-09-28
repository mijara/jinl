package com.mijara.engine;

import com.mijara.ast.Function;

/**
 * Wraps a function with metadata for the context.
 *
 * @author mijara
 */
public class InternalFunction
{
    private Function function;
    private Scope scope;

    public InternalFunction(Function function, Scope scope)
    {
        this.function = function;
        this.scope = scope;
    }

    public Function getFunction()
    {
        return function;
    }

    public Scope getScope()
    {
        return scope;
    }
}
