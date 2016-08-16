package com.mijara.ast;

public class AssignmentAST extends StatementAST
{
    String variable;
    ExpressionAST value;

    public AssignmentAST(String variable, ExpressionAST value)
    {
        this.variable = variable;
        this.value = value;
    }

    public ExpressionAST getValue()
    {
        return value;
    }

    public String getVariable()
    {
        return variable;
    }
}
