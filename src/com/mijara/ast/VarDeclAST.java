package com.mijara.ast;

import com.mijara.types.Type;

public class VarDeclAST extends StatementAST
{
    private Type type;
    private String name;

    public VarDeclAST(String name, Type type)
    {
        this.type = type;
        this.name = name;
    }

    public Type getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }
}
