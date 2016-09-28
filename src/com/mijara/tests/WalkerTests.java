package com.mijara.tests;

import com.mijara.ast.Block;
import com.mijara.ast.Function;
import com.mijara.ast.IntegerNode;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import com.mijara.engine.Value;
import com.mijara.walker.ProgramWalker;
import com.mijara.walker.StatementWalker;
import org.junit.Assert;
import org.junit.Test;

public class WalkerTests
{
    @Test
    public void testFuncDef()
    {
        Function function = new Function("Main", null, null, new Block());

        Program program = new Program();
        program.addFunction(function);

        Context context = new Context();
        ProgramWalker walker = new ProgramWalker(context);

        walker.walk(program);
        Assert.assertEquals(context.getFunction("Main"), function);
    }

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
}
