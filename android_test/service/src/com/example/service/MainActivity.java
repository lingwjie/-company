package com.example.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bind,unbind,getServiceStatus;
	BindService.MyBinder binder;
	//定义一个连接对象
	private ServiceConnection connection  = new ServiceConnection() {
		//连接断开时回调
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("service destroy");
		}
		//连接成功时回调
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("service connected");
			binder = (BindService.MyBinder) service;
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = (Button)findViewById(R.id.bind);
        unbind = (Button)findViewById(R.id.unbind);
        getServiceStatus = (Button)findViewById(R.id.getservice);
        final Intent intent = new Intent();
        intent.setAction("com.example.service.BIND_SERVICE");
        bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				bindService(intent, connection, Service.BIND_AUTO_CREATE);
			}
		});
        unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				unbindService(connection);
			}
		});
        getServiceStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(MainActivity.this, "service的count值为："+binder.getCount(), 4000).show();
			}
		});
    }
}
