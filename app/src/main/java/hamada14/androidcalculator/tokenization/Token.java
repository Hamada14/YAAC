package hamada14.androidcalculator.tokenization;

import hamada14.androidcalculator.calculator.CalculatorInput;

public class Token {

    private final String value;
    private final TokenType tokenType;

    public Token(final String value) {
        this.value = value;
        this.tokenType = TokenType.VALUE;
    }

    public Token(final char x) {
        this.value = String.valueOf(x);
        if (x == CalculatorInput.OPEN_BRACKET.getValue() ||
                x == CalculatorInput.CLOSED_BRACKET.getValue()) {
            this.tokenType = TokenType.BRACKET;
        } else if(CalculatorInput.toEnum(x).isOperator()){
            this.tokenType = TokenType.OPERATOR;
        } else {
            this.tokenType = TokenType.VALUE;
        }
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return tokenType;
    }

    public CalculatorInput getInputType() {
        return CalculatorInput.toEnum(value.charAt(0));
    }
}
