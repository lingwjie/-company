package com.example.myview;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);    	
      setContentView(R.layout.activity_main);
      RelativeLayout main = (RelativeLayout)findViewById(R.id.root);
      final ImageView view1 = new ImageView(this);
      final ImageView view2 = new ImageView(this);
      ImageView view = new ImageView(this);
      view.setImageResource(R.drawable.map_game_bg);
      view1.setImageResource(R.drawable.map_game_bg);
      view2.setImageResource(R.drawable.map_game_bg);
     
      main.addView(view1);
      view1.setX(0);
      main.addView(view2);
      view1.setX(0);
     // main.addView(view);
      final TranslateAnimation animation = new TranslateAnimation(1700, -1700, 0, 0);
      final TranslateAnimation animation1 = new TranslateAnimation(1700, 0, 0, 0);
      animation.setDuration(5000);
      animation.setInterpolator(new LinearInterpolator());
      view.startAnimation(animation1);
      final Handler handler = new Handler(){@Override
    public void handleMessage(Message msg) {
    	// TODO Auto-generated method stub
    	super.handleMessage(msg);
    	if (msg.what == 0x11) {
			count++;
			if (count%2 ==0) {
				view1.startAnimation(animation);
			} else {
				view2.startAnimation(animation);
			}
			
		}
    	
    }};
    new Timer().schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.sendEmptyMessage(0x11);
		}
	}, 2500, 2500);
    }
    class myview extends View
    {
    	private Bitmap backBitmap;
    	public myview(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			backBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mainbackground);
			
		}
    	
    	@Override

    	public void onDraw(final Canvas canvas)
    	{
    		final Bitmap backBitmap2 = Bitmap.createBitmap(backBitmap, 0, 0, 100, 10);
    		final Bitmap backBitmap3 = Bitmap.createBitmap(backBitmap, 0, 100, 10, 100);
    		canvas.drawBitmap(backBitmap2, 10, 500, null);
    		//canvas.drawBitmap(backBitmap3, 100, 100, null);
    		int Count=1;
    		if(Count ==1)
    			canvas.drawBitmap(backBitmap3, 100, 100, null);
//    		final Handler handler = new Handler(){
//    			@Override
//    		public void handleMessage(Message msg) {
//    			// TODO Auto-generated method stub
//    			super.handleMessage(msg);
//    			if (msg.what == 0x11) {
//    				canvas.drawBitmap(backBitmap3, 100, 100, null);
//				}
//    		}};
//    		new Timer().schedule(new TimerTask() {
//				
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					handler.sendEmptyMessage(0x11);
//					
//				}
//			}, 10);
    		
    	}
    	}
}
