package com.sxs.chengyupoemgame.common;

import com.sxs.chengyupoemgame.main.MainActivity;
import com.sxs.chengyupoemgame.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class Splash extends Activity {   


    private final int SPLASH_DISPLAY_LENGHT = 1500; //�ӳ�����

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
         @Override
         public void run() {
             Intent mainIntent = new Intent(Splash.this,MainActivity.class);
             Splash.this.startActivity(mainIntent);
             //��һ������Ϊ����ʱ����Ч�����ڶ�������Ϊ�˳�ʱ����Ч��  
             overridePendingTransition(R.anim.fade, R.anim.hold);  
             Splash.this.finish();
         }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
