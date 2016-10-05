// Generated from /Users/mijara/Projects/jinl/antlr/Jinl.g4 by ANTLR 4.5.3

package com.mijara.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JinlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JinlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JinlParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JinlParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(JinlParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(JinlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JinlParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(JinlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(JinlParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(JinlParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(JinlParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(JinlParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(JinlParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(JinlParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(JinlParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link JinlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(JinlParser.FunctionCallContext ctx);
}