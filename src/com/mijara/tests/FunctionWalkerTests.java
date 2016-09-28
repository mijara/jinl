package com.mijara.tests;

import com.mijara.ast.*;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import com.mijara.engine.Value;
import com.mijara.exceptions.AlreadyDefinedException;
import com.mijara.types.Type;
import com.mijara.walkers.FunctionWalker;
import com.mijara.walkers.ProgramWalker;
import com.mijara.walkers.StatementWalker;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FunctionWalkerTests
{
    @Test
    public void testFuncDef()
    {
        Function function = new Function("Main", null, null, new Block());

        FunctionWalker walker = new FunctionWalker(new Context());
        walker.walk(function);

        Assert.assertEquals(walker.getContext().getFunction("Main"), function);
    }

    @Test(expected = AlreadyDefinedException.class)
    public void testFuncParameterDefs()
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));

        Block block = new Block();
        block.addStatement(new VarDecl("a", Type.getIntType(), null));

        Function function = new Function("Main", parameters, null, block);

        FunctionWalker walker = new FunctionWalker(new Context());
        walker.walk(function);
    }
}
