package com.example.back;

import com.example.back.JieKou.listener;

public class B {
	private listener l;
	public void setclick(listener l){
		this.l = l;
	}
	public void work(){
		l.click();
	}
}
