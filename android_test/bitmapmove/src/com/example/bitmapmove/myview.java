package com.example.bitmapmove;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;

public class myview extends View {
	Bitmap backBitmap;
	
	int width ;// = backBitmap.getWidth();
	int heigh;// =backBitmap.getHeight();
	int WIDTH = 20;
	float StartX =0;
	private Matrix matrix = new Matrix();
	public myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		backBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.new_map_game_bg);
		//backBitmap = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.new_map_game_bg)).getBitmap();
		//final int width = backBitmap.getWidth();
		width = backBitmap.getWidth();
		heigh =backBitmap.getHeight();
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0x11) {
//					if (StartX >width) {
//						StartX =0;
//					}
//					else {
//						StartX+=90;
//					}
					
//					
			}
				postInvalidate();
			}
		};
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x11);
			}
		}, 0 , 1000);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		matrix.reset();
		matrix.setTranslate(-100, 0);
		width = backBitmap.getWidth();
		Bitmap bitmap2 = Bitmap.createBitmap(backBitmap,(int)StartX,0,width-100,heigh,matrix,true);
		canvas.drawBitmap(bitmap2, matrix, null);
	}

}
