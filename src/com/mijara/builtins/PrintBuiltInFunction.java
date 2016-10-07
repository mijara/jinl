package com.mijara.builtins;

import com.mijara.ast.BuiltInFunction;
import com.mijara.types.Type;


/**
 * The common print function.
 *
 * @author mijara
 */
public class PrintBuiltInFunction extends BuiltInFunction
{
    /**
     * Creates a built in function node.
     */
    public PrintBuiltInFunction()
    {
        super("Print", null, Type.getVoidType(), Type.getIntType());
    }

    @Override
    public Object call(Object... args)
    {
        System.out.println(args[0]);

        return null;
    }
}
