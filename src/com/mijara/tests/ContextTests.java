package com.mijara.tests;

import com.mijara.ast.*;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import com.mijara.engine.Value;
import com.mijara.types.Type;
import org.junit.Test;

import java.util.ArrayList;

public class ContextTests
{
    @Test
    public void testSimple()
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));

        Function mainFunction = new Function("Main", parameters, null, new Block());
        mainFunction.getBlock().addStatement(new VarDecl("z", null, new IntegerNode(10)));
        mainFunction.getBlock().addStatement(new Assignment("a", new Identifier("z")));

        Program program = new Program();
        program.addFunction(mainFunction);

        Context context = new Context();
        context.loadProgram(program);

        context.executeFunction("Main", new Value(Type.getIntType(), 5));
    }
}
