package com.mijara.tests;

import com.mijara.engine.Program;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.ParserError;
import com.mijara.parser.RecursiveDescentParser;
import com.mijara.types.Type;
import org.junit.Test;

public class ParserGeneralTests extends FakeLexer.Builder
{
    @Test(expected = ParserError.class)
    public void testInvalidInput()
    {
        FakeLexer lexer = new FakeLexer();
        lexer.add(varDecl("NONE", Type.getIntType()));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDependencies()
    {
        new RecursiveDescentParser(null, new Program());
    }
}
