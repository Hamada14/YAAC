package hamada14.androidcalculator.tokenization;

import junit.framework.Assert;

import org.junit.Test;

public class TokenUnitTest {

    @Test
    public void singleDigitNumber() {
        char num1 = '0';
        char num2 = '5';

        Token singleDigit = new Token(num1);
        Token singleDigit2 = new Token(num2);

        Assert.assertEquals(String.valueOf(num1), singleDigit.getValue());
        Assert.assertEquals(String.valueOf(num2), singleDigit2.getValue());

        Assert.assertEquals(TokenType.VALUE, singleDigit.getType());
        Assert.assertEquals(TokenType.VALUE, singleDigit2.getType());
    }

    @Test
    public void multipleDigitNumber() {
        String num1 = "10";
        String num2 = "434";

        Token multipleDigit = new Token(num1);
        Token multipleDigit2 = new Token(num2);

        Assert.assertEquals(num1, multipleDigit.getValue());
        Assert.assertEquals(num2, multipleDigit2.getValue());

        Assert.assertEquals(TokenType.VALUE, multipleDigit.getType());
        Assert.assertEquals(TokenType.VALUE, multipleDigit2.getType());
    }

    @Test
    public void operatorCharacter() {
        char operatorChar = '+';

        Token op = new Token(operatorChar);

        Assert.assertEquals(String.valueOf(operatorChar), op.getValue());
        Assert.assertEquals(TokenType.OPERATOR, op.getType());
    }

    @Test
    public void bracketCharacter() {
        char openBracket = '(';
        char closedBracket = ')';

        Token openBracketToken = new Token(openBracket);
        Token closedBracketToken = new Token(closedBracket);

        Assert.assertEquals(String.valueOf(openBracket), openBracketToken.getValue());
        Assert.assertEquals(String.valueOf(closedBracket), closedBracketToken.getValue());

        Assert.assertEquals(TokenType.BRACKET, openBracketToken.getType());
        Assert.assertEquals(TokenType.BRACKET, closedBracketToken.getType());
    }
}
