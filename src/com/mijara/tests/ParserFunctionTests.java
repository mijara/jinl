package com.mijara.tests;

import com.mijara.ast.FunctionAST;
import com.mijara.ast.ParameterAST;
import com.mijara.engine.Program;
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

public class ParserFunctionTests extends FakeLexer.Builder
{
    @Test
    public void testSimpleFunction()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction());

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();

        Program program = parser.getProgram();

        FunctionAST function = program.getFunctions().get(0);
        Assert.assertEquals(function.getName(), "Main");
    }

    @Test
    public void testSimpleFunctionWithParams()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(functionWithParams("Main",
                parameters(param(Type.getIntType(), "variable"))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();
        Program program = parser.getProgram();

        FunctionAST function = program.getFunctions().get(0);
        Assert.assertThat(function.getParameters().size(), is(1));

        ParameterAST parameter = function.getParameters().get(0);
        Assert.assertThat(parameter.getType(), is(Type.getIntType()));
        Assert.assertThat(parameter.getName(), is("variable"));
    }

    @Test
    public void testSimpleFunctionWithParamsNonVoid()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(function("Main",
                parameters(param(Type.getIntType(), "someVar")),
                Type.getFloatType()
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();
        Program program = parser.getProgram();

        FunctionAST function = program.getFunctions().get(0);
        Assert.assertThat(function.getReturnType(), is(Type.getFloatType()));
    }
}
