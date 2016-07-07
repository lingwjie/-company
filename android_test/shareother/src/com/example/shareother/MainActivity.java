package com.example.shareother;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context useCount = null;
		try {
			useCount = createPackageContext("com.example.shared", 
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SharedPreferences prefs= useCount.getSharedPreferences("crazyit", Context.MODE_WORLD_READABLE);
		int count = prefs.getInt("count", 0);		
		TextView show = (TextView)findViewById(R.id.show);
		show.setText(""+count);
    }
}
