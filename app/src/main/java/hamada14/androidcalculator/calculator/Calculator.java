package hamada14.androidcalculator.calculator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hamada14.androidcalculator.tokenization.Token;
import hamada14.androidcalculator.tokenization.Tokenizer;

public class Calculator {

    private final ExpressionSolver expressionSolver;
    private final List<Character> equation;

    private final static Set<Character> VALID_CHARACTERS;

    static {
        List<Character> validCharactersArray =
                Arrays.stream(CalculatorInput.values())
                        .map(CalculatorInput::getValue)
                        .collect(Collectors.toList());
        VALID_CHARACTERS = new HashSet<>(validCharactersArray);
    }

    private final Tokenizer tokenizer;

    public Calculator(final ExpressionSolver expressionSolver, final Tokenizer tokenizer) {
        this.expressionSolver = expressionSolver;
        this.tokenizer = tokenizer;
        equation = new ArrayList<>();
    }

    public String solveExpression() {
        List<Token> tokens = tokenizer.tokenize(getExpression());
        return expressionSolver.solveExpression(tokens);
    }

    public void addCharacter(CalculatorInput input) {
        char val = input.getValue();
        if (!validCharacter(val)) {
            throw new InvalidParameterException();
        }
        equation.add(val);
    }

    public void removeCharacter() {
        if (!equation.isEmpty()) {
            equation.remove(equation.size() - 1);
        }
    }

    public void reset() {
        equation.clear();
    }

    public String getExpression() {
        return equation.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private boolean validCharacter(final char x) {
        return VALID_CHARACTERS.contains(x);
    }
}
