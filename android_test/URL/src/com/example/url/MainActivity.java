package com.example.url;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView showTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTextView =(TextView)findViewById(R.id.show);
        Button getButton =    (Button)findViewById(R.id.get);
        Button postButton = (Button)findViewById(R.id.post);      
        getButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			new Thread(getRunnable).start();
    		}
    	});
        postButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			new Thread(postRunnable).start();
    		}
    	});
    }
    Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showTextView.setText(msg.obj.toString());
			
		}
	};
	 Runnable  getRunnable = new Runnable() {
			
			@Override
			public void run() {
				String respone = GetAndPost.sendGet("https://www.baidu.com", "s?wd=hhh");					
				Message msg = new Message();
				msg.what = 0x11;
				msg.obj = respone;
				handler.sendMessage(msg);
			}
		};
		
	 Runnable  postRunnable = new Runnable() {
			
			@Override
			public void run() {
				String respone = GetAndPost.sendGet("https://www.baidu.com", null);					
				Message msg = new Message();
				msg.what = 0x11;
				msg.obj = respone;
				handler.sendMessage(msg);
			}
		};
   
}
