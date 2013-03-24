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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class OptTab extends Fragment {
	
	private ViewGroup root;
	private Context context=null;
	
	public static Fragment newInstance(Context context) {
		OptTab f = new OptTab();	
		
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		root = (ViewGroup) inflater.inflate(R.layout.opt_tab, null);
		context = container.getContext().getApplicationContext();
		
		addListeners();
		
		return root;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		refreshValues();
	}
	
	private void addListeners(){
		//HTTPS toggle
		((ToggleButton)root.findViewById(R.id.Opt_HttpsToggle)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE).edit();
				prefs.putBoolean("server_https", ((ToggleButton)v).isChecked());
				prefs.commit();
			}
		});
		
		//RPC Hostname
		((EditText)root.findViewById(R.id.Opt_HostField)).addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable e) {
				SharedPreferences.Editor prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE).edit();
				prefs.putString("server_host", e.toString());
				prefs.commit();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		});
		
		//RPC Port
		((EditText)root.findViewById(R.id.Opt_PortField)).addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable e) {
				SharedPreferences.Editor prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE).edit();
				prefs.putString("server_port", e.toString());
				prefs.commit();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		});
		
		//RPC Username
		((EditText)root.findViewById(R.id.Opt_UsernameField)).addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable e) {
				SharedPreferences.Editor prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE).edit();
				prefs.putString("server_username", e.toString());
				prefs.commit();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		});
		
		//RPC PAssword
		((EditText)root.findViewById(R.id.Opt_PasswordField)).addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable e) {
				SharedPreferences.Editor prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE).edit();
				prefs.putString("server_password", e.toString());
				prefs.commit();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		});
	}
	
	private void refreshValues(){
		SharedPreferences prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE);

		((ToggleButton)root.findViewById(R.id.Opt_HttpsToggle)).setChecked(prefs.getBoolean("server_https", true));
		((EditText)root.findViewById(R.id.Opt_HostField)).setText(prefs.getString("server_host", "127.0.0.1"));
		((EditText)root.findViewById(R.id.Opt_PortField)).setText(prefs.getString("server_port", "8332"));
		((EditText)root.findViewById(R.id.Opt_UsernameField)).setText(prefs.getString("server_username", ""));
		((EditText)root.findViewById(R.id.Opt_PasswordField)).setText(prefs.getString("server_password", ""));
	}
}