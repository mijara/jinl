package com.mijara.lexer;

import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.IntegerToken;
import com.mijara.tokens.Token;
import com.mijara.types.Type;
import com.mijara.utils.Validate;

import java.util.ArrayList;
import java.util.Collections;

/**
 * FakeLexer is used for tests, it serves as an abstraction from a real lexer
 * and provides the tester a safe way to test other components.
 *
 * @author mijara
 */
public class FakeLexer implements Lexer
{
    private ArrayList<Token> tokens = new ArrayList<>();

    private int i = 0;

    /**
     * Adds a list of tokens, which often is created from the {@link Builder}
     *
     * @param tokens the token stream.
     */
    public void add(Token... tokens)
    {
        Collections.addAll(this.tokens, tokens);
    }

    @Override
    public Token getNext()
    {
        if (i == tokens.size()) {
            return new Token(Token.EOF);
        }

        return tokens.get(i++);
    }

    /**
     * Builder for standard token streams to use along the {@link FakeLexer}.
     * <p>
     * <pre>
     * {@code
     * FakeLexer lexer = new FakeLexer();
     * lexer.add(FakeLexer.Builder.mainFunction(
     *      FakeLexer.Builder.varDecl("someVar", Type.getFloatType())
     * ));
     * }
     * </pre>
     */
    public static class Builder
    {
        /**
         * Creates parameter stream to pass to a function.
         *
         * <pre>
         * {@code
         *
         * }
         * </pre>
         *
         * @return the token stream
         */
        public static Token[] parameters(Token[]... values)
        {
            ArrayList<Token> stream = new ArrayList<>();

            for (Token[] value : values) {
                assert value.length == 2;

                stream.add(value[0]);
                stream.add(value[1]);
            }

            return stream.toArray(new Token[0]);
        }

        /**
         * Creates a parameter stream.
         *
         * @return the token stream
         */
        public static Token[] param(Type type, String name)
        {
            return new Token[] { new IdToken(type.toString()), new IdToken(name) };
        }

        /**
         * Creates a function with void as return type.
         *
         * @param name the function name
         * @param block the block of code
         * @return the token stream
         */
        public static Token[] function(String name, Token[] params, Type type, Token... block)
        {
            ArrayList<Token> tokens = new ArrayList<>();
            tokens.add(new FunctionNameToken(name));
            tokens.add(new Token('('));
            Collections.addAll(tokens, params);
            tokens.add(new Token(')'));
            tokens.add(new Token(':'));
            tokens.add(new IdToken(type.toString()));
            Collections.addAll(tokens, block);
            tokens.add(Token.endToken);

            return tokens.toArray(new Token[0]);
        }

        /**
         * Creates a stream to represent a function with a block of code.
         *
         * @param name function name
         * @param returnType function return type
         * @param block block of code
         * @return the token stream
         */
        public static Token[] function(String name, Type returnType, Token... block)
        {
            ArrayList<Token> tokens = new ArrayList<>();
            tokens.add(new FunctionNameToken(name));
            tokens.add(new Token('('));
            tokens.add(new Token(')'));
            tokens.add(new Token(':'));
            tokens.add(new IdToken(returnType.toString()));
            Collections.addAll(tokens, block);
            tokens.add(Token.endToken);

            return tokens.toArray(new Token[0]);
        }

        /**
         * Creates a function with void as return type.
         *
         * @param name the function name
         * @param block the block of code
         * @return the token stream
         */
        public static Token[] function(String name, Token... block)
        {
            ArrayList<Token> tokens = new ArrayList<>();
            tokens.add(new FunctionNameToken(name));
            tokens.add(new Token('('));
            tokens.add(new Token(')'));
            Collections.addAll(tokens, block);
            tokens.add(Token.endToken);

            return tokens.toArray(new Token[0]);
        }

        /**
         * Creates a simple main function with some block of code.
         *
         * @param block the block of code
         * @return the token stream
         */
        public static Token[] mainFunction(Token... block)
        {
            return function("Main", block);
        }

        /**
         * Creates a variable declaration without an initial value
         *
         * @param name the new variable name
         * @param type the variable type
         * @return the token stream
         */
        public static Token[] varDecl(String name, Type type)
        {
            return new Token[]{
                    Token.varToken,
                    new IdToken(name),
                    new Token(':'),
                    new IdToken(type.toString())
            };
        }

        /**
         * Creates a variable declaration with an initial value and type.
         *
         * @param name the new variable name
         * @param type the variable type
         * @param initial some initial value
         * @return the token stream
         */
        public static Token[] varDecl(String name, Type type, Token[] initial)
        {
            ArrayList<Token> stream = new ArrayList<>();
            stream.add(Token.varToken);
            stream.add(new IdToken(name));
            stream.add(new Token(':'));
            stream.add(new IdToken(type.toString()));
            stream.add(new Token('='));
            Collections.addAll(stream, initial);

            return stream.toArray(new Token[0]);
        }

        /**
         * Creates a variable declaration with inferred type.
         *
         * @param name the new variable name
         * @param initial some initial value
         * @return the token stream
         */
        public static Token[] varDecl(String name, Token[] initial)
        {
            ArrayList<Token> stream = new ArrayList<>();
            stream.add(Token.varToken);
            stream.add(new IdToken(name));
            stream.add(new Token('='));
            Collections.addAll(stream, initial);

            return stream.toArray(new Token[0]);
        }

        public static Token[] integer(int value)
        {
            return new Token[] { new IntegerToken(value) };
        }

        public static Token[] assignment(String variable, Token[] value)
        {
            ArrayList<Token> stream = new ArrayList<>();

            stream.add(new IdToken(variable));
            stream.add(new Token('='));
            Collections.addAll(stream, value);

            return stream.toArray(new Token[0]);
        }
    }
}
