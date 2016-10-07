package com.mijara.tests;

import com.mijara.ast.*;
import com.mijara.builtins.IoModule;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import com.mijara.engine.Value;
import com.mijara.exceptions.AlreadyDefinedException;
import com.mijara.types.Type;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ContextTests
{
    @Test
    public void testSimple()
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));

        Function mainFunction = new Function("Main", null, parameters, null, new Block());
        mainFunction.getBlock().addStatement(new VarDecl("z", null, new IntegerNode(10)));
        mainFunction.getBlock().addStatement(new Assignment("a", new Identifier("z")));

        Program program = new Program();
        program.addFunction(mainFunction);

        Context context = new Context();
        context.loadProgram(program);

        context.executeFunction("Main", null, null, new Value(Type.getIntType(), 5));
    }

    @Test
    public void testBuiltIn()
    {
        BuiltInFunction function = new IoModule.PrintLine();

        Context context = new Context();
        context.addFunction(function);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        context.executeFunction("Print", "format", null, new Value(Type.getIntType(), 10));

        System.setOut(null);

        Assert.assertEquals("10\n", outContent.toString());
    }

    @Test(expected = AlreadyDefinedException.class)
    public void testFunctionIdentifierRepeated()
    {
        Context context = new Context();
        context.addFunction(new Function("Main", null, null, null, new Block()));
        context.addFunction(new Function("Main", null, null, null, new Block()));
    }

    @Test
    public void testFunctionIdentifier()
    {
        Context context = new Context();
        context.addFunction(new Function("Main", "first", null, null, new Block()));
        context.addFunction(new Function("Main", "second", null, null, new Block()));
    }
}
