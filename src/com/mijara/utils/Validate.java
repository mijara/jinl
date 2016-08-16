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
}
