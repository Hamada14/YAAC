package hamada14.androidcalculator.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hamada14.androidcalculator.tokenization.Token;
import hamada14.androidcalculator.tokenization.TokenType;

public class ExpressionSolver {

    private static final int DECIMAL_SCALE = 3;

    public String solveExpression(final List<Token> tokens) {
        BigDecimal result = calculateResult(tokens);
        return result.setScale(DECIMAL_SCALE, RoundingMode.CEILING).toString();
    }

    private BigDecimal calculateResult(final List<Token> tokens) {
        List<Token> output = infixToPostfix(tokens);
        return evaluatePostfix(output);
    }

    private List<Token> infixToPostfix(final List<Token> tokens) {
        Stack<Token> st = new Stack<>();
        List<Token> output = new ArrayList<>();

        for(Token currentToken : tokens) {
            if(currentToken.getType() == TokenType.VALUE) {
                output.add(currentToken);
            } else if (currentToken.getType() == TokenType.BRACKET) {
                CalculatorInput inputEnum = currentToken.getInputType();
                if(inputEnum == CalculatorInput.OPEN_BRACKET) {
                    st.push(currentToken);
                } else {
                    while(!st.isEmpty() && st.peek().getInputType() != CalculatorInput.OPEN_BRACKET) {
                        output.add(st.pop());
                    }
                    if(!st.isEmpty() && st.peek().getInputType() == CalculatorInput.OPEN_BRACKET) {
                        throw new InvalidParameterException();
                    }
                }
            } else {
                while(!st.isEmpty() && currentToken.getInputType().getPrecedence() <=
                        st.peek().getInputType().getPrecedence()) {
                    output.add(st.pop());
                }
                st.push(currentToken);
            }
        }
        while(!st.isEmpty()) {
            output.add(st.pop());
        }
        return output;
    }

    private BigDecimal evaluatePostfix(final List<Token> postfixExpression) {
        Stack<BigDecimal> st = new Stack<>();
        for(Token currentToken : postfixExpression) {
            if(currentToken.getInputType().isOperator()) {
                if (st.size() < 2) {
                    throw new InvalidParameterException();
                }
                BigDecimal d1 = st.pop();
                BigDecimal d2 = st.pop();
                switch (currentToken.getInputType()) {
                    case ADD:
                        st.push(d1.add(d2));
                        break;
                    case SUBTRACT:
                        st.push(d2.subtract(d1));
                        break;
                    case MULTIPLY:
                        st.push(d1.multiply(d2));
                        break;
                    case DIVIDE:
                        st.push(d2.divide(d1, 3, RoundingMode.HALF_UP));
                        break;
                    case EXPONENT:
                        /*
                            @To-Do
                            Use This implementation https://arxiv.org/pdf/0908.3030v3.pdf
                         */
                        st.push(d2.pow(d1.intValue()));
                        break;
                    case MOD:
                        st.push(d2.remainder(d1));
                        break;
                }
            } else {
                st.push(new BigDecimal(currentToken.getValue()));
            }
        }
        if(st.size() != 1) {
            throw new InvalidParameterException();
        }
        return st.pop();
    }
}
