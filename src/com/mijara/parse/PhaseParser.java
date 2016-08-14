package com.mijara.parse;

import com.mijara.ast.FunctionAST;
import com.mijara.ast.ParameterAST;
import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.Lexer;
import com.mijara.tokens.Token;
import com.mijara.types.Type;

import java.util.ArrayList;

public class PhaseParser implements Parser
{
    private VisitorCallee visitorCallee;
    private Lexer lexer;

    private Token token;

    public PhaseParser(Lexer lexer)
    {
        this.lexer = lexer;

        try {
            nextToken();
        } catch (EndOfInputException e) {
            throw new RuntimeException("Invalid state!");
        }
    }

    @Override
    public void parse() throws EndOfInputException
    {
        switch (token.getTag()) {
            case Token.FUNCTION_NAME:
                visitorCallee.visit(parseFunction());
                break;
        }

        parse();
    }

    /**
     * function : FUNCTION_NAME '(' [ ID ';'] parameterList ')' [ ':' typeName]
     *                  block
     *            END
     *
     * @return the AST node.
     * @throws EndOfInputException
     */
    private FunctionAST parseFunction() throws EndOfInputException
    {
        String name = token.toFunctionName().getValue();

        nextToken();

        assertToken('(');

        // eat '('
        nextToken();

        // parse parameterList.
        ArrayList<ParameterAST> parameters = new ArrayList<>();
        while (token.is(Token.ID)) {
            parameters.add(parseParameter());

            if (token.is(',')) {
                // eat ','
                nextToken();
            }
        }

        assertToken(')');
        // eat ')'
        nextToken();

        Type returnType = null;
        if (token.is(':')) {
            // eat ':'
            nextToken();

            assertToken(Token.ID);
            returnType = Type.fromString(token.toId().getValue());

            // eat type.
            nextToken();
        }

        assertToken(Token.END);
        nextToken(); // eat END.

        return new FunctionAST(name, parameters, returnType);
    }

    private ParameterAST parseParameter() throws EndOfInputException
    {
        Type type = Type.fromString(token.toId().getValue());
        // eat type.
        nextToken();

        assertToken(Token.ID);
        String name = token.toId().getValue();

        // eat name.
        nextToken();

        return new ParameterAST(type, name);
    }

    private void assertToken(int tokenTag) throws EndOfInputException
    {
        if (!token.is(tokenTag)) {
            throw TokenNotExpectedException.build(token, tokenTag);
        }
    }

    private void nextToken() throws EndOfInputException
    {
        token = lexer.getNext();
    }

    public void setVisitorCallee(VisitorCallee visitorCallee)
    {
        this.visitorCallee = visitorCallee;
    }
}
