package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"onReceive()메서드 호출됨");
        Bundle bundle = intent.getExtras(); // 인텐트에서 Bundle 객체 가져오기
        SmsMessage[] messages = parseSmsMessage(bundle); // parseSmsMessage() 메서드 호출

        if(messages != null && messages.length>0){
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG,"SMS sender : "+sender);

            String contents = messages[0].getMessageBody();
            Log.i(TAG, "SMS contents : " + contents);

            Date receiveDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG,"SMS received data : "+receiveDate.toString());

        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){

        Object[] objs = (Object[]) bundle.get("pdus"); //Bundle 객체의 부가 데이터 중 pdus 가져오기
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i = 0 ; i < smsCount ; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format =bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[]) objs[i],format);
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }

        }

        return messages;

    }
}
