package hamada14.androidcalculator.tokenization;

import java.util.ArrayList;
import java.util.List;

import hamada14.androidcalculator.calculator.CalculatorInput;

public class Tokenizer {

    public List<Token> tokenize(final String expression) {
        List<Token> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (CalculatorInput.isOperator(expression.charAt(i)) || CalculatorInput.isBracket(expression.charAt(i))) {
                result.add(new Token(expression.charAt(i)));
            } else {
                int j = i + 1;
                while (j < expression.length() && isNumberCharacter(expression.charAt(j))) {
                    j++;
                }
                result.add(new Token(expression.substring(i, j)));
                i = j - 1;
            }
        }
        return result;
    }

    private boolean isNumberCharacter(char x) {
        if ((x >= '0' && x <= '9') || x == '.') {
            return true;
        }
        return false;
    }
}
