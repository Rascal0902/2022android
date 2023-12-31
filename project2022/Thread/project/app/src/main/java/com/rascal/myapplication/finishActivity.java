package com.rascal.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rascal.myapplication.R;

public class finishActivity extends AppCompatActivity {
    Button finish;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        finish = (Button)findViewById(R.id.finishButton);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent();
                 setResult(RESULT_OK,intent);
                 finish();
            }
        });

    }
}

