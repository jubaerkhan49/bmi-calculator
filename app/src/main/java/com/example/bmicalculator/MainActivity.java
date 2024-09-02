package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etWeight, etHeight;
    private Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BMI Calculator");
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
            double bmi = weight / (height * height);

            String bmiResult = String.format(getString(R.string.result_bmi), String.format("%.2f", bmi));
            String bmiSuggestion;

            if (bmi < 18.5) {
                bmiSuggestion = getString(R.string.suggestion_underweight);
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiSuggestion = getString(R.string.suggestion_normal);
            } else if (bmi >= 25 && bmi < 29.9) {
                bmiSuggestion = getString(R.string.suggestion_overweight);
            } else {
                bmiSuggestion = getString(R.string.suggestion_obese);
            }

            // Create an Intent to start ResultActivity
            Intent intent = new Intent(MainActivity.this, BmiResult.class);
            intent.putExtra("bmiResult", bmiResult);
            intent.putExtra("bmiSuggestion", bmiSuggestion);
            startActivity(intent);

        } else {
            // You can show a toast or a dialog to notify the user to enter all fields
        }
    }
}