package com.mijara.tests;

import com.mijara.ast.Expression;
import com.mijara.ast.ExpressionStatement;
import com.mijara.ast.FloatNode;
import com.mijara.ast.IntegerNode;
import com.mijara.engine.Program;
import com.mijara.engine.explorer.ProgramExplorer;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class ParserBuiltInValuesTests extends FakeLexer.Builder
{
    @Test
    public void testIntegerValue()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                integerVal(100)
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ExpressionStatement statement = explorer
                .function("Main")
                .entry()
                .first(ExpressionStatement.class);

        Expression expression = statement.getExpression();

        Assert.assertTrue("The expression should be an IntegerNode.",
                expression instanceof IntegerNode);

        Assert.assertThat(((IntegerNode) expression).getValue(), is(100));
    }

    @Test
    public void testFloatValue()
    {
        float number = (float) (Math.random() * Float.MAX_VALUE);

        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                floatVal(number)
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ExpressionStatement statement = explorer
                .function("Main")
                .entry()
                .first(ExpressionStatement.class);

        Expression expression = statement.getExpression();

        Assert.assertTrue("The expression should be an FloatNode.",
                expression instanceof FloatNode);

        Assert.assertThat(((FloatNode) expression).getValue(), is(number));
    }
}
