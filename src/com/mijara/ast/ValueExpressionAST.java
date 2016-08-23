package com.mijara.ast;

/**
 * Any expression that can hold a value, such as integerVal, string, float, etc.
 *
 * @param <T> the type to hold
 *
 * @author mijara
 */
public class ValueExpressionAST <T> extends ExpressionAST
{
    private T value;

    public ValueExpressionAST(T value)
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
