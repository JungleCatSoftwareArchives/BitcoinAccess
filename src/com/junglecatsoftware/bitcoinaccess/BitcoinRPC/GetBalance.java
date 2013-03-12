package com.junglecatsoftware.bitcoinaccess.BitcoinRPC;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetBalance extends AsyncTask<TextView,Integer,String> {
	private TextView[] textViews;
	
	@Override
	protected String doInBackground(TextView... textViews) {
		this.textViews = textViews;
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
		}
		
		return "1111";
	}
	
	@Override
	protected void onPostExecute(String result) {
		for(TextView textView : textViews){
			textView.setText(result);
		}
	}
}
