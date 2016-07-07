package com.readboy.plan.service;

import org.json.JSONArray;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.readboy.plan.inface.DataCallback;

public class GetDataFromWeb {
	
	String url = "http://test.m.dream.cn:80/v1/plan/getchapters?sn=000000221445945996de48b9dd4e005e26d9f2006a1a8e10bcstudyplan&book_id=416166";
	public JsonArrayRequest getdatafromweb(Context context , String urlString ,  final DataCallback callback) {
		RequestQueue Queue = Volley.newRequestQueue(context);
		JsonArrayRequest request = new JsonArrayRequest(url, new Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				callback.callback(response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		Queue.add(request);
		return request;	
	}
	
	
	

}
