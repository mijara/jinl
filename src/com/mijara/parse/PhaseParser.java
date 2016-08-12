package com.mijara.parse;

import com.mijara.ast.FunctionAST;
import com.mijara.ast.ParameterAST;
import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.Lexer;
import com.mijara.tokens.Token;

import java.util.ArrayList;

public class PhaseParser implements Parser
{
    private VisitorCallee visitorCallee;
    private Lexer lexer;

    private Token token;

    public PhaseParser(Lexer lexer)
    {
        this.lexer = lexer;
    }

    @Override
    public void parse() throws EndOfInputException
    {
        nextToken();

        switch (token.getTag()) {
            case Token.FUNCTION_NAME:
                visitorCallee.visit(parseFunction());
                break;
        }
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

        expect('(');

        // eat '('
        nextToken();

        // parse parameterList.
        ArrayList<ParameterAST> parameters = new ArrayList<>();
        while (token.is(Token.ID)) {
            parameters.add(parseParameter());

            if (token.is(',')) {
                nextToken();
            }
        }

        expect(')');
        nextToken();

        expect(Token.END);

        return new FunctionAST(name, parameters);
    }

    private ParameterAST parseParameter() throws EndOfInputException
    {
        String type = token.toId().getValue();
        nextToken();

        expect(Token.ID);
        String name = token.toId().getValue();
        nextToken();

        return new ParameterAST(type, name);
    }

    private void expect(int tokenTag) throws EndOfInputException
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
