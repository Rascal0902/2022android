package com.example.gs20046csm;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

        LinearLayout container;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                EditText ifText = (EditText)findViewById(R.id.textView2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub1,container,true);
        }
        });
        }
}