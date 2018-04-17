package hamada14.androidcalculator.calculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import hamada14.androidcalculator.tokenization.Token;

public class ExpressionSolverUnitTest {

    @Test
    public void simpleCase1() {
        List<Token> tokens = Arrays.asList(new Token('1'), new Token('+'), new Token('7'),
                new Token('*'), new Token('3'));

        ExpressionSolver solver = new ExpressionSolver();
        Assert.assertEquals("22.000", solver.solveExpression(tokens));
    }

    @Test
    public void complexCase() {
        List<Token> tokens = Arrays.asList(new Token('1'), new Token('-'), new Token('7'),
                new Token('/'), new Token('3'), new Token('^'), new Token('2'));

        ExpressionSolver solver = new ExpressionSolver();
        Assert.assertEquals("0.222", solver.solveExpression(tokens));
    }

    @Test
    public void complexCase2() {
        List<Token> tokens = Arrays.asList(new Token('1'), new Token('-'), new Token('7'),
                new Token('/'), new Token('('), new Token('3'), new Token('^'),
                new Token('2'), new Token(')'));

        ExpressionSolver solver = new ExpressionSolver();
        Assert.assertEquals("0.222", solver.solveExpression(tokens));
    }
}
