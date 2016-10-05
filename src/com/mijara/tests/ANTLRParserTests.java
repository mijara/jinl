package com.mijara.tests;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.engine.Context;
import com.mijara.engine.Jinl;
import com.mijara.types.Type;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class ANTLRParserTests
{
    @Test
    public void testFunctionParametersSize()
    {
        Context context = Jinl.load(new File("input/testFunctionParametersSize.jinl"));

        Function function;

        function = context.getFunction("F1");
        Assert.assertEquals(1, function.getParameters().size());

        function = context.getFunction("F2");
        Assert.assertEquals(3, function.getParameters().size());

        function = context.getFunction("Main");
        Assert.assertEquals(0, function.getParameters().size());
    }

    @Test
    public void testFunctionParams()
    {
        Context context = Jinl.load(new File("input/testFunctionParams.jinl"));

        Function function = context.getFunction("Main");
        ArrayList<Parameter> parameters = function.getParameters();
        Assert.assertEquals(1, parameters.size());

        Assert.assertEquals(Type.getFloatType(), parameters.get(0).getType());
    }

    @Test
    public void testFunctionVersion()
    {
        Context context = Jinl.load(new File("input/testFunctionVersion.jinl"));

        Function function = context.getFunction("Main");
        Assert.assertEquals("test", function.getVersion());
    }
}
