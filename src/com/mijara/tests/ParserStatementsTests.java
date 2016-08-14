package com.mijara.tests;

import com.mijara.ast.FunctionAST;
import com.mijara.ast.VarDeclAST;
import com.mijara.lexer.EndOfInputException;
import com.mijara.lexer.FakeLexer;
import com.mijara.lexer.Lexer;
import com.mijara.parse.Parser;
import com.mijara.parse.PhaseParser;
import com.mijara.parse.VisitorCallee;
import com.mijara.tokens.FunctionNameToken;
import com.mijara.tokens.IdToken;
import com.mijara.tokens.Token;
import com.mijara.types.Type;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ParserStatementsTests
{
    @Test
    public void testEmptyVarDecl()
    {
        Lexer lexer = FakeLexer.Builder.mainFunction(
                Token.varToken, new IdToken("someVar"), new Token(':'), new IdToken("float")
        );

        Parser parser = new PhaseParser(lexer);
        parser.setVisitorCallee(new VisitorCallee()
        {
            @Override
            public void visit(FunctionAST node)
            {

            }

            @Override
            public void visit(VarDeclAST node)
            {
                Assert.assertThat(node.getName(), is("someVar"));
                Assert.assertThat(node.getType(), is(Type.getFloatType()));
            }
        });

        try {
            parser.parse();
        } catch (EndOfInputException ignored) {

        }
    }
}
