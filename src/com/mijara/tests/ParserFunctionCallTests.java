package com.mijara.tests;

import com.mijara.ast.ExpressionStatement;
import com.mijara.ast.FunctionCall;
import com.mijara.engine.Program;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;
import org.junit.Assert;
import org.junit.Test;

public class ParserFunctionCallTests extends FakeLexer.Builder
{
    @Test
    public void testSimple()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                functionCall("Main")
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ExpressionStatement statement = (ExpressionStatement) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        FunctionCall functionCall = (FunctionCall) statement.getExpression();

        Assert.assertEquals(functionCall.getFunctionName(), "Main");
        Assert.assertEquals(functionCall.getArguments().size(), 0);
    }

    @Test
    public void testWithArgs()
    {
        int number1 = (int) (Math.random() * Integer.MAX_VALUE);
        int number2 = (int) (Math.random() * Integer.MAX_VALUE);

        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                functionCall("Main", integerVal(number1), integerVal(number2))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ExpressionStatement statement = (ExpressionStatement) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        FunctionCall functionCall = (FunctionCall) statement.getExpression();

        Assert.assertEquals("Main", functionCall.getFunctionName());
        Assert.assertEquals(2, functionCall.getArguments().size());
        Assert.assertEquals(number1, functionCall.getArguments().get(0).toValue().getValue());
        Assert.assertEquals(number2, functionCall.getArguments().get(1).toValue().getValue());
    }

    @Test
    public void testWithArgsIdentifiers()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                functionCall("Main", identifier("a"))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ExpressionStatement statement = (ExpressionStatement) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        FunctionCall functionCall = (FunctionCall) statement.getExpression();

        Assert.assertEquals("Main", functionCall.getFunctionName());
        Assert.assertEquals(1, functionCall.getArguments().size());
        Assert.assertEquals("a", functionCall.getArguments().get(0).toValue().getValue());
    }
}
