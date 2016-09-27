package com.mijara.engine;

import com.mijara.ast.Function;
import com.mijara.walker.WalkerException;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Context
{
    private HashMap<String, Function> functions = new HashMap<>();

    private LinkedBlockingDeque<Scope> scopes = new LinkedBlockingDeque<>();

    public void addFunction(Function function)
    {
        if (functions.containsKey(function.getName())) {
            throw new WalkerException("Function already defined: " + function.getName());
        }

        functions.put(function.getName(), function);
    }

    public Function getFunction(String name)
    {
        return functions.get(name);
    }

    public Scope pushScope()
    {
        scopes.add(new Scope());
        return scopes.peek();
    }

    public Scope popScope()
    {
        return scopes.pop();
    }

    public Scope getScope()
    {
        Scope scope = scopes.peek();

        if (scope == null) {
            throw new RuntimeException("No scope has been defined.");
        }

        return scope;
    }
}
