package com.example.clipdrawable;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView view = (ImageView)findViewById(R.id.view);
        final ClipDrawable drawable = (ClipDrawable)view.getDrawable();
        
        final Handler handler =  new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		super.handleMessage(msg);
        		if (msg.what == 0x11) {
					drawable.setLevel(drawable.getLevel()+20);
				}
        	}
        };
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0x11);
				if (drawable.getLevel() >= 10000) {
					this.cancel();
				}
			}
		}, 0,100);
        
    }
}

