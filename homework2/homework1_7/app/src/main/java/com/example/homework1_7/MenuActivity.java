package com.example.homework1_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onAddClicked(View v) {
        Button button = findViewById(R.id.textguide);
        Button button2 = findViewById(R.id.textguide2);
        Button button3 = findViewById(R.id.textguide3);
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
}