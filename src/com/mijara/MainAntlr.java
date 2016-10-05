package com.mijara;

import com.mijara.antlr.JinlLexer;
import com.mijara.antlr.JinlParser;
import com.mijara.antlr.JinlVisitorImpl;
import com.mijara.builtins.PrintFormatBuiltInFunction;
import com.mijara.engine.Context;
import com.mijara.engine.Program;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;

public class MainAntlr
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream("input/demo.jinl");

        JinlLexer lexer = new JinlLexer(new ANTLRInputStream(fileInputStream));

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        JinlParser parser = new JinlParser(tokenStream);

        JinlParser.ProgramContext programContext = parser.program();

        JinlVisitorImpl visitor = new JinlVisitorImpl();
        Program program = (Program) visitor.visitProgram(programContext);

        Context context = new Context();
        context.addFunction(new PrintFormatBuiltInFunction());
        context.loadProgram(program);

        System.out.println(context.executeFunction("Main").getValue());
    }
}
