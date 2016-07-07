package com.gwj.jnijava;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView v = (TextView)findViewById(R.id.test);
        v.setText(calladd(5, 6)+" ");
    }
    static
    {
    	System.loadLibrary("test");
    }
    public native int calladd(int a , int b );
    public int add(int a , int b )
    {
    	return a+b;
    }
}
