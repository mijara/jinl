package com.mijara.tests;

import com.mijara.ast.Function;
import com.mijara.ast.Parameter;
import com.mijara.ast.Return;
import com.mijara.ast.ValueExpression;
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

        function = context.getFunction("F1", null);
        Assert.assertEquals(1, function.getParameters().size());

        function = context.getFunction("F2", null);
        Assert.assertEquals(3, function.getParameters().size());

        function = context.getFunction("Main", null);
        Assert.assertEquals(0, function.getParameters().size());
    }

    @Test
    public void testFunctionParams()
    {
        Context context = Jinl.load(new File("input/testFunctionParams.jinl"));

        Function function = context.getFunction("Main", null);
        ArrayList<Parameter> parameters = function.getParameters();
        Assert.assertEquals(1, parameters.size());

        Assert.assertEquals(Type.getFloatType(), parameters.get(0).getType());
    }

    @Test
    public void testFunctionVersion()
    {
        Context context = Jinl.load(new File("input/testFunctionVersion.jinl"));

        Function function = context.getFunction("Main", null);
        Assert.assertEquals("test", function.getVersion());
    }

    @Test
    public void testString()
    {
        Context context = Jinl.load(new File("input/testString.jinl"));

        Function function = context.getFunction("Main", null);
        Return returnStatement = (Return) function.getBlock().getStatements().get(0);

        Assert.assertNotNull(returnStatement.getExpression());
        Assert.assertTrue(returnStatement.getExpression() instanceof ValueExpression);
        Assert.assertEquals("test", ((ValueExpression<String>) returnStatement.getExpression()).getValue());
    }
}
