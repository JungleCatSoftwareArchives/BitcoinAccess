package com.junglecatsoftware.bitcoinaccess;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OptTab extends Fragment {
	
	public static Fragment newInstance(Context context) {
		OptTab f = new OptTab();	
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.opt_tab, null);	
		return root;
	}

}