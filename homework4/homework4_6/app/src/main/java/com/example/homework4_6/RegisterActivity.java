package com.example.homework4_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends Activity {

    private Button mSubmitButton;

    private EditText mName, mID, mPassword, mAge;

    private static String userName, userId, userPassword, userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mID = (EditText) findViewById(R.id.editText3);
        mPassword = (EditText) findViewById(R.id.editText4);
        mName = (EditText) findViewById(R.id.editText5);
        mAge = (EditText) findViewById(R.id.editText6);

        mSubmitButton=findViewById(R.id.button3);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
                userName = mName.getText().toString();
                userId = mID.getText().toString();
                userPassword = mPassword.getText().toString();
                userAge = mAge.getText().toString();

                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        System.out.println("!");
                        try{
                            System.out.println(response);
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class );
                                startActivity(intent);
                                finish();//액티비티를 종료시킴(회원등록 창을 닫음)

                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener=new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("에러 -> "+error.getMessage());
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                System.out.println(userId+ userPassword+ userName+ userAge);
                RegisterRequest registerRequest = new RegisterRequest(userId, userPassword, userName, userAge, responseListener,errorListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });
    }
}
