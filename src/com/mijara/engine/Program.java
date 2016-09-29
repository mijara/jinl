package com.mijara.engine;

import com.mijara.ast.Function;
import com.mijara.exceptions.UndefinedException;

import java.util.ArrayList;

/**
 * Used to create the AST and metadata read from the source code
 * without inspections.
 *
 * @author mijara
 */
public class Program
{
    /**
     * Functions defined in the source code.
     */
    private ArrayList<Function> functions = new ArrayList<>();

    /**
     * @param node the function to add to the program.
     */
    public void addFunction(Function node)
    {
        functions.add(node);
    }

    /**
     * @return all functions as a mutable ArrayList.
     */
    public ArrayList<Function> getFunctions()
    {
        return functions;
    }

    /**
     * Returns a function by it's name.
     *
     * @param name the name of the function.
     * @return the function found.
     *
     * @throws UndefinedException if no function is found.
     */
    public Function getFunction(String name)
    {
        for (Function function : functions) {
            if (function.getName().equals(name)) {
                return function;
            }
        }

        throw new UndefinedException("Function not defined in the program: " + name);
    }
}
