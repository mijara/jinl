package com.mijara.tests;

import com.mijara.engine.Jinl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class JinlTests
{
    @Test
    public void testMainReturn() throws FileNotFoundException
    {
        Assert.assertEquals(5, Jinl.execute(new File("input/testMainReturn.jinl")));
    }
}
