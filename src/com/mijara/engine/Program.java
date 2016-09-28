package com.mijara.engine;

import com.mijara.ast.Function;

import java.util.ArrayList;

/**
 * Used to store the AST and metadata read from the source code
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
}
