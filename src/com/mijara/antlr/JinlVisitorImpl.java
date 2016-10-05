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
        String returnType = ctx.type().IDENTIFIER().getText();
        Block block = (Block) visit(ctx.block());

        return new Function(name, null, Type.fromString(returnType), block);
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
}
