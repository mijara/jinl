package com.mijara.tests;

import com.mijara.ast.ExpressionAST;
import com.mijara.ast.ExpressionStatementAST;
import com.mijara.ast.IntegerAST;
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
    public void testIntegerType()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                integer(100)
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ExpressionStatementAST statement = explorer
                .function("Main")
                .entry()
                .first(ExpressionStatementAST.class);

        ExpressionAST expression = statement.getExpression();

        Assert.assertTrue("The expression should be an IntegerAST.",
                expression instanceof IntegerAST);

        Assert.assertThat(((IntegerAST) expression).getValue(), is(100));
    }
}
