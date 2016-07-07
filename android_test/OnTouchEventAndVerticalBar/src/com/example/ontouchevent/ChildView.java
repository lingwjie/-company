package com.example.ontouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ChildView extends View{
	
//	public ChildView(Context context, AttributeSet attrs, int defStyleAttr,
//			int defStyleRes) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//		// TODO Auto-generated constructor stub
//	}
	public ChildView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public ChildView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	private static final String TAG = "Child";
	public ChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i(TAG, "child onmeasure1");
		super.onDraw(canvas);
	}
	@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
		Log.i(TAG, "child onmeasure");
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "child down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "child move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i(TAG, "child up");
			break;
		default:
			break;
		}
		return false;
	}
}
