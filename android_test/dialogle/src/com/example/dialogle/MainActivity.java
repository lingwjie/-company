package com.example.dialogle;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button)findViewById(R.id.show);
        final AlertDialog.Builder builder =
        		new AlertDialog.Builder(this);
        bn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				builder.setTitle("对话框");
				builder.setMessage("提示对话框");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {	
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText show = (EditText)findViewById(R.id.edit);
						show.setText("点击了确定按钮");
					}

				});
				builder.setNegativeButton("取消", 
						new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText show = (EditText)findViewById(R.id.edit);
						show.setText("点击了取消按钮");
						}

				});
				builder.create().show();
			}
		});
    }
}
