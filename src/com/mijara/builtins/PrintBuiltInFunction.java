package com.mijara.builtins;

import com.mijara.ast.BuiltInFunction;
import com.mijara.ast.Parameter;
import com.mijara.types.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintBuiltInFunction extends BuiltInFunction
{
    /**
     * Parameters for this print function.
     */
    private static ArrayList<Parameter> parameters;

    static {
        parameters =  new ArrayList<>();
        parameters.add(new Parameter(Type.getIntType(), "value"));
    }

    /**
     * Creates a built in function node.
     */
    public PrintBuiltInFunction()
    {
        super("Print", null, parameters, Type.getVoidType());
    }

    @Override
    public Object call(HashMap<String, Object> args)
    {
        System.out.println(args.get("value"));

        return null;
    }
}
