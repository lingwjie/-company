 package gwj.first.linkgame;

import java.util.Timer;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//开始按钮
	private Button startButton = (Button)findViewById(R.id.start);
	//计时文本
	private TextView timeView = (TextView)findViewById(R.id.time);
	
	//计时器
	Timer timer = new Timer();
	int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				gamestart();
			}
		});
    }
    public void gamestart()
    {
    	//布局，初始化初始界面
    	
    }
}
