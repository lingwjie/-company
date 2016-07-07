package com.gwj.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class Tilefragment extends Fragment{
	private ImageButton leftmenu ;
	 @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState)  
	    {  
	        View view = inflater.inflate(R.layout.tilefragment, container, false);  
	        leftmenu = (ImageButton) view.findViewById(R.id.left_btn);  
	        leftmenu.setOnClickListener(new OnClickListener()  
	        {  
	            @Override  
	            public void onClick(View v)  
	            {  
	                Toast.makeText(getActivity(),  
	                        "i am an ImageButton in TitleFragment ! ",  
	                        Toast.LENGTH_SHORT).show();  
	            }  
	        });  
	        return view;  
	    } 
}
