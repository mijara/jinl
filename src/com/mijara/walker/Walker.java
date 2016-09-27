package com.mijara.walker;

import com.mijara.engine.Context;

public abstract class Walker
{
    private Context context;

    public Walker(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return context;
    }
}
