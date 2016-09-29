package com.mijara.ast;

import com.mijara.engine.Value;
import com.mijara.walkers.ExpressionWalker;

/**
 * Describes an identifier reference, as in:
 *
 * var someVariable = identifier
 *
 * @author mijara
 */
public class Identifier extends ValueExpression<String>
{
    /**
     * {@inheritDoc}
     */
    public Identifier(String value)
    {
        super(value);
    }

    @Override
    public Value accept(ExpressionWalker expressionWalker)
    {
        return expressionWalker.walk(this);
    }
}
