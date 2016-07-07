package com.example.progrebar;

import android.R.integer;
import android.app.Activity;
import android.net.wifi.WifiConfiguration.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private int[] data = new int[100];
	int hasdata =0;
	int mprogressstate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar bar = (ProgressBar)findViewById(R.id.bar);
        final ProgressBar changebar = (ProgressBar)findViewById(R.id.changebar);
        bar.incrementProgressBy(10);
        changebar.incrementProgressBy(20);
        final Handler tHandler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		super.handleMessage(msg);
        		if (msg.what == 0x11) {
					changebar.incrementProgressBy(-10);
				}
        	}
        };
        new Thread(){
        	public void run() {
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		tHandler.sendEmptyMessage(0x11);
        	};
        }.start();
        
//        final Handler mHandler = new Handler(){
//        	@Override
//        	public void handleMessage(Message msg) {
//        	super.handleMessage(msg);
//        	if (msg.what == 0x11) {
//				bar.setProgress(mprogressstate);
//				changebar.setProgress(mprogressstate);
//			}
//        }};
//        new Thread(){
//        	public void run() {
//        		while (mprogressstate <100) {
//        			mprogressstate = dowork();
//					
//					Message m = new Message();
//					m.what = 0x11;
//					mHandler.sendMessage(m);
//				}
//        	}}.start();
    }
    public int dowork()
    {
    	//data[hasdata++] = (int)(Math.random()*100);    
    	hasdata++;
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return hasdata;
    }
}

