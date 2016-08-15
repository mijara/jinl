package com.mijara.engine;

import com.mijara.ast.FunctionAST;

import java.util.ArrayList;

/**
 * Used to store the AST and metadata read from the source code
 * without inspections.
 */
public class Program
{
    private ArrayList<FunctionAST> functions = new ArrayList<>();

    public void addFunction(FunctionAST node)
    {
        functions.add(node);
    }

    public ArrayList<FunctionAST> getFunctions()
    {
        return functions;
    }
}
