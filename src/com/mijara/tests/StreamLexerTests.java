package com.mijara.tests;

import com.mijara.lexer.Lexer;
import com.mijara.lexer.StreamLexer;
import com.mijara.tokens.*;
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

        Assert.assertEquals(Token.eofToken, lexer.getNext());
    }

    @Test
    public void testFunctionName()
    {
        setSource("@MyFunction");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(new FunctionNameToken("MyFunction"), lexer.getNext());
    }

    @Test
    public void testId()
    {
        setSource("myId");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(new IdToken("myId"), lexer.getNext());
    }

    @Test
    public void testSingleChars()
    {
        String chars = "();{}.";
        setSource(chars);

        Lexer lexer = new StreamLexer();

        Token token;
        int i = 0;
        while ((token = lexer.getNext()) != Token.eofToken) {
            Assert.assertEquals(new Token(chars.charAt(i)), token);
            i++;
        }
    }

    @Test
    public void testSimpleFunction()
    {
        setSource("@MyFunction (int a) End");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(new FunctionNameToken("MyFunction"), lexer.getNext());
        Assert.assertEquals(new Token('('), lexer.getNext());
        Assert.assertEquals(new IdToken("int"), lexer.getNext());
        Assert.assertEquals(new IdToken("a"), lexer.getNext());
        Assert.assertEquals(new Token(')'), lexer.getNext());
        Assert.assertEquals(Token.endToken, lexer.getNext());
    }

    @Test
    public void testMathSymbols()
    {
        setSource("<= >= <");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(Token.leqToken, lexer.getNext());
        Assert.assertEquals(Token.geqToken, lexer.getNext());
        Assert.assertEquals(new Token('<'), lexer.getNext());
    }

    @Test
    public void testVarAssignInteger()
    {
        setSource("var mySymbol:int = 42");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(Token.varToken, lexer.getNext());
        Assert.assertEquals(new IdToken("mySymbol"), lexer.getNext());
        Assert.assertEquals(new Token(':'), lexer.getNext());
        Assert.assertEquals(new IdToken("int"), lexer.getNext());
        Assert.assertEquals(new Token('='), lexer.getNext());
        Assert.assertEquals(new IntegerToken(42), lexer.getNext());
    }

    @Test
    public void testVarAssignFloat()
    {
        setSource("var mySymbol:float = 42.5f");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(Token.varToken, lexer.getNext());
        Assert.assertEquals(new IdToken("mySymbol"), lexer.getNext());
        Assert.assertEquals(new Token(':'), lexer.getNext());
        Assert.assertEquals(new IdToken("float"), lexer.getNext());
        Assert.assertEquals(new Token('='), lexer.getNext());
        Assert.assertEquals(new FloatToken(42.5f), lexer.getNext());
    }

    @Test
    public void testVarAssignDouble()
    {
        setSource("var mySymbol:double = 42.5");

        Lexer lexer = new StreamLexer();

        Assert.assertEquals(Token.varToken, lexer.getNext());
        Assert.assertEquals(new IdToken("mySymbol"), lexer.getNext());
        Assert.assertEquals(new Token(':'), lexer.getNext());
        Assert.assertEquals(new IdToken("double"), lexer.getNext());
        Assert.assertEquals(new Token('='), lexer.getNext());
        Assert.assertEquals(new DoubleToken(42.5), lexer.getNext());
        Assert.assertEquals(Token.eofToken, lexer.getNext());
    }

    private void setSource(String source)
    {
        System.setIn(new ByteArrayInputStream(source.getBytes()));
    }
}
