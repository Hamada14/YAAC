package hamada14.androidcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hamada14.androidcalculator.calculator.Calculator;
import hamada14.androidcalculator.calculator.CalculatorInput;
import hamada14.androidcalculator.calculator.ExpressionSolver;
import hamada14.androidcalculator.tokenization.Tokenizer;

public class MainActivity extends AppCompatActivity {

    private static final String INVALID_EXPRESSION = "Invalid Expression";

    private Calculator calculator;
    private TextView expressionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator(new ExpressionSolver(), new Tokenizer());
        expressionView = findViewById(R.id.text_panel);

        initializeButtons();
    }

    private void initializeButtons() {
        int[] buttonResources = new int[]{
                R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three,
                R.id.button_four, R.id.button_five, R.id.button_six, R.id.button_seven,
                R.id.button_eight, R.id.button_nine, R.id.button_open_bracket, R.id.button_closed_bracket,
                R.id.button_addition, R.id.button_subtraction, R.id.button_multiply,
                R.id.button_divide, R.id.button_mod, R.id.button_exponent, R.id.button_dot
        };
        CalculatorInput[] inputs = new CalculatorInput[]{
                CalculatorInput.ZERO, CalculatorInput.ONE, CalculatorInput.TWO, CalculatorInput.THREE,
                CalculatorInput.FOUR, CalculatorInput.FIVE, CalculatorInput.SIX, CalculatorInput.SEVEN,
                CalculatorInput.EIGHT, CalculatorInput.NINE, CalculatorInput.OPEN_BRACKET,
                CalculatorInput.CLOSED_BRACKET, CalculatorInput.ADD, CalculatorInput.SUBTRACT,
                CalculatorInput.MULTIPLY, CalculatorInput.DIVIDE, CalculatorInput.MOD,
                CalculatorInput.EXPONENT, CalculatorInput.DOT
        };

        for (int i = 0; i < buttonResources.length; i++)
            attachInput(inputs[i], buttonResources[i]);

        Button backButton = findViewById(R.id.button_backspace);
        backButton.setOnClickListener((View v) -> {
            calculator.removeCharacter();
            expressionView.setText(calculator.getExpression());
            expressionView.setError(null);
        });

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener((View v) -> {
            calculator.reset();
            expressionView.setText(calculator.getExpression());
            expressionView.setError(null);
        });

        Button equalButton = findViewById(R.id.button_equal);
        equalButton.setOnClickListener((View v) -> {
            try {
                expressionView.setText(calculator.solveExpression());
            } catch (Exception e) {
                expressionView.setError(INVALID_EXPRESSION);
            }
        });
    }

    private void attachInput(final CalculatorInput input, final int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener((View v) -> {
            calculator.addCharacter(input);
            expressionView.setText(calculator.getExpression());
            expressionView.setError(null);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
