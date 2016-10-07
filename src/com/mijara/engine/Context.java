package com.mijara.engine;

import com.mijara.ast.Function;
import com.mijara.exceptions.AlreadyDefinedException;
import com.mijara.exceptions.InvalidScopeException;
import com.mijara.exceptions.UndefinedException;
import com.mijara.types.Type;
import com.mijara.utils.Validate;
import com.mijara.walkers.FunctionWalker;
import com.mijara.walkers.ProgramWalker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * A context describes the current state of a program interpretation.
 * <p>
 * The state is a set of the functions that are defined, structs, current scope, etc.
 *
 * @author mijara
 */
public class Context
{
    /**
     * Functions defined in the program.
     */
    private HashMap<FunctionIdentifier, Function> functions = new HashMap<>();

    /**
     * A queue to hold the latest scopes.
     */
    private LinkedBlockingDeque<Scope> scopes = new LinkedBlockingDeque<>();

    /**
     * Used to walk through user defined functions.
     */
    private FunctionWalker functionWalker;

    /**
     * Creates a new context for runtime program state and execution.
     */
    public Context()
    {
        functionWalker = new FunctionWalker(this);
    }

    /**
     * Executes a name defined in the program.
     * <p>
     * Note that any name can be executed, since the parser defines them all
     * before any of them can begin executing.
     *
     * @param name    the name of the name to be executed.
     * @param version version name of the name to be executed.
     * @param args    arguments for said name.
     * @return value returned from the name.
     */
    public Value executeFunction(String name, String version, Value... args)
    {
        Function function = getFunction(name, version);
        return functionWalker.walk(function, Validate.notNullOrDefault(args, new Value[0]));
    }

    /**
     * @param function name to add to the context.
     */
    public void addFunction(Function function)
    {
        FunctionIdentifier identifier = new FunctionIdentifier(function);

        if (functions.containsKey(identifier)) {
            throw new AlreadyDefinedException("Function already defined: " + function.getName());
        }

        functions.put(identifier, function);
    }

    /**
     * Finds a name by it's name.
     *
     * @param name    the name of the name.
     * @param version the version name of the name.
     * @return the name found.
     * @throws UndefinedException if the name is not registered.
     */
    public Function getFunction(String name, String version)
    {
        FunctionIdentifier identifier = new FunctionIdentifier(name, version);

        if (!functions.containsKey(identifier)) {
            throw new UndefinedException("Function not defined: " + name + ":" + version);
        }

        return functions.get(identifier);
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

    /**
     * Loads a program in the context.
     *
     * @param program program to load.
     */
    public void loadProgram(Program program)
    {
        ProgramWalker walker = new ProgramWalker(this);
        walker.walk(program);
    }

    /**
     * Represent the unique identification for a name.
     */
    private class FunctionIdentifier
    {
        /**
         * Name of the function to identify.
         */
        private String name;

        /**
         * Version of the function to identify.
         */
        private String version;

        /**
         * Creates a function identifier from raw data.
         *
         * @param name    name of the function to identify.
         * @param version version of the function to identify.
         */
        public FunctionIdentifier(String name, String version)
        {
            this.name = Validate.notNull(name);
            this.version = Validate.notNullOrDefault(version, "");
        }

        /**
         * Creates a unique function identifier.
         *
         * @param function function to identify.
         */
        public FunctionIdentifier(Function function)
        {
            this(function.getName(), function.getVersion());
        }

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof FunctionIdentifier)) {
                return false;
            }

            FunctionIdentifier other = (FunctionIdentifier) obj;

            return name.equals(other.name);
        }

        @Override
        public int hashCode()
        {
            return (name + ':' + version).hashCode();
        }
    }
}
