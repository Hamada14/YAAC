package hamada14.androidcalculator.tokenization;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

public class TokenizerUnitTest {

    @Test
    public void simpleCase1() {
        String sampleCase = "4+3-1";

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = tokenizer.tokenize(sampleCase);

        int expectedLength = 5;
        Assert.assertEquals(expectedLength, tokenList.size());

        Assert.assertEquals("4", tokenList.get(0).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(0).getType());


        Assert.assertEquals("+", tokenList.get(1).getValue());
        Assert.assertEquals(TokenType.OPERATOR, tokenList.get(1).getType());


        Assert.assertEquals("3", tokenList.get(2).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(2).getType());


        Assert.assertEquals("-", tokenList.get(3).getValue());
        Assert.assertEquals(TokenType.OPERATOR, tokenList.get(3).getType());


        Assert.assertEquals("1", tokenList.get(4).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(4).getType());}

    @Test
    public void simpleCase2() {
        String sampleCase = "4*3/1";

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = tokenizer.tokenize(sampleCase);

        int expectedLength = 5;
        Assert.assertEquals(expectedLength, tokenList.size());


        Assert.assertEquals("4", tokenList.get(0).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(0).getType());


        Assert.assertEquals("*", tokenList.get(1).getValue());
        Assert.assertEquals(TokenType.OPERATOR, tokenList.get(1).getType());


        Assert.assertEquals("3", tokenList.get(2).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(2).getType());


        Assert.assertEquals("/", tokenList.get(3).getValue());
        Assert.assertEquals(TokenType.OPERATOR, tokenList.get(3).getType());


        Assert.assertEquals("1", tokenList.get(4).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(4).getType());
    }

    @Test
    public void complexCase1() {
        String sampleCase = "4*3-(21)";

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = tokenizer.tokenize(sampleCase);

        int expectedLength = 7;
        Assert.assertEquals(expectedLength, tokenList.size());


        Assert.assertEquals("(", tokenList.get(4).getValue());
        Assert.assertEquals(TokenType.BRACKET, tokenList.get(4).getType());


        Assert.assertEquals("21", tokenList.get(5).getValue());
        Assert.assertEquals(TokenType.VALUE, tokenList.get(5).getType());


        Assert.assertEquals(")", tokenList.get(6).getValue());
        Assert.assertEquals(TokenType.BRACKET, tokenList.get(6).getType());

    }

    @Test
    public void complexCase2() {
        String sampleCase = "342*231%234+123";

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = tokenizer.tokenize(sampleCase);

        int expectedLength = 7;
        Assert.assertEquals(expectedLength, tokenList.size());
    }
}
