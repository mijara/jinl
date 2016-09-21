package com.mijara.tests;

import com.mijara.ast.Block;
import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import com.mijara.engine.walker.ProgramWalker;
import com.mijara.types.Type;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WalkerTests
{
    @Test
    public void testFuncDef()
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));
        Block block = new Block();
        Function function = new Function("Main", parameters, Type.getIntType(), block);

        Program program = new Program();
        program.addFunction(function);

        Context context = new Context();

        ProgramWalker walker = new ProgramWalker();
        walker.walk(context, program);

        Assert.assertEquals(context.getFunction("Main"), function);
    }

    @Test
    public void testAssignment()
    {

    }
}
