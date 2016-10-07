package com.mijara.ast;

import com.mijara.types.Type;

import java.util.ArrayList;

/**
 * A built in function definition, which means can call any Java code.
 *
 * @author mijara
 */
public abstract class BuiltInFunction extends Function
{
    /**
     * Creates a builtin function.
     *
     * @param name       name of the function, must be unique.
     * @param version    version of the function, can be null for blank.
     * @param returnType return type of the function, can be null for void return type.
     * @param types      vararg parameter types of the function.
     */
    public BuiltInFunction(String name, String version, Type returnType, Type... types)
    {
        super(name, version, bundle(types), returnType, new Block());
    }

    /**
     * Bundles some types into a parameter array. This is useful when the parameter names
     * are not really necessary, this function generates them.
     *
     * @param types types of the parameters.
     * @return the parameter array.
     */
    public static ArrayList<Parameter> bundle(Type... types)
    {
        int id = 0;

        ArrayList<Parameter> parameters = new ArrayList<>();
        for (Type type : types) {
            parameters.add(new Parameter(type, String.valueOf(id++)));
        }

        return parameters;
    }

    /**
     * Calls the function built in data.
     *
     * @param args arguments for the built in function.
     * @return some value or null.
     */
    public abstract Object call(Object... args);
}
