package com.mijara.antlr;

import com.mijara.ast.*;
import com.mijara.engine.Program;
import com.mijara.types.Type;

import java.util.ArrayList;

public class JinlVisitorImpl extends JinlBaseVisitor<Object>
{
    @Override
    public Object visitProgram(JinlParser.ProgramContext ctx)
    {
        Program program = new Program();

        for (JinlParser.FunctionContext functionContext : ctx.function()) {
            program.addFunction((Function) visit(functionContext));
        }

        return program;
    }

    @Override
    public Object visitFunction(JinlParser.FunctionContext ctx)
    {
        String name = ctx.FUNCTION_NAME().getText();
        Block block = (Block) visit(ctx.block());

        String version = ctx.version() == null ? null : ctx.version().IDENTIFIER().getText();
        String returnType = ctx.type() == null ? "void" : ctx.type().IDENTIFIER().getText();

        @SuppressWarnings("unchecked")
        ArrayList<Parameter> parameters = ctx.parameterList() == null ? null :
                (ArrayList<Parameter>) visit(ctx.parameterList());

        return new Function(name, version, parameters, Type.fromString(returnType), block);
    }

    @Override
    public Object visitParameterList(JinlParser.ParameterListContext ctx)
    {
        ArrayList<Parameter> parameters = new ArrayList<>();
        for (JinlParser.ParameterContext parameterContext : ctx.parameter()) {
            parameters.add((Parameter) visit(parameterContext));
        }

        return parameters;
    }

    @Override
    public Object visitParameter(JinlParser.ParameterContext ctx)
    {
        return new Parameter(Type.fromString(ctx.type().getText()), ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitBlock(JinlParser.BlockContext ctx)
    {
        Block block = new Block();
        for (JinlParser.StatementContext statementContext : ctx.statement()) {
            block.addStatement((Statement) visit(statementContext));
        }

        if (ctx.returnStatement() != null) {
            block.addStatement((Statement) visit(ctx.returnStatement()));
        }

        return block;
    }

    @Override
    public Object visitExpressionStatement(JinlParser.ExpressionStatementContext ctx)
    {
        return new ExpressionStatement((Expression) visit(ctx.expression()));
    }

    @Override
    public Object visitVarDecl(JinlParser.VarDeclContext ctx)
    {
        String variable = ctx.IDENTIFIER().getText();
        Expression expression = (Expression) visit(ctx.expression());
        return new VarDecl(variable, null, expression);
    }

    @Override
    public Object visitReturnStatement(JinlParser.ReturnStatementContext ctx)
    {
        return new Return((Expression) visit(ctx.expression()));
    }

    @Override
    public Object visitFunctionCall(JinlParser.FunctionCallContext ctx)
    {
        String name = ctx.FUNCTION_NAME().getText();

        ArrayList<Expression> arguments = new ArrayList<>();
        for (JinlParser.ExpressionContext expressionContext : ctx.expression()) {
            arguments.add((Expression) visit(expressionContext));
        }

        return new FunctionCall(name, arguments);
    }

    @Override
    public Object visitInteger(JinlParser.IntegerContext ctx)
    {
        return new IntegerNode(Integer.parseInt(ctx.getText()));
    }

    @Override
    public Object visitIdentifier(JinlParser.IdentifierContext ctx)
    {
        return new Identifier(ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitString(JinlParser.StringContext ctx)
    {
        return new StringNode(ctx.STRING().getText());
    }
}
