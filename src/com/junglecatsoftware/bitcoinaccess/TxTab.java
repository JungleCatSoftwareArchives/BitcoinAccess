package com.junglecatsoftware.bitcoinaccess;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TxTab extends Fragment {
	
	public static Fragment newInstance(Context context) {
		TxTab f = new TxTab();	
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tx_tab, null);	
		return root;
	}

}