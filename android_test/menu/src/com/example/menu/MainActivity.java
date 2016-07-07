package com.example.menu;

import java.security.PublicKey;

import com.example.menu.R.color;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	
	final int PLAIN_ITEM = 0x11b;
	
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;
	
	private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edittext);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	SubMenu fontMenu = menu.addSubMenu("�����С");
    	fontMenu.setIcon(R.drawable.note3);
    	fontMenu.add(0, FONT_10, 0, "10������");
    	fontMenu.add(0, FONT_12, 0, "12������");
    	fontMenu.add(0, FONT_14, 0, "14������");
    	fontMenu.add(0, FONT_16, 0, "16������");
    	fontMenu.add(0, FONT_18, 0, "18������");
    	
    	menu.add(0,PLAIN_ITEM,0,"��ͨ�˵���");
    	
    	SubMenu colorMenu = menu.addSubMenu("������ɫ");
    	colorMenu.setIcon(R.drawable.note2);
    	colorMenu.setHeaderTitle("ѡ��������ɫ");
    	colorMenu.add(0,FONT_RED,0,"��ɫ");
    	colorMenu.add(0, FONT_BLUE, 0, "��ɫ");
    	colorMenu.add(0, FONT_GREEN, 0, "��ɫ"); 
    	
		return super.onCreateOptionsMenu(menu);	
    	
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		switch (mi.getItemId()) {
		case FONT_10:
			editText.setTextSize(10*2);
			break;
		case FONT_12:
			editText.setTextSize(12*2);
			break;
		case FONT_14:
			editText.setTextSize(14*2);
			break;
		case FONT_16:
			editText.setTextSize(16*2);
			break;
		case FONT_18:
			editText.setTextSize(18*2);
			break;
		case FONT_RED:
			editText.setTextColor(Color.RED);
			break;
		case FONT_BLUE:
			editText.setTextColor(Color.BLUE);
			break;
		case FONT_GREEN:
			editText.setTextColor(Color.GREEN);
			break;
		case PLAIN_ITEM:
			Toast toast = Toast.makeText(MainActivity.this, "��������ͨ�˵�", 5000);
			toast.show();
			break;
			}
		return true;
	}
}
