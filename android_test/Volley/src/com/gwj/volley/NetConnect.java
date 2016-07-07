package com.gwj.volley;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;

public class NetConnect extends BroadcastReceiver{
	boolean state;
	//MainActivity activity;	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)){  
            //signal strength changed  
        }  
        else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){//wifi���������  
            System.out.println("����״̬�ı�");  
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);  
            if(info.getState().equals(NetworkInfo.State.DISCONNECTED)){ 
        		
                System.out.println("wifi�������ӶϿ�");  
                //con.setVisibility(View.VISIBLE);
            }  
            else if(info.getState().equals(NetworkInfo.State.CONNECTED)){  
                  
                WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);  
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();                  
                state = true;
                //��ȡ��ǰwifi����  
                System.out.println("���ӵ����� " + wifiInfo.getSSID());  
                              }  
              
        }  
        else if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){//wifi�����  
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);  
              
            if(wifistate == WifiManager.WIFI_STATE_DISABLED){  
                System.out.println("ϵͳ�ر�wifi"); 
                state = false;
               // con.setVisibility(View.VISIBLE);
            }  
            else if(wifistate == WifiManager.WIFI_STATE_ENABLED){  
                System.out.println("ϵͳ����wifi"); 
                state = true;
            }  
        }  
    } 
		
}
