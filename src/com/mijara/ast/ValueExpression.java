package com.mijara.ast;

import com.mijara.utils.Validate;

/**
 * Any expression that can hold a value, such as integerVal, string, float, etc.
 *
 * @param <T> the type to hold
 *
 * @author mijara
 */
public abstract class ValueExpression<T> extends Expression
{
    /**
     * The value wrapped.
     */
    private T value;

    /**
     * @param value the value to wrap.
     */
    public ValueExpression(T value)
    {
        this.value = Validate.notNull(value);
    }

    /**
     * @return the stored value.
     */
    public T getValue()
    {
        return value;
    }
}
