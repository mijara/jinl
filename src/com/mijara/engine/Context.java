package com.mijara.engine;

import com.mijara.ast.Function;
import com.mijara.exceptions.InvalidScopeException;
import com.mijara.exceptions.UndefinedException;
import com.mijara.walkers.FunctionWalker;
import com.mijara.walkers.ProgramWalker;
import com.mijara.walkers.WalkerException;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * A context describes the current state of a program interpretation.
 *
 * The state is a set of the functions that are defined, structs, current scope, etc.
 *
 * @author mijara
 */
public class Context
{
    /**
     * Functions defined in the program.
     */
    private HashMap<String, Function> functions = new HashMap<>();

    /**
     * A queue to hold the latest scopes.
     */
    private LinkedBlockingDeque<Scope> scopes = new LinkedBlockingDeque<>();

    private FunctionWalker functionWalker;

    /**
     * Creates a new context for runtime program state and execution.
     */
    public Context()
    {
        functionWalker = new FunctionWalker(this);
    }

    /**
     * Executes a function defined in the program.
     *
     * Note that any function can be executed, since the parser defines them all
     * before any of them can begin executing.
     *
     * @param name the name of the function to be executed.
     * @param args arguments for said function.
     * @return value returned from the function.
     */
    public Value executeFunction(String name, Value... args)
    {
        Function function = getFunction(name);
        return functionWalker.walk(function, args);
    }

    /**
     * @param function function to add to the context.
     */
    public void addFunction(Function function)
    {
        if (functions.containsKey(function.getName())) {
            throw new WalkerException("Function already defined: " + function.getName());
        }

        functions.put(function.getName(), function);
    }

    /**
     * Finds a function by it's name.
     *
     * @param name the name of the function.
     * @return the function found.
     *
     * @throws UndefinedException if the function is not registered.
     */
    public Function getFunction(String name)
    {
        if (!functions.containsKey(name)) {
            throw new UndefinedException("Function not defined: " + name);
        }

        return functions.get(name);
    }

    /**
     * Creates a new scope.
     *
     * @return the created scope.
     */
    public Scope pushScope()
    {
        scopes.add(new Scope());
        return scopes.peek();
    }

    /**
     * Removes the last scope added.
     *
     * @return the scope removed.
     *
     * @throws InvalidScopeException if no {@link #pushScope} has been called before.
     */
    public Scope popScope()
    {
        try {
            return scopes.pop();
        } catch (NoSuchElementException e) {
            throw new InvalidScopeException("No scopes left in the context.");
        }
    }

    /**
     * @return the last scope created.
     *
     * @throws InvalidScopeException if no {@link #pushScope} has been called before.
     */
    public Scope getScope()
    {
        Scope scope = scopes.peek();

        if (scope == null) {
            throw new InvalidScopeException("No scope has been defined.");
        }

        return scope;
    }

    public void loadProgram(Program program)
    {
        ProgramWalker walker = new ProgramWalker(this);
        walker.walk(program);
    }
}
