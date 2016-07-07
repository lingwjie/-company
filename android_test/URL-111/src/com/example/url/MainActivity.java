package com.example.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView showImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showImageView  = (ImageView)findViewById(R.id.imageview);
        try {
			URL url = new URL("http://www.crazyit.org/attachments/" +
            "month_1008/20100812_7763e970f822325bfb019ELQVym8tW3A.png");
			
			InputStream is;
			try {
				is = url.openStream();
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				showImageView.setImageBitmap(bitmap);
				is.close();
				is = url.openStream();
				OutputStream os = openFileOutput("crazyit.png", MODE_WORLD_READABLE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
