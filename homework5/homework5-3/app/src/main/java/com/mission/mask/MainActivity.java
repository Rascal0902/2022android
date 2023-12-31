package com.mission.mask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLinearLayout;
    private String maskInfo = "";
    private final Handler handler = new Handler();

    static double lat;
    static double lng;
    static String mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLinearLayout = findViewById(R.id.main_linear_layout);
        new Thread(this::readAPI).start();
    }

    private void readAPI() {
        try {
            URL url = new URL("https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/sample.json");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            for(String i; (i = in.readLine()) != null; maskInfo += i)
                ;

            JSONObject maskInfoJSON = new JSONObject(maskInfo);
            int infoCount = maskInfoJSON.getInt("count");
            JSONArray jsonArray = maskInfoJSON.getJSONArray("stores");

            for (int i = 0; i < infoCount; i++) {
                try {
                    addOneItem(jsonArray.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addOneItem(JSONObject jsonObject) throws JSONException {
        View view = getOneItem();

        TextView name = view.findViewById(R.id.textView3);
        TextView remain = view.findViewById(R.id.textView4);
        TextView address = view.findViewById(R.id.textView5);

        String nameString = jsonObject.getString("name");
        String addressString = jsonObject.getString("addr");

        double lat = jsonObject.getDouble("lat");
        double lng = jsonObject.getDouble("lng");

        handler.post(() -> name.setTextColor(Color.BLACK));
        handler.post(() -> name.setText(nameString));
        switch (jsonObject.getString("remain_stat")) {
            case "plenty" :
                handler.post(() -> remain.setText("많음"));
                handler.post(() -> remain.setTextColor(Color.GREEN));
                break;
            case "some":
                handler.post(() -> remain.setText("적당"));
                handler.post(() -> remain.setTextColor(Color.YELLOW));
                break;
            case "few":
                handler.post(() -> remain.setText("적음"));
                handler.post(() -> remain.setTextColor(Color.RED));
                break;
            case "break":
            case "empty":
                handler.post(() -> remain.setText("없음"));
                handler.post(() -> remain.setTextColor(Color.BLACK));
                handler.post(() -> remain.setPaintFlags(remain.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG));
                break;
            default:
                System.out.println(nameString + "\n" + addressString);
                throw new JSONException("unsupported action");
        }
        handler.post(() -> address.setText(addressString));

        view.findViewById(R.id.button).setOnClickListener(l -> {
            MainActivity.lat = lat;
            MainActivity.lng = lng;
            MainActivity.mark = nameString;
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });
        handler.post(() -> mainLinearLayout.addView(view));
    }

    private View getOneItem() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_mask, null);
        view.setPadding(50, 50, 50, 50);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(50, 50, 50, 0);
        view.setLayoutParams(params);
        return view;
    }
}