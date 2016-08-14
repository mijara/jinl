package com.mijara.types;

public class Type
{
    private static Type intType = new Type("int");
    private static Type floatType = new Type("float");

    private String representation;

    private Type(String representation)
    {
        this.representation = representation;
    }

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

    @Override
    public String toString()
    {
        return representation;
    }
}
