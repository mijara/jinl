package com.mijara.engine;

import com.mijara.ast.Function;

import java.util.ArrayList;

/**
 * Used to store the AST and metadata read from the source code
 * without inspections.
 */
public class Program
{
    private ArrayList<Function> functions = new ArrayList<>();

    public void addFunction(Function node)
    {
        functions.add(node);
    }

    public ArrayList<Function> getFunctions()
    {
        return functions;
    }
}
