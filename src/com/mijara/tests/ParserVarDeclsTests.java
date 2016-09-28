package com.mijara.tests;

import com.mijara.ast.IntegerNode;
import com.mijara.ast.VarDecl;
import com.mijara.engine.Program;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;
import com.mijara.types.Type;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ParserVarDeclsTests extends FakeLexer.Builder
{
    @Test
    public void testEmpty()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                varDecl("someVar", Type.getFloatType())
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();

        VarDecl varDecl = (VarDecl) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        Assert.assertThat(varDecl.getName(), is("someVar"));
        Assert.assertThat(varDecl.getType(), is(Type.getFloatType()));
    }

    @Test
    public void testWithInitial()
    {
        int number = (int) (Math.random() * Integer.MAX_VALUE);

        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                varDecl("someVar", Type.getFloatType(),
                        integerVal(number)
                )
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();

        VarDecl varDecl = (VarDecl) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        Assert.assertNotNull(varDecl.getInitial());

        IntegerNode initial = (IntegerNode) varDecl.getInitial();
        Assert.assertEquals(initial.getValue().intValue(), number);
    }

    @Test
    public void testWithInitialAndInference()
    {
        int number = (int) (Math.random() * Integer.MAX_VALUE);

        FakeLexer lexer = new FakeLexer();
        lexer.add(mainFunction(
                varDecl("someVar", integerVal(number))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();

        VarDecl varDecl = (VarDecl) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        Assert.assertNotNull(varDecl.getInitial());
        Assert.assertNull(varDecl.getType());

        IntegerNode initial = (IntegerNode) varDecl.getInitial();
        Assert.assertEquals(initial.getValue().intValue(), number);
    }
}
