package com.example.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import android.R.integer;

public class GetAndPost {
	
	public static String sendGet(String url, String params) {
		String urlName = url + "?" + params;
		BufferedReader in = null;
		String result = "";
		URL connectUrl;
		try {
			connectUrl = new URL(urlName);
			URLConnection connection = connectUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ��������
			connection.connect();
			// ��ȡ���е���Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// ��������ͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "---> " + map.get(key));
			}
			// ��ȡurl����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String lineString;

			while ((lineString = in.readLine()) != null) {
				result += "\n" + lineString;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	public static String sendPost(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		URL connectUrl;
		try {
			connectUrl = new URL(url);
			URLConnection connection = connectUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			/*************/
			connection.setDoOutput(true);
			connection.setDoInput(true);

			out = new PrintWriter(connection.getOutputStream());

			out.print(params);
			out.flush();

			// ��ȡurl����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String lineString;

			while ((lineString = in.readLine()) != null) {
				result += "\n" + lineString;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {

					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
