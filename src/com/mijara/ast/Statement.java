package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walker.StatementWalker;

public abstract class Statement
{
    public abstract Value accept(StatementWalker statementWalker);
}
