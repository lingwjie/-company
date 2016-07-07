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
public class snail extends View  {
	int[] image = {R.drawable.a00 , R.drawable.a01 , R.drawable.a02 ,
			R.drawable.a03 , R.drawable.a04 , R.drawable.a05 ,
			R.drawable.a06 , R.drawable.a07 , R.drawable.a08 ,
			R.drawable.a09 };
	int[] imageb = {R.drawable.b00 , R.drawable.b01 , R.drawable.b02 ,
			R.drawable.b03 , R.drawable.b04 , R.drawable.b05 ,
			R.drawable.b06 , R.drawable.b07 , R.drawable.b08 ,
			R.drawable.b09,R.drawable.b10 , R.drawable.b11 , R.drawable.b12 ,
			R.drawable.b13 , R.drawable.b14 , R.drawable.b15 ,
			R.drawable.b16 , R.drawable.b17 , R.drawable.b18 ,
			R.drawable.b19 , R.drawable.b20 , R.drawable.b21 , R.drawable.b22 ,
			R.drawable.b23 , R.drawable.b24 };
	int index = 0 ;
	static int maxindex = 9;
	static boolean move = true;//蜗牛是否被点击,false点击
	Bitmap snail ;
	private Matrix matrix= new Matrix();
	public snail( Context context, AttributeSet attrs) {
		super(context, attrs);
		final Resources resources = context.getResources();
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0x11) {	
					
					if (move) {
						if (index >= maxindex) {
							index = 0;}
						snail = BitmapFactory.decodeResource(resources, image[index]);
					}
					else {
						snail = BitmapFactory.decodeResource(resources, imageb[index]);
						if(index == maxindex){
							move = true;
						    maxindex = 9;
						 }
					}
					index++;
					invalidate();
				}
			}};
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				handler.sendEmptyMessage(0x11);				
		}},0, 300);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		//图片反转
		 float[] floats = null; 
		 floats = new float[] { -1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f };  
		 matrix.setValues(floats);
		 Bitmap bitmap = Bitmap.createBitmap(snail, 0, 0, snail.getWidth(), 
				                             snail.getHeight(), matrix, true);
		
		 if(MainActivity.direction)
		 {
			 canvas.drawBitmap(snail, 0, 0, null);
		 }		 
		 if (!MainActivity.direction) {
			 canvas.drawBitmap(bitmap, 0, 0 , null);
		}
	}
}
