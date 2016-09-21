package com.mijara.ast;

/**
 * Any expression that can hold a value, such as integerVal, string, float, etc.
 *
 * @param <T> the type to hold
 *
 * @author mijara
 */
public class ValueExpression<T> extends Expression
{
    private T value;

    public ValueExpression(T value)
    {
        this.value = value;
    }

    /**
     * @return the stored value.
     */
    public T getValue()
    {
        return value;
    }
}
