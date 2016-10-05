grammar Jinl;

@header {
package com.mijara.antlr;
}

program: function+;

function: FUNCTION_NAME '(' ')' (':' type)? block END;

type: IDENTIFIER;

block: statement* returnStatement?;

// PARSER - Statements
statement:
    varDecl
    | expressionStatement
    ;

varDecl: VAR IDENTIFIER ('=' expression)?;

returnStatement: RETURN expression;

expressionStatement: expression;

// PARSER - Expressions
expression:
    integer
    | identifier
    | functionCall
    ;

integer: INTEGER;

identifier: IDENTIFIER;

functionCall: FUNCTION_NAME '(' expression* ')';

// LEXER
END:                'End';
VAR:                'var';
RETURN:             'return';

FUNCTION_NAME:     '@'+ STRONG_NAME        { setText(getText().substring(1)); };
STRONG_NAME:       ([A-Z][A-Za-z0-9]+);
IDENTIFIER:         [a-zA-Z]+;
INTEGER:            [0-9]+;

WHITESPACE: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip;
