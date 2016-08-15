package com.mijara.tests;

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

public class ParserStatementsTests
{
    @Test
    public void testEmptyVarDecl()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(FakeLexer.Builder.mainFunction(
                FakeLexer.Builder.varDecl("someVar", Type.getFloatType())
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());

        parser.parse();

        ProgramExplorer explorer = new ProgramExplorer(parser.getProgram());

        VarDeclAST varDecl = (VarDeclAST) explorer.function("Main").entry().first();
        Assert.assertThat(varDecl.getName(), is("someVar"));
        Assert.assertThat(varDecl.getType(), is(Type.getFloatType()));
    }
}
