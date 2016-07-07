package com.example.ontouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;

public class VerticalSeeBar extends AbsSeekBar{

	public VerticalSeeBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public VerticalSeeBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public VerticalSeeBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.rotate(-90);
		canvas.translate(-getHeight(), 0);//将经过旋转后得到的VerticalSeekBar移到正确的位置,注意经旋转后宽高值互换  
		super.onDraw(canvas);
	}
	
	
}
