package com.example.aidlclient;


import com.example.aidlservice.ICat;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private ICat catservice;
	private Button get;
	EditText color , weight;
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			catservice = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder server) {
			catservice = ICat.Stub.asInterface(server);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = (Button)findViewById(R.id.getdata);
        color = (EditText)findViewById(R.id.color);
        weight = (EditText)findViewById(R.id.weight);
        //创建intent
        Intent intent = new Intent();
        intent.setAction("com.example.aidl.action.AIDL_SERVICE");
        //绑定远程服务
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
        get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					color.setText(catservice.getColor());
					weight.setText(catservice.getWeight()+" ");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
    }
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	this.unbindService(connection);
    }
}
