package com.mijara.ast;

import com.mijara.types.Type;
import com.mijara.utils.Validate;
import com.mijara.walker.StatementWalker;

/**
 * A variable declaration consisting of name, type and value, although some of
 * them are optional as stated in the constructor {@link VarDecl#VarDecl(String, Type, Expression)}
 */
public class VarDecl extends Statement
{
    /**
     * Type of the newly created variable (can be null).
     */
    private Type type;

    /**
     * Name of the newly created variable.
     */
    private String name;

    /**
     * Initial value to assign (can be null).
     */
    private Expression initial;

    /**
     * Creates a variable declaration in any of the modes indicated by the syntax guide.
     *
     * @param name name for the variable.
     * @param type type of the variable, can be null for type inference.
     * @param initial value to assign to the varible, can be null for default.
     */
    public VarDecl(String name, Type type, Expression initial)
    {
        this.name = Validate.notNull(name);
        this.type = type;
        this.initial = initial;

        if (type == null && initial == null) {
            throw new IllegalArgumentException(
                    "Type and initial value can't be null at the same time.");
        }
    }

    /**
     * @return the type of the created variable, can be null for inference.
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @return name of the created variable.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return initial value of the created variable, can be null for default.
     */
    public Expression getInitial()
    {
        return initial;
    }

    @Override
    public void accept(StatementWalker statementWalker)
    {
        statementWalker.walk(this);
    }
}
