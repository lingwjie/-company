package com.example.audiomanager;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button start ;
	private Button end ;
	private Button config;
	private Toast messageToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);
        config = (Button)findViewById(R.id.config);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        config.setOnClickListener(this);
        
    }
    
    

	@Override
	protected void onDestroy() {
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		messageToast = new Toast(this);
		switch (v.getId()) {
		case R.id.start:
			if (messageToast != null) {
				messageToast.cancel();
			}
			messageToast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
			intent.setClass(MainActivity.this, BellService.class);
			startService(intent);
			break;
		case R.id.end:
			if (messageToast != null) {
				messageToast.cancel();
			}
			messageToast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
			intent.setClass(MainActivity.this, BellService.class);
			stopService(intent);
			break;
		case R.id.config:
			if (messageToast != null) {
				messageToast.cancel();
			}
			messageToast.makeText(MainActivity.this, "config", Toast.LENGTH_SHORT).show();
			intent.setClass(MainActivity.this, BellService.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}
}
