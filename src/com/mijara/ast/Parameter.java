package com.mijara.ast;

import com.mijara.types.Type;
import com.mijara.utils.Validate;

/**
 * A parameter of some function declaration. It usually belongs in a list.
 */
public class Parameter
{
    /**
     * Type of this parameter.
     */
    private Type type;

    /**
     * Name of the parameter.
     */
    private String name;

    /**
     * Creates a new parameter.
     *
     * @param type the type of the parameter.
     * @param name the name of the parameter.
     */
    public Parameter(Type type, String name)
    {
        this.type = Validate.notNull(type);
        this.name = Validate.notNull(name);
    }

    /**
     * @return the type of this parameter.
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @return the name of this parameter.
     */
    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Parameter)) {
            return false;
        }

        Parameter other = (Parameter) obj;

        return type.equals(other.type) && name.equals(other.name);
    }
}
