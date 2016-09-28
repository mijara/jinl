package com.mijara.parser;

import com.mijara.ast.*;
import com.mijara.engine.Program;
import com.mijara.lexer.Lexer;
import com.mijara.tokens.Token;
import com.mijara.types.Type;
import com.mijara.utils.Validate;

import java.util.ArrayList;

/**
 * Uses the recursive descent parser strategy to match a grammar.
 */
public class RecursiveDescentParser implements Parser
{
    /**
     * Some lexer implementation.
     */
    private Lexer lexer;

    /**
     * Current token.
     */
    private Token token;

    /**
     * Used to store AST and metadata.
     */
    private Program program;

    /**
     * Creates a new recursive descent parser by injecting the lexer and program.
     *
     * @param lexer the lexer to use.
     * @param program the program to store information to.
     */
    public RecursiveDescentParser(Lexer lexer, Program program)
    {
        this.lexer = Validate.notNull(lexer);
        this.program = Validate.notNull(program);

        nextToken();
    }

    @Override
    public void parse()
    {
        while (true) {
            switch (token.getTag()) {
                case Token.FUNCTION_NAME:
                    program.addFunction(parseFunction());
                    continue;

                case Token.EOF:
                    return;

                default:
                    throw new ParserError("Unexpected token received: " + token.getTag());
            }
        }
    }

    /**
     * function : FUNCTION_NAME '(' [ ID ';'] parameterList ')' [ ':' typeName]
     * block
     * END
     *
     * @return the AST node.
     */
    private Function parseFunction()
    {
        String name = token.toFunctionName().getValue();

        nextToken();

        assertToken('(');

        // eat '('
        nextToken();

        // parse parameterList.
        ArrayList<Parameter> parameters = new ArrayList<>();
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

        // parser block.
        Block block = parseBlock();

        // function end.
        assertToken(Token.END);
        nextToken(); // eat END.

        return new Function(name, parameters, returnType, block);
    }

    /**
     * block : statement block
     *
     * @return the matched block.
     */
    private Block parseBlock()
    {
        Block block = new Block();

        while (true) {
            switch (token.getTag()) {
                case Token.VAR:
                    block.addStatement(parseVarDecl());
                    continue;

                case Token.ID:
                    block.addStatement(parseAssignment());
                    continue;

                case Token.FUNCTION_NAME:
                    block.addStatement(new ExpressionStatement(parseFunctionCall()));
                    continue;

                case Token.RETURN:
                    block.addStatement(parseReturn());
                    continue;

                default:
                    // try to parse an expression.
                    Expression expression = parseExpression();
                    if (expression != null) {
                        block.addStatement(new ExpressionStatement(expression));
                        continue;
                    }

                    return block;
            }
        }
    }

    /**
     * @return the return statement node.
     */
    private Statement parseReturn()
    {
        nextToken(); // eat RETURN.

        Expression expression = parseExpression();
        if (expression == null) {
            throw new ParserError("Expected expression after return keyword.");
        }

        return new Return(expression);
    }

    /**
     * functionName '(' parameterList ')'
     *
     * @return the function call expression node.
     */
    private Expression parseFunctionCall()
    {
        String name = token.toFunctionName().getValue();
        nextToken(); // eat name.

        assertToken('(');
        nextToken(); // eat '('.

        ArrayList<Expression> args = new ArrayList<>();
        while (!token.is(')')) {
            Expression expression = parseExpression();
            args.add(expression);
        }

        assertToken(')');
        nextToken(); // eat ')'.

        return new FunctionCall(name, args);
    }

    /**
     * VAR name '=' expression
     *
     * @return the assignment node.
     */
    private Statement parseAssignment()
    {
        String variable = token.toId().getValue();
        nextToken(); // eat name.

        assertToken('=');
        nextToken(); // eat '='.

        // required value expression.
        Expression value = parseExpression();
        if (value == null) {
            throw new ParserError("Expression expected.");
        }

        return new Assignment(variable, value);
    }

    /**
     * varDecl : VAR ID ':' typeName
     *         | VAR ID ':' typeName '=' expression
     *         | VAR ID '=' expression
     *
     * @return the variable declaration node.
     */
    private VarDecl parseVarDecl()
    {
        assertToken(Token.VAR);
        nextToken(); // eat VAR.

        assertToken(Token.ID);
        String name = token.toId().getValue();
        nextToken(); // eat name.

        Type type = parseSmartType();

        Expression initial = null;
        if (token.is('=')) {
            nextToken(); // eat '='

            // required initial expression.
            initial = parseExpression();
            if (initial == null) {
                throw new ParserError("Initial value expected after '='.");
            }
        }

        return new VarDecl(name, type, initial);
    }

    /**
     * Parse an {@link Expression}.
     *
     * @return the expression object or null if nothing matches.
     */
    private Expression parseExpression()
    {
        switch (token.getTag()) {
            case Token.INTEGER:
                return parseInteger();
            case Token.FLOAT:
                return parseFloat();
        }

        return null;
    }

    /**
     * @return the float wrapped.
     */
    private FloatNode parseFloat()
    {
        float value = token.toFloat().getValue();
        nextToken(); // eat float.
        return new FloatNode(value);
    }

    /**
     * @return the integer wrapped.
     */
    private IntegerNode parseInteger()
    {
        int value = token.toInt().getValue();
        nextToken(); // eat int.
        return new IntegerNode(value);
    }

    /**
     * Parses an smart type.
     * <p>
     * A smart type refers to any type that can be left blank in the source
     * code, such as a return type (in which case the type is VOID), or in a
     * variable declaration, where the type is inferred from the initial value.
     *
     * @return the type or null.
     */
    private Type parseSmartType()
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

    /**
     * @return the parameter node.
     */
    private Parameter parseParameter()
    {
        assertToken(Token.ID);
        Type type = Type.fromString(token.toId().getValue());
        nextToken(); // eat type.

        assertToken(Token.ID);
        String name = token.toId().getValue();
        nextToken(); // eat name.

        return new Parameter(type, name);
    }

    /**
     * Asserts that the current token has some tag.
     *
     * @param tokenTag code to assert.
     */
    private void assertToken(int tokenTag)
    {
        if (!token.is(tokenTag)) {
            throw TokenNotExpectedException.build(token, tokenTag);
        }
    }

    /**
     * Retrieves the next token from the lexer.
     */
    private void nextToken()
    {
        token = lexer.getNext();
    }

    @Override
    public Program getProgram()
    {
        return program;
    }
}
