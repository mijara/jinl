package com.mijara.engine.explorer;

import com.mijara.ast.FunctionAST;
import com.mijara.engine.Program;

public class ProgramExplorer
{
    Program program;

    public ProgramExplorer(Program program)
    {
        this.program = program;
    }

    public FunctionExplorer function(String name)
    {
        for (FunctionAST function : program.getFunctions()) {
            if (function.getName().equals(name)) {
                return new FunctionExplorer(function);
            }
        }

        throw new ExplorerError("No such function: " + name);
    }
}
