package com.mijara.tests;

import com.mijara.engine.Jinl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class JinlTests
{
    @Test
    public void testEmpty() throws FileNotFoundException
    {
        Integer integer = (Integer) Jinl.execute(new File("input/demo.jinl"));

        Assert.assertEquals(new Integer(1), integer);
    }
}
