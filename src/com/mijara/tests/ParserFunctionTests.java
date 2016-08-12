package com.mijara.tests;

import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.FakeLexer;
import com.mijara.parse.Parser;
import com.mijara.parse.PhaseParser;
import com.mijara.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class ParserFunctionTests
{
    @Test
    public void testSimpleFunction()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new Token(Token.FUNCTION_NAME), new Token('('), new Token(')'));
        lexer.add(new Token(Token.KW_END));

        Parser parser = new PhaseParser(lexer);
        parser.setVisitorCallee(node -> Assert.assertEquals(node.getName(), "Test"));

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {}
    }

    @Test
    public void testSimpleFunctionWithParams()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new Token(Token.FUNCTION_NAME), new Token('('));
        lexer.add(new Token(Token.ID), new Token(Token.ID));
        lexer.add(new Token(')'));
        lexer.add(new Token(Token.KW_END));

        Parser parser = new PhaseParser(lexer);
        parser.setVisitorCallee(node -> Assert.assertThat(node.getParameters().size(), is(1)));

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {}
    }
}
