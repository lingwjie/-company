package com.example.yuyin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



//import com.example.android.apis.R;  
   
import android.content.Intent;  
import android.content.pm.PackageManager;  
import android.content.pm.ResolveInfo;  
import android.speech.RecognizerIntent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.ArrayAdapter;  
import android.widget.Button;  
import android.widget.ListView;  
   
import java.util.ArrayList;  
import java.util.List;  

/** 
 * Sample code that invokes the speech recognition intent API. 
 */  
public class MainActivity extends Activity implements OnClickListener {  
   
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;  
   
    private ListView mList;  
   
    /** 
     * Called with the activity is first created. 
     */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
   
        // Inflate our UI from its XML layout description.  
        setContentView(R.layout.activity_main);  
   
        // Get display items for later interaction  
        Button speakButton = (Button) findViewById(R.id.btn_speak);  
   
        mList = (ListView) findViewById(R.id.list);  
   
        // Check to see if a recognition activity is present  
        PackageManager pm = getPackageManager();  
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(  
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);  
        if (activities.size() != 0) {  
            speakButton.setOnClickListener(this);  
        } else {  
            speakButton.setEnabled(false);  
            speakButton.setText("Recognizer not present");  
        }  
    }  
   
    /** 
     * Handle the click on the start recognition button. 
     */  
    public void onClick(View v) {  
        if (v.getId() == R.id.btn_speak) {  
            startVoiceRecognitionActivity();  
        }  
    }  
   
    /** 
     * Fire an intent to start the speech recognition activity. 
     */  
    private void startVoiceRecognitionActivity() {  
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,  
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,  
                "Speech recognition demo");  
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);  
    }  
   
    /** 
     * Handle the results from the recognition activity. 
     */  
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE  
                && resultCode == RESULT_OK) {  
            // Fill the list view with the strings the recognizer thought it  
            // could have heard  
            ArrayList<String> matches = data  
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  
            mList.setAdapter(new ArrayAdapter<String>(this,  
                    android.R.layout.simple_list_item_1, matches));  
        }  
   
        super.onActivityResult(requestCode, resultCode, data);  
    }  
} 