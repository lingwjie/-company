package com.example.ontouchevent;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditView extends EditText{
	private final static String TAG = "MyEditView";  
    private Drawable imgInable;  
    private Drawable imgAble;  
    private Drawable searchDrawable;
    private Context mContext; 
	public MyEditView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		init();
	}
	 private void init() {  
//	        imgInable = mContext.getResources().getDrawable(R.drawable.delete_gray);  
//	        imgAble = mContext.getResources().getDrawable(R.drawable.delete);  
	        //searchDrawable = mContext.getResources().getDrawable(R.id.search);
	        addTextChangedListener(new TextWatcher() {  
	            @Override  
	            public void onTextChanged(CharSequence s, int start, int before, int count) {}  
	            @Override  
	            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}  
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					setDrawable();  
				}  
	        });  
	        setDrawable();  
	    }  
	      
	 @Override
	public void setCompoundDrawables(Drawable left, Drawable top,
			Drawable right, Drawable bottom) {
		// TODO Auto-generated method stub
		super.setCompoundDrawables(left, top, right, bottom);
	}
	    //设置删除图片  
	    private void setDrawable() {  
	        if(length() < 1)  
	            setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);  
	        else  
	            setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);  
	    }  
	      
	     // 处理删除事件  
	    @Override  
	    public boolean onTouchEvent(MotionEvent event) {  
	        /*if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {  
	            int eventX = (int) event.getRawX();  
	            int eventY = (int) event.getRawY();  
	            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);  
	            Rect rect = new Rect();  
	            getGlobalVisibleRect(rect);  
	            rect.left = rect.right - 50;  
	            if(rect.contains(eventX, eventY))   
	                setText("");  
	        } */ 
	        return super.onTouchEvent(event);  
	    }  
	  
	    @Override  
	    protected void finalize() throws Throwable {  
	        super.finalize();  
	    }  
}
