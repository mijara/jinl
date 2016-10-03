package com.mijara.tests;

import com.mijara.ast.Identifier;
import com.mijara.ast.IntegerNode;
import com.mijara.ast.Parameter;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Context;
import com.mijara.engine.Value;
import com.mijara.types.Type;
import com.mijara.walkers.FunctionWalker;
import com.mijara.walkers.StatementWalker;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class StatementWalkerTests
{
    @Test
    public void testAssignment()
    {
        Context context = new Context();

        context.pushScope();

        StatementWalker statementWalker = new StatementWalker(context);
        statementWalker.walk(new VarDecl("z", null, new IntegerNode(10)));
        Value value = context.getScope().load("z");

        context.popScope();

        Assert.assertEquals(10, value.getValue());
    }

    @Test
    public void testAssignmentDefault()
    {
        Context context = new Context();

        context.pushScope();

        StatementWalker statementWalker = new StatementWalker(context);
        statementWalker.walk(new VarDecl("z", Type.getIntType(), null));
        Value value = context.getScope().load("z");

        context.popScope();

        Assert.assertEquals(value.getValue(), null);
    }

    @Test
    public void testVarDeclFromParameter()
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));

        Context context = new Context();

        context.pushScope();

        // call parameters with some interger value.
        FunctionWalker functionWalker = new FunctionWalker(context);
        functionWalker.walk(parameters, new Value(Type.getIntType(), 1));

        // create a variable from the parameter.
        StatementWalker statementWalker = new StatementWalker(context);
        statementWalker.walk(new VarDecl("z", Type.getIntType(), new Identifier("a")));
        Value value = context.getScope().load("z");

        // store a new integer in the parameter value.
        Value p = context.getScope().load("a");
        p.setRawValue(5);

        context.popScope();

        // the variable created should still have the old value.
        Assert.assertEquals(value.getValue(), 1);
    }
}
