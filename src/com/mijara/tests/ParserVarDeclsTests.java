package com.mijara.tests;

import com.mijara.ast.IntegerAST;
import com.mijara.ast.VarDeclAST;
import com.mijara.engine.Program;
import com.mijara.engine.explorer.ProgramExplorer;
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

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());

        VarDeclAST varDecl = (VarDeclAST) explorer.function("Main").entry().first();
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

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());

        VarDeclAST varDecl = (VarDeclAST) explorer.function("Main").entry().first();
        Assert.assertNotNull(varDecl.getInitial());

        IntegerAST initial = (IntegerAST) varDecl.getInitial();
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

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());

        VarDeclAST varDecl = (VarDeclAST) explorer.function("Main").entry().first();
        Assert.assertNotNull(varDecl.getInitial());
        Assert.assertNull(varDecl.getType());

        IntegerAST initial = (IntegerAST) varDecl.getInitial();
        Assert.assertEquals(initial.getValue().intValue(), number);
    }
}
