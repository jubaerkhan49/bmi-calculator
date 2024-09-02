package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BmiResult extends AppCompatActivity {

    private TextView tvBmiResult, tvBmiSuggestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BMI Calculator");

        tvBmiResult = findViewById(R.id.tvBmiResult);
        tvBmiSuggestion = findViewById(R.id.tvBmiSuggestion);

        // Get the intent and extract the data
        Intent intent = getIntent();
        String bmiResult = intent.getStringExtra("bmiResult");
        String bmiSuggestion = intent.getStringExtra("bmiSuggestion");

        // Set the BMI result and suggestion
        tvBmiResult.setText(bmiResult);
        tvBmiSuggestion.setText(bmiSuggestion);
    }
}