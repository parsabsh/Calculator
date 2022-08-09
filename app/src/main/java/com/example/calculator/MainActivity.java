package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.controller.ShuntingYardCalculator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ShuntingYardCalculator calculator = new ShuntingYardCalculator();

    String expression;
    TextView monitor;

    Button signChanger;
    Button backspace;
    Button equals;

    ArrayList<Button> inputButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        signChanger.setOnClickListener(view -> {
            if (expression.endsWith("-")) {
                expression = expression.substring(0, expression.length()-1);
            } else {
                expression += "-";
            }
            updateMonitor();
        });

        backspace.setOnClickListener(view -> {
            if (expression.length() <= 1) {
                expression = getString(R.string.zero);
            } else {
                expression = expression.substring(0, expression.length() - 1);
            }
            updateMonitor();
        });

        equals.setOnClickListener(view -> {
            expression = calculator.shuntingYard(expression);
            updateMonitor();
        });

        for (Button button : inputButtons) {
            button.setOnClickListener(view -> {
                if (button.getText().equals("+") || button.getText().equals("-") || button.getText().equals("×") || button.getText().equals("÷")) {
                    expression += " " + button.getText() + " ";
                } else {
                    expression += button.getText();
                }
                updateMonitor();
            });
        }
    }

    private void initButtons() {
        inputButtons.add(findViewById(R.id.open_parenthesis));
        inputButtons.add(findViewById(R.id.close_parenthesis));
        inputButtons.add(findViewById(R.id.division));
        inputButtons.add(findViewById(R.id.seven));
        inputButtons.add(findViewById(R.id.eight));
        inputButtons.add(findViewById(R.id.multiply));
        inputButtons.add(findViewById(R.id.four));
        inputButtons.add(findViewById(R.id.five));
        inputButtons.add(findViewById(R.id.six));
        inputButtons.add(findViewById(R.id.minus));
        inputButtons.add(findViewById(R.id.one));
        inputButtons.add(findViewById(R.id.two));
        inputButtons.add(findViewById(R.id.three));
        inputButtons.add(findViewById(R.id.plus));
        inputButtons.add(findViewById(R.id.zero));
        inputButtons.add(findViewById(R.id.dot));
        inputButtons.add(findViewById(R.id.open_parenthesis));
        inputButtons.add(findViewById(R.id.open_parenthesis));
        equals = findViewById(R.id.equals);
        backspace = findViewById(R.id.backspace);
        signChanger = findViewById(R.id.sign_changer);
        monitor = findViewById(R.id.monitorTextView);
        expression = getString(R.string.zero);
    }

    private void updateMonitor() {
        if (expression.startsWith(getString(R.string.zero))) {
            expression = expression.substring(1);
        }
        if (expression.length() == 0) {
            expression = getString(R.string.zero);
        }
        monitor.setText(expression);
    }
}