package com.mijara.tests;

import com.mijara.ast.Assignment;
import com.mijara.ast.IntegerNode;
import com.mijara.engine.Program;
import com.mijara.lexer.FakeLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;
import org.junit.Assert;
import org.junit.Test;

public class ParserAssignmentTests extends FakeLexer.Builder
{
    @Test
    public void testSimpleAssignment()
    {
        int number = (int) (Math.random() * Integer.MAX_VALUE);

        FakeLexer lexer = new FakeLexer();

        lexer.add(mainFunction(
                assignment("variable", integerVal(number))
        ));

        Parser parser = new RecursiveDescentParser(lexer, new Program());
        parser.parse();

        Assignment assignment = (Assignment) parser.getProgram()
                .getFunction("Main")
                .getBlock()
                .getStatements()
                .get(0);

        Assert.assertNotNull(assignment);
        Assert.assertEquals(assignment.getVariable(), "variable");
        Assert.assertEquals(((IntegerNode) assignment.getValue()).getValue().intValue(), number);
    }
}
