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

    private final static Set<Character> VALID_CHARACTERS;

    static {
        List<Character> validCharactersArray =
                Arrays.stream(CalculatorInput.values())
                        .map(CalculatorInput::getValue)
                        .collect(Collectors.toList());
        VALID_CHARACTERS = new HashSet<>(validCharactersArray);
    }

    private final ExpressionSolver expressionSolver;
    private final List<Character> equation;
    private final Tokenizer tokenizer;

    public Calculator(final ExpressionSolver expressionSolver, final Tokenizer tokenizer) {
        this.expressionSolver = expressionSolver;
        this.tokenizer = tokenizer;
        equation = new ArrayList<>();
    }

    public String solveExpression() {
        List<Token> tokens = tokenizer.tokenize(getExpression());
        String result = expressionSolver.solveExpression(tokens);
        result = normalizeResult(result);
        equation.clear();
        for (int i = 0; i < result.length(); i++)
            equation.add(result.charAt(i));
        return result;
    }

    public String getExpression() {
        return equation.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private String normalizeResult(String denormalizedResult) {
        String frac = denormalizedResult.substring(denormalizedResult.length() - 4);
        int i = denormalizedResult.length() - 1;
        while (denormalizedResult.charAt(i) == '0') {
            i--;
        }
        if (denormalizedResult.charAt(i) == '.')
            i--;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j <= i; j++)
            sb.append(denormalizedResult.charAt(j));
        return sb.toString();
    }

    public void addCharacter(CalculatorInput input) {
        char val = input.getValue();
        if (!validCharacter(val)) {
            throw new InvalidParameterException();
        }
        equation.add(val);
    }

    private boolean validCharacter(final char x) {
        return VALID_CHARACTERS.contains(x);
    }

    public void removeCharacter() {
        if (!equation.isEmpty()) {
            equation.remove(equation.size() - 1);
        }
    }

    public void reset() {
        equation.clear();
    }
}
