package com.example.myapplication;
import android.graphics.Bitmap;
import android.media.Image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

public class RootController{
    static int selected=0,now=0,minselected=0,maxselected=9;
    static Bitmap beforeImage;
    public static void initialize(){
        ChangeImage.lightX = new int[10];ChangeImage.lightY = new int[10];ChangeImage.lightZ = new int[10];
        ChangeImage.lightR = new int[10];ChangeImage.lightG = new int[10];ChangeImage.lightB = new int[10];
        ChangeImage.power = new float[10];ChangeImage.ambient = new float[10];
        for(int i=0 ; i<10; i++){
            ChangeImage.lightX[i]=150;ChangeImage.lightY[i]=150;ChangeImage.lightZ[i]=100000;
            ChangeImage.lightR[i]=255;ChangeImage.lightG[i]=255;ChangeImage.lightB[i]=255;
            ChangeImage.power[i] = 0;ChangeImage.ambient[i] = 1;
        }
    }
    public static void settingBtn(){
        setting();
    }
    public static void setting(){
        if(selected == 0){(new setdataFragmentActivity()).nothingselected();}
        (new setdataFragmentActivity()).update();
        System.out.println();
    }
    public void loadBtn(){
        ChangeImage.setImage(beforeImage);
        ChangeImage.update();
        System.out.println("load");
    }
    static String savename = "convertedpicture";
    public static void saveBtn(){
        ChangeImage.saveImage(savename);
        System.out.println("save");
    }
}