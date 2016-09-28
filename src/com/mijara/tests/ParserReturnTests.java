package com.mijara.tests;

import com.mijara.ast.IntegerNode;
import com.mijara.ast.Return;
import com.mijara.engine.Program;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.ParserError;
import com.mijara.parser.RecursiveDescentParser;
import com.mijara.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

public class ParserReturnTests extends FakeLexer.Builder
{
    @Test
    public void testReturnInteger()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                returnStmt(integerVal(100))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        Return returnStatement = (Return) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        Assert.assertNotNull(returnStatement);

        IntegerNode integer = (IntegerNode) returnStatement.getExpression();

        Assert.assertNotNull(integer);
    }

    @Test(expected = ParserError.class)
    public void testReturnIncomplete()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(Token.returnToken));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();
    }
}
