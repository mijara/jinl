package com.mijara.walker;

import com.mijara.engine.Context;
import com.mijara.utils.Validate;

/**
 * A walker should be used to interpret the AST from the parser. There should be one for each
 * kind of construct in the language, and shall use the visitor pattern when dealing with multiple
 * subtypes of construct (for example: statement, expression).
 *
 * A walker could spawn sub walkers to delegate the workload.
 *
 * @author mijara
 */
public abstract class Walker
{
    /**
     * The main context of the program.
     */
    private Context context;

    /**
     * Creates a walker fulfilling the dependency injection.
     *
     * @param context the context to use when walking.
     */
    public Walker(Context context)
    {
        this.context = Validate.notNull(context);
    }

    /**
     * @return the main context of the program.
     */
    public Context getContext()
    {
        return context;
    }
}
