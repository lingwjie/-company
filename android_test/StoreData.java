package com.readboy.plan.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.readboy.plan.data.CourseInfo;
import com.readboy.plan.data.PlanDatabase;
import com.readboy.plan.data.PlanInfo;
import com.readboy.plan.inface.DataCallback;

public class StoreData implements DataCallback{
	PlanInfo plan ;
	@Override
	public void callback(JSONArray array) {
		/*JSONObject object = array.optJSONObject(0);
		try {
			String string  = object.getString("Catalog");
			System.out.println(string);
	
			int ii = 3;
			CourseInfo[] infos = new CourseInfo[10];
			for (int j = 0; j < infos.length; j++) 
				infos[j] = new CourseInfo(j, ii, 3, 3, "NO1.", 123, 234,1103, 25, 10, 8, 80, 0);
			 plan = new PlanInfo(ii, 2, "android", "bookUrl", "1124", "edition", "Ver", "print", 
					7, 1, 1, 24, "good good study", "icon",0,10,20,"first", 20151101, 20151202,null,infos);
			//PlanInfo[] queryPlan = PlanDatabase.queryPlan();
			PlanInfo[] queryPlan = null;
			for (int i = 0; queryPlan!=null&&i < queryPlan.length; i++) {
				
				if (queryPlan[i].id != ii) {
					boolean insert = PlanDatabase.insertPlan(plan);
				}
			}
			
			
			CourseInfo[] infos2 = new CourseInfo[10];
			for (int j = 0; j < infos.length; j++) 
				infos2[j] = new CourseInfo(ii*10+j, ii+1, 3, 3, "NO2.", 123, 234,1103, 25, 10, 8, 80, 0);
			PlanInfo plan2 = new PlanInfo(ii+1, 3, "java", "bookUrl", "11240", "edition", "Ver", "print", 
					8, 2, 2, 22, "day day up", "icon2",0,20,30,"last", 20151111, 20151222,null,infos2);
			for (int i = 0;queryPlan!=null&& i < queryPlan.length; i++) {
				
				if (queryPlan[i].id != ii+1) {
					boolean insert = PlanDatabase.insertPlan(plan2);
				}
			}
			//boolean insert2 = DataService.insertPlan(plan2);
		    //System.out.println(insert+"==="+insert2);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
	}

}
