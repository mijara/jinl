package com.mijara.tests;

import com.mijara.ast.FunctionAST;
import com.mijara.ast.ParameterAST;
import com.mijara.engine.Program;
import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;
import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.Token;
import com.mijara.types.Type;
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

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        try {
            parser.parse();
            Assert.fail("Unknown error at parser time.");
        } catch (EndOfInputException ignored) {
            Program program = parser.getProgram();

            FunctionAST function = program.getFunctions().get(0);
            Assert.assertEquals(function.getName(), "Main");
        } catch (Error error) {
            Assert.fail("Unknown error: " + error.getMessage());
        }
    }

    @Test
    public void testSimpleFunctionWithParams()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new FunctionNameToken("Main"), new Token('('));
        lexer.add(new IdToken("int"), new IdToken("variable"));
        lexer.add(new Token(')'));
        lexer.add(Token.endToken);

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {
            Program program = parser.getProgram();

            FunctionAST function = program.getFunctions().get(0);
            Assert.assertThat(function.getParameters().size(), is(1));

            ParameterAST parameter = function.getParameters().get(0);
            Assert.assertThat(parameter.getType(), is(Type.getIntType()));
            Assert.assertThat(parameter.getName(), is("variable"));
        } catch (Error error) {
            Assert.fail("Unknown error: " + error.getMessage());
        }
    }

    @Test
    public void testSimpleFunctionWithParamsNonVoid()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(new FunctionNameToken("Main"), new Token('('));
        lexer.add(new IdToken("int"), new IdToken("variable"));
        lexer.add(new Token(')'));
        lexer.add(new Token(':'));
        lexer.add(new IdToken("float"));
        lexer.add(Token.endToken);

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {
            Program program = parser.getProgram();

            FunctionAST function = program.getFunctions().get(0);
            Assert.assertThat(function.getReturnType(), is(Type.getFloatType()));
        } catch (Error error) {
            Assert.fail("Unknown error: " + error.getMessage());
        }
    }
}
