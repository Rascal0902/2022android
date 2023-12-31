package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

public class setdataFragmentActivity extends Fragment{
    EditText lightx,lighty,lightz;
    SeekBar power,lightr,lightg,lightb,ambient;
    Button lightposition;
    static ViewGroup rootview;
    static Boolean init = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = (ViewGroup)inflater.inflate(R.layout.setdatafragment,container,false);
        return rootview;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        lightposition = (Button) rootview.findViewById(R.id.lightposition); // 76 240  //ToDo 빛 위치 맞추기
        if(init) {
            setinfo();
            init = false;
        }
        lightx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {ChangeImage.lightX[RootController.selected] = Integer.parseInt(String.valueOf(lightx.getText()));update();}
        });
        lighty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {ChangeImage.lightY[RootController.selected] = Integer.parseInt(String.valueOf(lighty.getText()));update();}
        });
        lightz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {ChangeImage.lightZ[RootController.selected] = Integer.parseInt(String.valueOf(lightz.getText()));update();}
        });
        power.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {ChangeImage.power[RootController.selected] = power.getProgress();update();
                System.out.println(power.getProgress());}
        });
        lightr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {ChangeImage.lightR[RootController.selected] = lightr.getProgress() * 255 / 100;update();
                System.out.println(lightr.getProgress() * 255 / 100);}
        });
        lightg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {ChangeImage.lightG[RootController.selected] = lightg.getProgress() * 255 / 100;update();
                System.out.println(lightg.getProgress() * 255 / 100);}
        });
        lightb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {ChangeImage.lightB[RootController.selected] = lightb.getProgress() * 255 / 100;update();
                System.out.println(lightb.getProgress() * 255 / 100);}
        });
        ambient.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {ChangeImage.ambient[RootController.selected] = (float)(ambient.getProgress()/100.0);update();
                System.out.println("############"+ChangeImage.ambient[RootController.selected]);}
        });
    }
    public void update(){
        try{
            ChangeImage.update();
            System.out.println("show");
        }catch(NullPointerException e){
            System.out.println("no image loaded");
        }
    }
    public void nothingselected(){
        System.out.println("nothing selected!!");
        lightx.setText("");lighty.setText("");lightz.setText("");
        lightr.setProgress(0);lightg.setProgress(0);lightb.setProgress(0);
        power.setProgress(0);ambient.setProgress(0);
    }
    @SuppressLint("SetTextI18n")
    public void setinfo() {
        lightx = (EditText) rootview.findViewById(R.id.lightx);
        lighty = (EditText) rootview.findViewById(R.id.lighty);
        lightz = (EditText) rootview.findViewById(R.id.lightz);

        power = (SeekBar) rootview.findViewById(R.id.power);
        lightr = (SeekBar) rootview.findViewById(R.id.lightr);
        lightg = (SeekBar) rootview.findViewById(R.id.lightg);
        lightb = (SeekBar) rootview.findViewById(R.id.lightb);
        ambient = (SeekBar) rootview.findViewById(R.id.ambient);

        System.out.println("I have entered");
        System.out.println(RootController.selected);
        if (RootController.selected != 0) {
            System.out.println(ChangeImage.lightX[RootController.selected]);
            lightx.setText(Integer.toString(ChangeImage.lightX[RootController.selected]));
            lighty.setText(Integer.toString(ChangeImage.lightY[RootController.selected]));
            lightz.setText(Integer.toString(ChangeImage.lightZ[RootController.selected]));
            lightr.setProgress(ChangeImage.lightR[RootController.selected] * 100 / 255);
            lightg.setProgress(ChangeImage.lightG[RootController.selected] * 100 / 255);
            lightb.setProgress(ChangeImage.lightB[RootController.selected] * 100 / 255);
            power.setProgress((int) ChangeImage.power[RootController.selected]);
            ambient.setProgress((int) (ChangeImage.ambient[RootController.selected]*100));
        } else{
            lightx.setText("");
            lighty.setText("");
            lightz.setText("");
            lightr.setProgress(0);
            lightg.setProgress(0);
            lightb.setProgress(0);
            power.setProgress(0);
            ambient.setProgress(0);
        }
    }
}
