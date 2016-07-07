package com.gwj.clothes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.*;

import javax.annotation.Resource;

import android.graphics.*;
public class Pobing extends View {
	Bitmap pob = MainActivity.pobBitmap[0];
	static int i = 0;
	public Pobing( Context context, AttributeSet attrs) {
		super(context, attrs);
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0x11) {											
					pob = MainActivity.pobBitmap[i];
					i++;

					invalidate();
				}
			}};
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				handler.sendEmptyMessage(0x11);	
				if (i == 7) {
					cancel();
					i = 0;
				}
		}},0, 200);
	}
	public void onDraw(Canvas canvas)
	{	 
		canvas.drawBitmap(pob, 0, 0, null);
	}
}
