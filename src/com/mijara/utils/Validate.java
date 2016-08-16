package com.mijara.utils;

public class Validate
{
    public static void notNull(Object arg, String argName)
    {
        if (arg == null) {
            throw new IllegalArgumentException(String.format("Argument can't be null: %s", argName));
        }
    }

    public static <T> T notNull(T arg)
    {
        if (arg == null) {
            throw new IllegalArgumentException("Illegal null argument found.");
        }

        return arg;
    }

    public static void notNullNorEmpty(Object[] arg)
    {
        Validate.notNull(arg);

        if (arg.length == 0) {
            throw new IllegalArgumentException("Array mush be of length > 0");
        }
    }

    public static void notNullAndSizeOf(Object[] arg, int size)
    {
        Validate.notNull(arg);

        if (arg.length == size) {
            throw new IllegalArgumentException("Array mush be of length = " + size);
        }
    }
}
