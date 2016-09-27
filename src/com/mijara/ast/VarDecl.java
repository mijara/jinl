package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.types.Type;
import com.mijara.walker.StatementWalker;

public class VarDecl extends Statement
{
    private Type type;
    private String name;
    private Expression initial;

    public VarDecl(String name, Type type, Expression initial)
    {
        this.type = type;
        this.name = name;
        this.initial = initial;
    }

    public Type getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public Expression getInitial()
    {
        return initial;
    }

    @Override
    public Value accept(StatementWalker statementWalker)
    {
        return statementWalker.walk(this);
    }
}
