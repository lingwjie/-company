package com.example.ontouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class VerticalProgressBar extends ProgressBar{
	
	public VerticalProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public VerticalProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public VerticalProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.rotate(-90);
		canvas.translate(-getHeight(), 0);//��������ת��õ���VerticalProgressBar�Ƶ���ȷ��λ��,ע�⾭��ת<span style="white-space:pre">                     </span>    ����ֵ����
		super.onDraw(canvas);
	}
	/*@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}*/
	
	 @Override  
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	        super.onSizeChanged(h, w, oldw, oldh);//�������ֵ  
	    } 

}
