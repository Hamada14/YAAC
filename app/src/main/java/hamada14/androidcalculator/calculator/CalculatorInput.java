package hamada14.androidcalculator.calculator;

import java.security.InvalidParameterException;

public enum CalculatorInput {
    ZERO('0'), ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
    EIGHT('8'), NINE('9'), DOT('.'), EXPONENT('^', 5), MOD('%', 4), MULTIPLY('*', 3),
    DIVIDE('/', 2), ADD('+', 1), SUBTRACT('-', 0), OPEN_BRACKET('('), CLOSED_BRACKET(')');

    private final char value;
    private final int precedence;
    private final boolean isOperator;

    CalculatorInput(final char value) {
        this.value = value;
        this.isOperator = false;
        this.precedence = -1;
    }

    CalculatorInput(final char value, final int precedence) {
        this.value = value;
        this.isOperator = true;
        this.precedence = precedence;
    }

    public Character getValue() {
        return value;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public static boolean isOperator(final char value) {
        CalculatorInput inputEnum = toEnum(value);
        if(inputEnum == null) {
            throw new InvalidParameterException();
        }
        return inputEnum.isOperator;
    }

    public int getPrecedence() {
        return precedence;
    }

    public static CalculatorInput toEnum(final char value) {
        for(CalculatorInput inputEnum: CalculatorInput.values()) {
            if(value == inputEnum.value) {
                return inputEnum;
            }
        }
        return null;
    }

    public static boolean isBracket(final char c) {
        CalculatorInput inputType = toEnum(c);
        return inputType == OPEN_BRACKET || inputType == CLOSED_BRACKET;
    }
}
