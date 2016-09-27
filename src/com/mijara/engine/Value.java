package com.mijara.engine;

import com.mijara.types.Type;

public class Value
{
    private Type type;

    private Object value;

    public Value(Type type, Object value)
    {
        this.type = type;
        this.value = value;
    }

    public Type getType()
    {
        return type;
    }

    public Object getValue()
    {
        return value;
    }
}
