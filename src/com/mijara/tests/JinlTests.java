package com.mijara.tests;

import com.mijara.engine.Jinl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class JinlTests
{
    @Test
    public void testDemo() throws FileNotFoundException
    {
        Jinl.execute(new File("input/demo.jinl"));
    }

    @Test
    public void testMainReturn() throws FileNotFoundException
    {
        Assert.assertEquals(5, Jinl.execute(new File("input/testMainReturn.jinl")));
    }

    @Test
    public void testMainReturnString() throws FileNotFoundException
    {
        Assert.assertEquals("test", Jinl.execute(new File("input/testString.jinl")));
    }
}
