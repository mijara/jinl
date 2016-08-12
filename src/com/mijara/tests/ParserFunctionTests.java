package com.mijara.tests;

import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.FakeLexer;
import com.mijara.parse.Parser;
import com.mijara.parse.PhaseParser;
import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ParserFunctionTests
{
    @Test
    public void testSimpleFunction()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new FunctionNameToken("Main"), new Token('('), new Token(')'));
        lexer.add(Token.endToken);

        Parser parser = new PhaseParser(lexer);
        parser.setVisitorCallee(node -> Assert.assertEquals(node.getName(), "Main"));

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {}
    }

    @Test
    public void testSimpleFunctionWithParams()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new FunctionNameToken("Main"), new Token('('));
        lexer.add(new IdToken("int"), new IdToken("variable"));
        lexer.add(new Token(')'));
        lexer.add(Token.endToken);

        Parser parser = new PhaseParser(lexer);
        parser.setVisitorCallee(node -> Assert.assertThat(node.getParameters().size(), is(1)));

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {}
    }
}
