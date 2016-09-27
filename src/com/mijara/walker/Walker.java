package com.mijara.walker;

import com.mijara.engine.Context;

public interface Walker<T>
{
    void walk(T node);
}
