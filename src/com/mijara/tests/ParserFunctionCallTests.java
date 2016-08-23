package com.mijara.tests;

import com.mijara.ast.ExpressionStatementAST;
import com.mijara.ast.FunctionCallAST;
import com.mijara.engine.Program;
import com.mijara.engine.explorer.ProgramExplorer;
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

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ExpressionStatementAST statement =
                explorer.function("Main").entry().first(ExpressionStatementAST.class);
        FunctionCallAST functionCall = (FunctionCallAST) statement.getExpression();

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

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ExpressionStatementAST statement =
                explorer.function("Main").entry().first(ExpressionStatementAST.class);
        FunctionCallAST functionCall = (FunctionCallAST) statement.getExpression();

        Assert.assertEquals("Main", functionCall.getFunctionName());
        Assert.assertEquals(2, functionCall.getArguments().size());
        Assert.assertEquals(number1, functionCall.getArguments().get(0).toValue().getValue());
        Assert.assertEquals(number2, functionCall.getArguments().get(1).toValue().getValue());
    }
}
