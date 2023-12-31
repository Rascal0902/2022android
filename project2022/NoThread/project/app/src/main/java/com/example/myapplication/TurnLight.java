package com.example.myapplication;

import android.graphics.Color;
import android.os.Build;

class TurnLight{
    static int height,width;
    static float[][] Red = new float[2000][2000];
    static float[][] Green = new float[2000][2000];
    static float[][] Blue = new float[2000][2000];
    static float[][] newRed = new float[2000][2000];
    static float[][] newGreen = new float[2000][2000];
    static float[][] newBlue = new float[2000][2000];
    static float lightX,lightY,lightZ;
    static float lightR,lightG,lightB;
    static float power;
    static float[] Ambient ={(float)0.7,(float)0.7,(float)0.7};
    static void getinfo(){
        System.out.println("TurnLight : "+lightX +" "+lightY+" "+lightZ+" "+lightR+" "+lightG+" "+lightB+" ambient : "+Ambient[0]+" "+Ambient[1]+" "+Ambient[2]);
    }
    static Color ambient_color(int x, int y){
        float R,G,B;
        R = (int)(Red[x][y]*Ambient[0]);
        G = (int)(Green[x][y]*Ambient[1]);
        B = (int)(Blue[x][y]*Ambient[2]);
        Color newCol = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newCol = Color.valueOf(Color.rgb((float)(R/255), (float)(G/255), (float)(B/255)));
            return newCol;
        }
        return newCol;
    }
    static Color diffuse_color(int x,int y){
        float R,G,B;
        float[] lightVector = { lightX - x , lightY - y, lightZ };
        float mag = (float)Math.sqrt((lightX-x)*(lightX-x)+(lightY-y)*(lightY-y)+lightZ*lightZ);
        for(int i=0 ; i<3 ; i++ ){lightVector[i]/=mag;}
        float cosine = lightVector[2];
        float lambertfactor = Math.max(cosine, 0);
        float luminosity = 1/(1+mag*mag);
        R = Math.min(Red[x][y]*power*2*lightR*lambertfactor*luminosity,255);
        G = Math.min(Green[x][y]*power*2*lightG*lambertfactor*luminosity,255);
        B = Math.min(Blue[x][y]*power*2*lightB*lambertfactor*luminosity,255);
        Color newCol = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newCol = Color.valueOf(Color.rgb((float)(R/255), (float)(G/255), (float)(B/255)));
            return newCol;
        }
        return newCol;
    }
    static Color lambert_color(int x,int y){
        Color ambient = ambient_color(x,y);
        Color diffuse = diffuse_color(x,y);
        float finalR,finalG,finalB;
        Color newCol = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            finalR = (float) Math.min(((ambient.red() + diffuse.red()) * 255), 255);
            finalG = (float) Math.min(((ambient.green() + diffuse.green()) * 255), 255);
            finalB = (float) Math.min(((ambient.blue() + diffuse.blue()) * 255), 255);
            newCol = Color.valueOf(Color.rgb((float)(finalR/255), (float)(finalG/255), (float)(finalB/255)));
            return newCol;
        }
        return newCol;
    }
}