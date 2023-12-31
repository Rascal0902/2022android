package org.techtown.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.RECEIVE_SMS)
                .onGranted(new Action<List<String>>() {
                    public void onAction(List<String> permissions){
                        showToast("허용된 권한 개수: " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    public void onAction(List<String> permissions){
                        showToast("허용된 권한 개수: " + permissions.size());
                    }
                })
                .start();
    }
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void plusClick(View v){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);

        TextView result = (TextView) findViewById(R.id.result);

        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        result.setText(Integer.toString(n1 + n2));
    }
    public void subClick(View v){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);

        TextView result = (TextView) findViewById(R.id.result);

        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        result.setText(Integer.toString(n1 - n2));
    }
    public void mulClick(View v){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);

        TextView result = (TextView) findViewById(R.id.result);

        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        result.setText(Integer.toString(n1 * n2));
    }
    public void divClick(View v){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);

        TextView result = (TextView) findViewById(R.id.result);

        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        result.setText(Integer.toString(n1 / n2));
    }
    public void exit(View v){
        finish();
    }
}