package com.mijara.lexer;

import com.mijara.ast.VarDeclAST;
import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.Token;
import com.mijara.types.Type;

import java.util.ArrayList;
import java.util.Collections;

public class FakeLexer implements Lexer
{
    private ArrayList<Token> tokens = new ArrayList<>();

    private int i = 0;

    public void addToken(Token token)
    {
        tokens.add(token);
    }

    public void add(Token... tokens)
    {
        Collections.addAll(this.tokens, tokens);
    }

    @Override
    public Token getNext() throws EndOfInputException
    {
        if (i > tokens.size()) {
            throw new EndOfInputException();
        }

        if (i == tokens.size()) {
            return new Token(Token.EOC);
        }

        return tokens.get(i++);
    }

    public static class Builder
    {
        public static Lexer mainFunction(Token... block)
        {
            FakeLexer lexer = new FakeLexer();
            lexer.add(new FunctionNameToken("Main"), new Token('('), new Token(')'));
            lexer.add(block);
            lexer.add(Token.endToken);

            return lexer;
        }

        public static Token[] varDecl(String name, Type type)
        {
            return new Token[]{
                    Token.varToken,
                    new IdToken(name),
                    new Token(':'),
                    new IdToken(type.toString())
            };
        }
    }
}
