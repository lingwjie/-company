package com.example.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	//int count ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //��ȡ����
        
        preferences = getSharedPreferences("crazyit",MODE_WORLD_READABLE);
        editor = preferences.edit();
        Button writeButton = (Button)findViewById(R.id.write);
        Button readButton = (Button)findViewById(R.id.read);
        
        writeButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				int count = preferences.getInt("count", 0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy��mm��dd��"+"hh:mm:ss");
				//���뵱ǰ����
				editor.putString("time",sdf.format(new Date()));
				//����һ�������
				editor.putInt("random", (int)(Math.random()*100));
				editor.putInt("count", ++count);				
				editor.commit();
			}
		});
        readButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {				
				String time = preferences.getString("time", null);
				int randnm = preferences.getInt("random", 0);
				String result = time == null? "����ʱ��û��д������":"д��ʱ�䣺"+time +"д��������:"+ randnm;
				Toast.makeText(MainActivity.this, result, 50000).show();
			}
		});
    }
}
