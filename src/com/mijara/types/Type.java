package com.mijara.types;

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

    /**
     * The internal name for the type name.
     */
    private String representation;

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
        }

        throw new NoSuchTypeError("Invalid Type");
    }

    public static Type getIntType()
    {
        return intType;
    }

    public static Type getFloatType()
    {
        return floatType;
    }

    public static Type getVoidType()
    {
        return voidType;
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
}
