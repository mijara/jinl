package com.mijara.engine;

import com.mijara.exceptions.AlreadyDefinedException;
import com.mijara.exceptions.UndefinedException;

import java.util.HashMap;

/**
 * Represents a enclosing state for the block of code being executed.
 *
 * @author mijara
 */
public class Scope
{
    /**
     * Variables defined.
     */
    private HashMap<String, Value> variables = new HashMap<>();

    /**
     * Creates a new variable into this scope.
     *
     * @param name name for the variable.
     * @param value the value the variable holds.
     * @return the value passed as argument for one-liners.
     *
     * @throws AlreadyDefinedException if the variable name is already registered in this scope.
     */
    public Value create(String name, Value value)
    {
        if (variables.containsKey(name)) {
            throw new AlreadyDefinedException("Variable already defined: " + name);
        }

        variables.put(name, value);

        return value;
    }

    /**
     * Stores some value to an existing variable in this scope.
     *
     * @param name name for the variable.
     * @param value the value to store in the variable.
     * @return the value passed as argument for one-liners.
     *
     * @throws UndefinedException if the variable is not registered in this scope.
     */
    public Value store(String name, Value value)
    {
        if (!variables.containsKey(name)) {
            throw new UndefinedException("Undefined variable: " + name);
        }

        variables.put(name, value);

        return value;
    }

    /**
     * Returns a variable registered by the given name.
     *
     * @param name the name to look for.
     * @return the variable found.
     *
     * @throws UndefinedException if there's no variable by such name.
     */
    public Value load(String name)
    {
        if (!variables.containsKey(name)) {
            throw new UndefinedException("Undefined variable: " + name);
        }

        return variables.get(name);
    }
}
