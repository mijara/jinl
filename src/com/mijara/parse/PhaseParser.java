package com.mijara.parse;

import com.mijara.ast.*;
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
        while (true) {
            switch (token.getTag()) {
                case Token.FUNCTION_NAME:
                    visitorCallee.visit(parseFunction());
                    continue;

                default:
                    return;
            }
        }
    }

    /**
     * function : FUNCTION_NAME '(' [ ID ';'] parameterList ')' [ ':' typeName]
     * block
     * END
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
        nextToken(); // eat ')'

        Type returnType = parseSmartType();

        // parse block.
        parseBlock();

        // function end.
        assertToken(Token.END);
        nextToken(); // eat END.

        return new FunctionAST(name, parameters, returnType);
    }

    private void parseBlock() throws EndOfInputException
    {
        BlockAST block = new BlockAST();

        while (true) {
            switch (token.getTag()) {
                case Token.VAR:
                    VarDeclAST node = parseVarDecl();
                    block.addStatement(node);
                    visitorCallee.visit(node);
                    continue;

                default:
                    return;
            }
        }
    }

    /**
     * varDecl : VAR ID ':' typeName
     */
    private VarDeclAST parseVarDecl() throws EndOfInputException
    {
        assertToken(Token.VAR);
        nextToken(); // eat VAR.

        assertToken(Token.ID);
        String name = token.toId().getValue();
        nextToken(); // eat name.

        Type type = parseSmartType();

        return new VarDeclAST(name, type);
    }

    /**
     * Parses an smart type.
     * <p>
     * A smart type refers to any type that can be left blank in the source
     * code, such as a return type (in which case the type is VOID), or in a
     * variable declaration. where the type is inferred from the initial value.
     *
     * @return the type or null.
     */
    private Type parseSmartType() throws EndOfInputException
    {
        if (token.is(':')) {
            nextToken(); // eat ':'

            assertToken(Token.ID);
            Type type = Type.fromString(token.toId().getValue());
            nextToken(); // eat type.

            return type;
        }

        return null;
    }

    private ParameterAST parseParameter() throws EndOfInputException
    {
        assertToken(Token.ID);
        Type type = Type.fromString(token.toId().getValue());
        nextToken(); // eat type.

        assertToken(Token.ID);
        String name = token.toId().getValue();
        nextToken(); // eat name.

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
