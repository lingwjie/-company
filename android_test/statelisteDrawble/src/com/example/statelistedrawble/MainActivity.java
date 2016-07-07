package com.example.statelistedrawble;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText view1 = (EditText)findViewById(R.id.textView1);
        final EditText view2 = (EditText)findViewById(R.id.textView2);
    }
}
