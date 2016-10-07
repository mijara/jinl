package com.mijara.engine;

import com.mijara.exceptions.JinlInterpreterError;
import com.mijara.exceptions.TypeMismatchError;
import com.mijara.types.Type;

/**
 * Represents a tuple of type and value. Is the minimal representation of data in the
 * interpreter. It's designed to be able to hold any value and associate a (immutable) type with it.
 */
public class Value
{
    /**
     * The immutable type for this value.
     */
    private Type type;

    /**
     * The internal representation of this value.
     */
    private Object value;

    /**
     * Creates a value with a type and representation.
     *
     * @param type the immutable type for this value.
     * @param value the internal representation of the value, can be null for default.
     */
    public Value(Type type, Object value)
    {
        this.type = type;

        setRawValue(value);
    }

    /**
     * @return the immutable type of this value.
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @return the internal representation of the value.
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * @param value overrides the internal raw representation.
     */
    public void setRawValue(Object value)
    {
        // check raw value is valid for type.
        if (!type.validate(value)) {
            throw new TypeMismatchError("Value is not valid for type " + type);
        }

        this.value = value;
    }

    /**
     * Sets this value from another value, checking that the types are equal or can
     * be coerced.
     *
     * @param other overrides the internal representation.
     */
    public void setValue(Value other)
    {
        if (!getType().equals(other.getType())) {
            // TODO: check coercion.

            throw new JinlInterpreterError(String.format("Values cannot be coerced: %s -> %s",
                    other.getType().toString(), getType().toString()));
        }

        setRawValue(other.getValue());
    }

    /**
     * @return a copy of this value.
     */
    public Value copy()
    {
        return new Value(type, value);
    }
}
