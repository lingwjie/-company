package com.example.bitmapmove;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        myview myview = (myview)findViewById(R.id.myview);
//        TranslateAnimation translateAnimation = new TranslateAnimation(0, -1360, 0, 0);
//        translateAnimation.setInterpolator(new LinearInterpolator());
//        translateAnimation.setFillAfter(true);
//        translateAnimation.setDuration(4000);
//        myview.startAnimation(translateAnimation);
    }
   
}
