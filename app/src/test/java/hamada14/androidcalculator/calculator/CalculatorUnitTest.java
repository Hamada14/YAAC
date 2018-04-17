package hamada14.androidcalculator.calculator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import hamada14.androidcalculator.tokenization.Token;
import hamada14.androidcalculator.tokenization.Tokenizer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorUnitTest {

    @Mock
    private ExpressionSolver solver;

    @Mock
    private Tokenizer tokenizer;

    @InjectMocks
    private Calculator calculator;

    @Test
    public void simpleCase1() {
        calculator.addCharacter(CalculatorInput.FIVE);
        calculator.addCharacter(CalculatorInput.TWO);
        calculator.addCharacter(CalculatorInput.MULTIPLY);
        calculator.addCharacter(CalculatorInput.THREE);
        calculator.addCharacter(CalculatorInput.SUBTRACT);
        calculator.addCharacter(CalculatorInput.ONE);
        calculator.addCharacter(CalculatorInput.EIGHT);

        Assert.assertEquals("52*3-18", calculator.getExpression());

        calculator.removeCharacter();
        Assert.assertEquals("52*3-1", calculator.getExpression());
        calculator.removeCharacter();
        Assert.assertEquals("52*3-", calculator.getExpression());
        calculator.removeCharacter();
        Assert.assertEquals("52*3", calculator.getExpression());

        String intermedResult = "52*3";
        String expectedResult = "123.451";

        List<Token> tokens = Arrays.asList(new Token('5'));
        when(tokenizer.tokenize(intermedResult)).thenReturn(tokens);
        when(solver.solveExpression(tokens)).thenReturn(expectedResult);

        Assert.assertEquals(expectedResult, calculator.solveExpression());

        verify(tokenizer).tokenize(intermedResult);
        verify(solver).solveExpression(tokens);


        calculator.reset();
        Assert.assertEquals("", calculator.getExpression());
    }
}
