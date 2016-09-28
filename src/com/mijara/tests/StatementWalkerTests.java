package com.mijara.tests;

import com.mijara.ast.IntegerNode;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Context;
import com.mijara.engine.Value;
import com.mijara.types.Type;
import com.mijara.walkers.StatementWalker;
import org.junit.Assert;
import org.junit.Test;

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
}
