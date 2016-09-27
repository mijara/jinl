package com.mijara.engine;

import com.mijara.exceptions.AlreadyDefinedException;
import com.mijara.exceptions.UndefinedException;

import java.util.HashMap;

public class Scope
{
    private HashMap<String, Value> variables = new HashMap<>();

    public Value store(String name, Value value)
    {
        if (variables.containsKey(name)) {
            throw new AlreadyDefinedException("Variable already defined: " + name);
        }

        variables.put(name, value);

        return value;
    }

    public Value load(String name)
    {
        if (!variables.containsKey(name)) {
            throw new UndefinedException("Variable not defined: " + name);
        }

        return variables.get(name);
    }
}
