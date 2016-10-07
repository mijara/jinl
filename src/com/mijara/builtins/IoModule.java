package com.mijara.builtins;

import com.mijara.ast.BuiltInFunction;
import com.mijara.engine.Context;
import com.mijara.types.Type;

import java.util.Scanner;


/**
 * Module of I/O functions.
 *
 * @author mijara
 */
public class IoModule
{
    /**
     * Loads all functions in this module.
     *
     * @param context context to load the functions to.
     */
    public static void loadAllTo(Context context)
    {
        context.addFunction(new Print());
        context.addFunction(new PrintLine());
        context.addFunction(new Scan());
        context.addFunction(new ScanDisplay());
    }

    /**
     * Prints some text to standard output followed by a new line character.
     */
    public static class PrintLine extends BuiltInFunction
    {
        /**
         * Creates a builtin function.
         */
        public PrintLine()
        {
            super("Print", "line", Type.getVoidType(), Type.getIntType());
        }

        @Override
        public Object call(Object... args)
        {
            System.out.println(args[0]);
            return null;
        }
    }

    /**
     * Prints some text to standard output.
     */
    public static class Print extends BuiltInFunction
    {
        /**
         * Creates a builtin function.
         */
        public Print()
        {
            super("Print", null, Type.getVoidType(), Type.getIntType());
        }

        @Override
        public Object call(Object... args)
        {
            System.out.print(args[0]);
            return null;
        }
    }

    /**
     * Scans a line of text from the standard input.
     */
    public static class Scan extends BuiltInFunction
    {
        /**
         * Used to scan the stdin.
         */
        private Scanner scanner;

        /**
         * Creates a builtin function node.
         */
        public Scan()
        {
            super("Scan", null, Type.getStringType());
            scanner = new Scanner(System.in);
        }

        @Override
        public String call(Object... args)
        {
            return scanner.nextLine();
        }
    }

    /**
     * Prints some text to the standard output and reads from the standard input.
     */
    public static class ScanDisplay extends BuiltInFunction
    {
        /**
         * Used to scan the stdin.
         */
        private Scanner scanner;

        /**
         * Creates a builtin function node.
         */
        public ScanDisplay()
        {
            super("Scan", "display", Type.getStringType(), Type.getStringType());
            scanner = new Scanner(System.in);
        }

        @Override
        public String call(Object... args)
        {
            System.out.print(args[0]);
            return scanner.nextLine();
        }
    }
}
