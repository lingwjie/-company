package com.example.mediaandvideo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	

	private Button mediaButton , soundpool;
	private Button videoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaButton = (Button)findViewById(R.id.media);
        videoButton = (Button)findViewById(R.id.video);
        soundpool = (Button)findViewById(R.id.soundpool);
        mediaButton.setOnClickListener(this);
        videoButton.setOnClickListener(this);
        soundpool.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		switch (v.getId()) {
		case R.id.media:			
			MediaFragment mediaFragment = new MediaFragment();
			fragmentTransaction.replace(R.id.fragment_rooter, mediaFragment , "media");
			fragmentTransaction.addToBackStack(MediaFragment.class.getName());
		
			fragmentTransaction.commit();	
			break;
		case R.id.soundpool:
			SoundPoolFragment soundPoolFragment = new SoundPoolFragment();
			fragmentTransaction.replace(R.id.fragment_rooter, soundPoolFragment, "soundpool");
			fragmentTransaction.addToBackStack(SoundPoolFragment.class.getName());
			fragmentTransaction.commit();
			break;
		case R.id.video:
			Intent intent  = new Intent(MainActivity.this , VideoActivity.class);
			startActivity(intent);
			
	}
		
			
			
}}
