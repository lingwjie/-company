package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Client extends Activity {

	TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        show = (TextView)findViewById(R.id.show);
        
        try {
        	
			Socket socket = new Socket("192.168.20.6", 5000);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line =br.readLine();
			
			show.setText("来自服务器的数据： "+line);
			br.close();
			socket.close();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
