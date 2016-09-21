package com.mijara.engine.walker;

import com.mijara.engine.Context;

public interface Walker<T>
{
    void walk(Context context, T node);
}
