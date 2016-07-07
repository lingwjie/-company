package com.example.nogif;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class piano extends View {
	
	public float currentX =  40;
	public float currentY = 50;
	 public piano(Context context)//,AttributeSet sttr) 
	 {
			super(context);//,sttr);
			// TODO Auto-generated constructor stub
		}
	 public void onDraw(Canvas canvas )
	 {
		 super.onDraw(canvas);
		 Paint p = new Paint();
		 p.setColor(Color.RED);
		 
		 canvas.drawCircle(currentX, currentY, 15, p);
	 }
	 
	 @Override
	 public boolean onTouchEvent(MotionEvent e)
	 {
		 this.currentX = e.getX();
		 this.currentY = e.getY();
		 
		 this.invalidate();//draw×é¼þÖØ»æ 
		return true;
	 }

}