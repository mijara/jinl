package com.mijara.utils;

/**
 * Offers multiple static methods to validate data when passing to methods.
 */
public class Validate
{
    /**
     * Checks that the object is not null and throws an error if it's is.
     *
     * @param value the object to be checked.
     * @param name the name for a verbose exception in case of error.
     *
     * @throws IllegalArgumentException if the value is null.
     */
    public static void notNull(Object value, String name)
    {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Argument can't be null: %s", name));
        }
    }

    /**
     * If the object is null, then return def.
     *
     * It returns the same type as given, so you can use it as:
     * {@code someValue = notNullOrDefault(value, ifNullValue);}
     *
     * @param value the object to be tested.
     * @param def value returned if the object is null.
     * @param <T> type of the object in order to return the same type.
     * @return the object if not null, or def.
     */
    public static <T> T notNullOrDefault(T value, T def)
    {
        return value != null ? value : def;
    }

    /**
     * Simple check for null on an object.
     *
     * @param object the object to check.
     * @param <T> type of the object to return the same type.
     * @return the object passed.
     *
     * @throws IllegalArgumentException if the value is null.
     */
    public static <T> T notNull(T object)
    {
        if (object == null) {
            throw new IllegalArgumentException("Illegal null argument found.");
        }

        return object;
    }

    /**
     * Checks that an array is not null and has at least one value.
     *
     * @param array the array to check.
     *
     * @throws IllegalArgumentException if the array is null or zero-sized.
     */
    public static void notNullNorEmpty(Object[] array)
    {
        Validate.notNull(array);

        if (array.length == 0) {
            throw new IllegalArgumentException("Array mush be of length > 0");
        }
    }

    /**
     * Checks that an array is not null and has the exact size.
     *
     * @param array the array to check.
     * @param size size to expect.
     *
     * @throws IllegalArgumentException if the array is null or doesn't has the size expected.
     */
    public static void notNullAndSizeOf(Object[] array, int size)
    {
        Validate.notNull(array);

        if (array.length == size) {
            throw new IllegalArgumentException("Array mush be of length = " + size);
        }
    }
}
