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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainTab extends Fragment {
	
	public static Fragment newInstance(Context context) {
		MainTab f = new MainTab();
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.main_tab, null);
		
		root.findViewById(R.id.Main_Balance_Label).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Balance_Value).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Unconfirmed_Label).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Unconfirmed_Value).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Send_Button).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Rec_Button).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.Main_Spinner).setVisibility(View.VISIBLE);
		
		//Data Fetch
		new Thread(new Runnable() {
			public void run(){
				//Artificial Delay (for now)
				try{
					Thread.sleep(5000);
				}catch(Exception e){
				}
				root.post(new Runnable(){
					public void run(){
						root.findViewById(R.id.Main_Balance_Label).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Balance_Value).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Unconfirmed_Label).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Unconfirmed_Value).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Send_Button).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Rec_Button).setVisibility(View.VISIBLE);
						root.findViewById(R.id.Main_Spinner).setVisibility(View.GONE);
					}
				});
			}
		}).start();
		
		return root;
	}

}