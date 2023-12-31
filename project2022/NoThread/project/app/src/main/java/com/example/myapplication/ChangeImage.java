package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

import java.io.IOException;

public class ChangeImage{
    public static int lightX[],lightY[],lightZ[],lightR[],lightG[],lightB[];
    public static float power[],ambient[];
    public static Bitmap beforeImage,afterImage ;
    public static int now=0;
    ChangeImage(){}
    static void setImage(Bitmap image){
        beforeImage = image;
        TurnLight.width = (int) beforeImage.getWidth();
        TurnLight.height = (int) beforeImage.getHeight();
    }
    static void saveImage(String base){
        saveActivity.bitmap = afterImage;
        saveActivity.savename = base;
    }
    static void update(){
        ImageInfo Info = new ImageInfo(beforeImage);
        MakeImage t = new MakeImage((int)beforeImage.getWidth(),(int)beforeImage.getHeight(),beforeImage.hasAlpha());
        for(int i=0;i<beforeImage.getWidth();i++){
            for(int j=0;j<beforeImage.getHeight();j++){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    MakeImage.setPixel(i,j, Color.valueOf(Color.rgb(ImageInfo.getRGB(i,j)[0],ImageInfo.getRGB(i,j)[1],ImageInfo.getRGB(i,j)[2])));
                }
            }
        }
         Bitmap temp=MakeImage.returnimage();
        for(int cnt=1; cnt<=now ; cnt++){
            Info = new ImageInfo(temp);
            for(int i=0;i<beforeImage.getWidth();i++){
                for(int j=0;j<beforeImage.getHeight();j++){
                    TurnLight.Red[i][j]= (float) ImageInfo.getRGB(i,j)[0];
                    TurnLight.Green[i][j]= (float) ImageInfo.getRGB(i,j)[1];
                    TurnLight.Blue[i][j]= (float) ImageInfo.getRGB(i,j)[2];
                }
            }
            TurnLight.lightX=lightX[cnt];TurnLight.lightY=lightY[cnt];TurnLight.lightZ=lightZ[cnt];
            TurnLight.lightR=lightR[cnt];TurnLight.lightG=lightG[cnt];TurnLight.lightB=lightB[cnt];
            float[] temparray = new float[3];
            for(int k=0 ;k<3; k++){
                temparray[k]=ambient[cnt];
            }
            TurnLight.power=power[cnt]; TurnLight.Ambient = temparray;
            //TurnLight.getinfo();
            for(int i=0;i<beforeImage.getWidth();i++){
                for(int j=0;j<beforeImage.getHeight();j++){
                    TurnLight.lambert_color(i, j);
                }
            }
            MakeImage tmp = new MakeImage((int)beforeImage.getWidth(),(int)beforeImage.getHeight(),beforeImage.hasAlpha());
            for(int i=0;i<beforeImage.getWidth();i++){
                for(int j=0;j<beforeImage.getHeight();j++){
                    Color col = TurnLight.lambert_color(i,j);
                    MakeImage.setPixel(i, j, col);
                }
            }
            temp = MakeImage.returnimage();
        }
        afterImage = temp;
    }
}


