package com.mijara.parse;

import com.mijara.ast.FunctionAST;

/**
 * The pass callee is a callback used by the parser at certain
 * stages determined by it, as an example, the PhaseCallee will
 * call this every complete statement, while a CompletionParser
 * will call when complete processing stages are read.
 */
public interface VisitorCallee
{
    void visit(FunctionAST node);
}
