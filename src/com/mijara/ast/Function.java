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
     * Creates a function node with definition.
     *
     * @param name name of the function, must be unique.
     * @param parameters parameters of the function, can be null for no parameters.
     * @param returnType return type of the function, can be null for void return type.
     * @param block definition block of the function.
     */
    public Function(String name, ArrayList<Parameter> parameters, Type returnType, Block block)
    {
        this.name = Validate.notNull(name);
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
}
