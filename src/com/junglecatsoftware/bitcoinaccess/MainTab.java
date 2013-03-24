/*
* Copyright 2013 Bryan Wyatt
*
* This file is part of BitcoinAccess.
*
* BitcoinAccess is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* BitcoinAccess is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with BitcoinAccess. If not, see <http://www.gnu.org/licenses/>.
*/

package com.junglecatsoftware.bitcoinaccess;

import com.junglecatsoftware.bitcoinaccess.BitcoinRPC.GetBalance;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainTab extends Fragment {
	
	private ViewGroup root;
	private Context context=null;
	
	public static Fragment newInstance(Context context) {
		MainTab f = new MainTab(context);
		
		return f;
	}
	public MainTab(){
		super();
	}
	public MainTab(Context context){
		super();
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		root = (ViewGroup) inflater.inflate(R.layout.main_tab, null);
		
		return root;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		refreshBalance();
	}
	
	private void refreshBalance(){
		new GetBalance(context).execute((TextView)root.findViewById(R.id.Main_Balance_Value));
	}

}