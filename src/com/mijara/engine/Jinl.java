package com.mijara.engine;

import com.mijara.lexer.StreamLexer;
import com.mijara.parser.Parser;
import com.mijara.parser.RecursiveDescentParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
        InputStream inputStream = new FileInputStream(file);
        System.setIn(inputStream);

        Parser parser = new RecursiveDescentParser(new StreamLexer(), new Program());
        parser.parse();

        System.setIn(System.in);

        Context context = new Context();
        context.loadProgram(parser.getProgram());

        return context.executeFunction("Main").getValue();
    }
}