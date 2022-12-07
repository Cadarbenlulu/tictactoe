package com.example.tictactoe;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Result extends Dialog {

    private final String message;
    private final MainActivity mainActivity;

    public Result(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);
        Button restartButton = findViewById(R.id.restartButton);

        resultText.setText(message);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                dismiss();
            }
        });
    }
}