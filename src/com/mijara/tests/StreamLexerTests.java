package com.mijara.tests;

import com.mijara.lexer.Lexer;
import com.mijara.lexer.StreamLexer;
import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class StreamLexerTests
{
    @Test
    public void testEOF()
    {
        setSource("");

        Lexer lexer = new StreamLexer();
        Token token = lexer.getNext();

        Assert.assertEquals(token, Token.eofToken);
    }

    @Test
    public void testFunctionName()
    {
        setSource("@MyFunction");

        Lexer lexer = new StreamLexer();
        Token token = lexer.getNext();

        Assert.assertEquals(new FunctionNameToken("MyFunction"), token);
    }

    private void setSource(String source)
    {
        System.setIn(new ByteArrayInputStream(source.getBytes()));
    }
}
