package com.mijara.ast;

import com.mijara.types.Type;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A built in function definition, which means can call any Java code.
 *
 * @author mijara
 */
public abstract class BuiltInFunction extends Function
{
    /**
     * Creates a built in function node.
     *
     * @param name       name of the function, must be unique.
     * @param version    version of the function, can be null for blank.
     * @param parameters parameters of the function, can be null for no parameters.
     * @param returnType return type of the function, can be null for void return type.
     */
    public BuiltInFunction(String name, String version, ArrayList<Parameter> parameters, Type returnType)
    {
        super(name, version, parameters, returnType, new Block());
    }

    /**
     * Calls the function built in data.
     *
     * @param args arguments for the built in function.
     * @return some value or null.
     */
    public abstract Object call(HashMap<String, Object> args);
}
