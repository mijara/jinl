package com.mijara.ast;

import com.mijara.types.Type;

public class VarDeclAST extends StatementAST
{
    private Type type;
    private String name;
    private ExpressionAST initial;

    public VarDeclAST(String name, Type type, ExpressionAST initial)
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

    public ExpressionAST getInitial()
    {
        return initial;
    }
}
