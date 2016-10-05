package com.mijara.engine;

import com.mijara.antlr.JinlLexer;
import com.mijara.antlr.JinlParser;
import com.mijara.antlr.JinlVisitorImpl;
import com.mijara.builtins.PrintFormatBuiltInFunction;
import com.mijara.exceptions.JinlInterpreterError;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;

/**
 * This is something of a factory, it bundles all default components and modules.
 */
public abstract class Jinl
{
    /**
     * Executes a file with the default components of the Jinl Runtime.
     *
     * @param file main file to be executed.
     * @return the exit value of the program.
     *
     * @throws FileNotFoundException if the file does not exists.
     */
    public static Object execute(File file) throws FileNotFoundException
    {
        Context context = load(file);

        return context.executeFunction("Main").getValue();
    }

    /**
     * Executes a file with the default components of the Jinl Runtime.
     *
     * @param file main file to be executed.
     * @return the exit value of the program.
     *
     * @throws FileNotFoundException if the file does not exists.
     */
    public static Context load(File file)
    {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new JinlInterpreterError("Cannot read file: " + file.getName());
        }

        JinlLexer lexer = null;
        try {
            lexer = new JinlLexer(new ANTLRInputStream(fileInputStream));
        } catch (IOException e) {
            throw new JinlInterpreterError("Cannot read file: " + file.getName());
        }

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        JinlParser parser = new JinlParser(tokenStream);

        JinlParser.ProgramContext programContext = parser.program();

        JinlVisitorImpl visitor = new JinlVisitorImpl();
        Program program = (Program) visitor.visitProgram(programContext);

        Context context = new Context();
        loadBuiltIns(context);

        context.loadProgram(program);

        return context;
    }

    /**
     * Loads all built-ins to a context.
     *
     * @param context context to load built-ins to.
     */
    public static void loadBuiltIns(Context context)
    {
        context.addFunction(new PrintFormatBuiltInFunction());
    }
}
