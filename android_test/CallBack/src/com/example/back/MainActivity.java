package com.example.back;

import java.util.Timer;
import java.util.TimerTask;

import com.example.back.JieKou.listener;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	private B b;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("11111111111");
        b = new B();
        
        final Handler handler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		super.handleMessage(msg);
        		if (msg.what == 0x111) {
        			System.out.println("mmmmmmmmmm");
        			b.setclick(new listener() {
    					
    					@Override
    					public void click() {
    						System.out.print("kkkkkkkkk");
    					}
    					
    				});
				}
        		System.out.println("ccccccc");
        		b.work();
        		System.out.println("bbbbbbbb");
        	}
        };
       
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0x111);
				System.out.println("jjjjjjjjjj");
			}
		}, 1000);
    }
}
