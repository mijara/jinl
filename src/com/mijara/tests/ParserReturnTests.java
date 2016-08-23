package com.mijara.tests;

import com.mijara.ast.FunctionCallAST;
import com.mijara.ast.IntegerAST;
import com.mijara.ast.ReturnAST;
import com.mijara.engine.Program;
import com.mijara.engine.explorer.ProgramExplorer;
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
                returnStmt(integer(100))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());
        ReturnAST returnStatement =
                explorer.function("Main").entry().first(ReturnAST.class);

        Assert.assertNotNull(returnStatement);

        IntegerAST integer = (IntegerAST) returnStatement.getExpression();

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
