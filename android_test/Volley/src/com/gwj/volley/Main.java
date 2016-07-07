package com.gwj.volley;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.IntentFilter;

import android.os.Bundle;
import android.util.Log;


public class Main extends Activity {
	

	protected static final String TAG = "Main";
	IntentFilter intentFilter;
	String url = "http://test.m.dream.cn:80/v1/user/addplan?sn=00000022144919234062266b72d310423cd5ba77d39ffdec97studyplan";
	JSONArray upArray = new JSONArray();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RequestQueue upQueue = Volley.newRequestQueue(this);	
		JSONObject obj = new JSONObject();				
		try {
			obj.put("ChapterEnd", 1446191485);
			obj.put("ChapterStart", 1446191480);
			obj.put("Cid", 1);
			obj.put("Pid", 1);
			obj.put("Uid", 644836);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		upArray.put(obj);
		
	
	/*	JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, obj, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG, "response = "+response.toString());
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "error = "+error.toString());
				
			}
		});
		upQueue.add(request);*/
		JsonRequest<JSONArray> request= new JsonRequest<JSONArray>(Method.POST,
				url, 
				upArray.toString(),
				new Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, "response -> " );
					}
				},
				new ErrorListener(){
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, error.getMessage(), error);
						}
					}) {

						@Override
						protected Response<JSONArray> parseNetworkResponse(
								NetworkResponse response) {
							String parsed;
						    try {
						        parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
						    } catch (UnsupportedEncodingException e) {
						        parsed = new String(response.data);
						    }
						    JSONArray array = null;
							try {
								array = new JSONArray(parsed);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    return Response.success(array, HttpHeaderParser.parseCacheHeaders(response));
							
						}
			
		};
		upQueue.add(request);
	}
	

	/*RequestQueue mQueue = Volley.newRequestQueue(this);
	//Cache aCache = mQueue.getCache();//获取volley的cache对象
	String url = "http://test.m.dream.cn:80/v1/plan/getplans?sn=000000221446170024ce6a2f6d63d23753d4502faa1b190701studyplan&uid=1";

	JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
			new Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					Log.d("TAG", response.toString());
					try {
						JSONObject jsonObject = response.getJSONObject(1);
						Log.d("TAG", jsonObject.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Log.d("e", error.getMessage(), error);
				}
			});*/
	
	}






	
/*		Entry entry = new Entry();
//aCache.get(url);//获取缓存entry
//aCache.put(url, entry);
//jsonObjectRequest.getCacheKey();
System.out.println(jsonObjectRequest.getCacheKey());
if(mQueue.getCache().get(jsonObjectRequest.getCacheKey())!=null){
System.out.println("cache"+aCache.get(url).data.toString());
   // mTextView.setText(new String(mRequestQueue.getCache().get(url).data).toString());
	System.out.println(mQueue.getCache().get(url));
}
else {
	System.out.println("空");
}*/
