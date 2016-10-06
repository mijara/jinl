package com.mijara.ast;

import com.mijara.types.Type;
import com.mijara.utils.Validate;

import java.util.ArrayList;

/**
 * A standard JINL function definition.
 *
 * @author mijara
 */
public class Function
{
    /**
     * Name for this function.
     */
    private String name;

    /**
     * Parameter list of this function.
     */
    private ArrayList<Parameter> parameters;

    /**
     * Return type of this function.
     */
    private Type returnType;

    /**
     * Statement block definition.
     */
    private Block block;

    /**
     * Version of the function.
     */
    private String version;

    /**
     * Creates a function node with definition.
     *
     * @param name name of the function, must be unique.
     * @param version version name of the function, can be null for blank.
     * @param parameters parameters of the function, can be null for no parameters.
     * @param returnType return type of the function, can be null for void return type.
     * @param block definition block of the function.
     */
    public Function(String name, String version, ArrayList<Parameter> parameters, Type returnType, Block block)
    {
        this.name = Validate.notNull(name);
        this.version = Validate.notNullOrDefault(version, "");
        this.parameters = Validate.notNullOrDefault(parameters, new ArrayList<>());
        this.block = Validate.notNull(block);
        this.returnType = Validate.notNullOrDefault(returnType, Type.getVoidType());
    }

    /**
     * @return parameters of the function.
     */
    public ArrayList<Parameter> getParameters()
    {
        return parameters;
    }

    /**
     * @return name of the function.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return return type of the function.
     */
    public Type getReturnType()
    {
        return returnType;
    }

    /**
     * @return definition block of the function.
     */
    public Block getBlock()
    {
        return block;
    }

    /**
     * @return the version name of this function.
     */
    public String getVersion()
    {
        return version;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Function)) {
            return false;
        }

        Function other = (Function) obj;

        // Block and returnType are ignored since they don't define a function firm.
        return name.equals(other.name) && version.equals(other.version);
    }
}
