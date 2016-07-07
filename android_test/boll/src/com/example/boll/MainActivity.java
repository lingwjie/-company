package com.example.boll;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        
        LinearLayout root = (LinearLayout)findViewById(R.id.root);
      /*  final Drawview draw = new Drawview(this);
        draw.setOnTouchListener(new OnTouchListener(){
        	public boolean onTouch(View agr0,MotionEvent event)
        	{
        		draw.currentX = event.getX();
        		draw.currentY = event.getY();
        		draw.invalidate();
        		return true;
        	}
        });
        root.addView(draw);*/
    }
}
