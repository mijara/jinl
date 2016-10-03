package com.mijara.builtins;

import com.mijara.ast.BuiltInFunction;
import com.mijara.ast.Parameter;
import com.mijara.types.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintFormatBuiltInFunction extends BuiltInFunction
{
    private static ArrayList<Parameter> parameters;

    static {
        parameters =  new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "a"));
    }

    /**
     * Creates a built in function node.
     */
    public PrintFormatBuiltInFunction()
    {
        super("Print", parameters, Type.getVoidType());
    }

    @Override
    public Object call(HashMap<String, Object> args)
    {
        System.out.println(args.get("a"));
        return null;
    }
}
