package com.example.water;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	public static boolean flag = true;
	int i = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Button start = new Button(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout rot = (RelativeLayout)findViewById(R.id.main);
        final ImageView [] water = {new ImageView(this),new ImageView(this)};
        water[0].setImageResource(R.drawable.waterleft);
        water[1].setImageResource(R.drawable.waterleft);
        rot.addView(water[0]);
        rot.addView(water[1]);
        final ImageView water1 = new ImageView(this);
        water1.setX(1020);
        water1.setY(300);
        water1.setImageResource(R.drawable.waterleft);
        /*final ImageView water2 = new ImageView(this);
        rot.addView(water1);
        rot.addView(water2);
        
        water2.setX(1020);
        water2.setY(300);
        
        water2.setBackgroundResource(R.anim.water);*/
        final TranslateAnimation animation = new TranslateAnimation(0, -1100, 0, 0);
        animation.setDuration(8000);
        animation.setFillAfter(true);
        water[0].setX(0);
        water[0].setY(300);
        water[1].setX(1020);
        water[1].setY(300);
        water[0].startAnimation(animation);
        water[1].startAnimation(animation);        
        animation.setInterpolator(new LinearInterpolator());      
        
        rot.addView(start);
        start.setOnClickListener(new OnClickListener() {        	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				water1.startAnimation(animation);
				}
		});
        boolean flag = true;
        int sum=2;
        water waterThread = new water();
        while (flag) {
			
				waterThread.start();				
					start.performClick();
				
			
				
			
		}
        
    }
   
   }
       /* 
        final ImageView water3 = new ImageView(this);
        
//        
//        rot.addView(water2);
//        rot.addView(water3);
        water1.setImageResource(R.drawable.waterleft);
        water2.setImageResource(R.drawable.waterleft);
        water3.setImageResource(R.drawable.waterleft);
        water[0].setImageResource(R.drawable.waterleft);
        water[1].setImageResource(R.drawable.waterleft);
       
        
        water3.setX(1020);
        water3.setY(100);
        water[0].setX(1020);
        water[0].setY(200);
        water[1].setX(1020);
        water[1].setY(100);
        final TranslateAnimation animation = new TranslateAnimation(0, -1100, 0, 0);
        animation.setDuration(8000);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        final TranslateAnimation animation1 = new TranslateAnimation(0, -2100, 0, 0);
        animation1.setDuration(15000);
        final TranslateAnimation animation2 = new TranslateAnimation(0, -1100, 0, 0);
        animation2.setDuration(8000);
        animation2.setFillAfter(true);
        animation1.setInterpolator(new LinearInterpolator());
        animation2.setInterpolator(new LinearInterpolator());
        rot.addView(water[0]);
        rot.addView(water[1]);
       boolean Flag = true; 
       while(Flag)
       {
    	   water[0].startAnimation(animation2);
    	   if(water[0].getX()==0)
    		   water[1].startAnimation(animation2);
    	   if(water[1].getX()==0)
    		   water[0].startAnimation(animation2);
       }*/
  
