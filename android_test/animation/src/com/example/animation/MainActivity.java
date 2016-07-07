package com.example.animation;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;

import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = (Button)findViewById(R.id.start);
        Button stop = (Button)findViewById(R.id.stop);
        ImageView imageview = (ImageView)findViewById(R.id.animation);
        final AnimationDrawable anim = (AnimationDrawable)imageview.getBackground();
        play.setOnClickListener(new OnClickListener()
        {
        	@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				anim.start();
			}
        });
        stop.setOnClickListener(new OnClickListener()
        {
        	@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				anim.stop();
			}
        });
       
    }
}
