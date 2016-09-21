package com.mijara.engine;

import com.mijara.ast.Function;
import com.mijara.engine.walker.WalkerException;

import java.util.HashMap;

public class Context
{
    private HashMap<String, Function> functions = new HashMap<>();

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
}
