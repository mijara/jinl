package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.ExpressionWalker;

/**
 * Any expression that can hold a value, such as integerVal, string, float, etc.
 *
 * @param <T> the type to hold
 *
 * @author mijara
 */
public abstract class ValueExpression<T> extends Expression
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
