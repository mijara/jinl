grammar Jinl;

@header {
package com.mijara.antlr;
}

program: function+;

function: FUNCTION_NAME '(' version? parameterList? ')' (':' type)? block END;

version: IDENTIFIER ';';

parameterList: (parameter ',')* parameter;
parameter: type IDENTIFIER;

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
    | string
    | functionCall
    ;

integer: INTEGER;

identifier: IDENTIFIER;

string: STRING;

functionCall: FUNCTION_NAME '(' expression* ')';

// LEXER
END:                'End';
VAR:                'var';
RETURN:             'return';

FUNCTION_NAME:      '@'+ STRONG_NAME        { setText(getText().substring(1)); };
STRONG_NAME:        ([A-Z][A-Za-z0-9]+);
IDENTIFIER:         [a-zA-Z]+;
INTEGER:            [0-9]+;
STRING:             '"' ~[\r\n"]*? '"'      { setText(getText().substring(1, getText().length() - 1)); };

WHITESPACE: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip;
