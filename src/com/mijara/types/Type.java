package com.mijara.types;

import com.mijara.exceptions.TypeMismatchError;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A language type written as natural string.
 *
 * @author mijara
 */
public class Type
{
    private static Type intType = new Type("int");
    private static Type floatType = new Type("float");
    private static Type voidType = new Type("void");
    private static Type stringType = new Type("string");

    /**
     * The internal name for the type name.
     */
    private String representation;

    /**
     * {@inheritDoc}
     */
    private Type(String representation)
    {
        this.representation = representation;
    }

    /**
     * Returns a type from it's literal name.
     *
     * @param representation the literal representation.
     * @return the static type.
     */
    public static Type fromString(String representation)
    {
        switch (representation) {
            case "int":
                return intType;
            case "float":
                return floatType;
            case "void":
                return voidType;
            case "string":
                return stringType;
        }

        throw new NoSuchTypeError("Invalid Type");
    }

    /**
     * @return the int static type.
     */
    public static Type getIntType()
    {
        return intType;
    }

    /**
     * @return the float static type.
     */
    public static Type getFloatType()
    {
        return floatType;
    }

    /**
     * @return the void static type.
     */
    public static Type getVoidType()
    {
        return voidType;
    }

    /**
     * @return the string static type.
     */
    public static Type getStringType()
    {
        return stringType;
    }

    @Override
    public String toString()
    {
        return representation;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Type)) {
            return false;
        }

        Type other = (Type) obj;

        return other.representation.equals(representation);
    }

    /**
     * Creates an array of types from varargs types for simplification.
     *
     * @param types types to add to the array.
     * @return resulting array of types.
     */
    public static ArrayList<Type> bundle(Type... types)
    {
        ArrayList<Type> list = new ArrayList<>();
        Collections.addAll(list, types);
        return list;
    }

    /**
     * Checks the value is valid for the this type.
     *
     * @param value value to be tested.
     * @return true if the value is valid.
     */
    public boolean validate(Object value)
    {
        if (value == null) {
            // FIXME: is this alright?
            return true;
        }

        if (this.equals(floatType)) {
            return value instanceof Float;
        }

        if (this.equals(intType)) {
            return value instanceof Integer;
        }

        if (this.equals(stringType)) {
            return value instanceof String;
        }

        if (this.equals(voidType)) {
            return value instanceof Void || value == null;
        }

        throw new TypeMismatchError("Unknown value type: " + value.getClass().getSimpleName());
    }
}
