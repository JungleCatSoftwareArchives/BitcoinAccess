package com.junglecatsoftware.bitcoinaccess.BitcoinRPC;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class GetBalance extends AsyncTask<TextView,Integer,String> {
	private TextView[] textViews;
	private Context context = null;
	
	public GetBalance(){
		super();
	}
	public GetBalance(Context context){
		super();
		this.context = context;
	}
	
	@Override
	protected String doInBackground(TextView... textViews) {
		this.textViews = textViews;
		if(context == null){
			context = textViews[0].getContext().getApplicationContext();
		}
		
		return BitcoinRPC.getBalance(context,6);
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	protected void onPostExecute(String result) {
		String balance = "--";
		if(result.toLowerCase().startsWith("error")){
			if(context!=null){
				Toast.makeText(context, result, Toast.LENGTH_LONG).show();
			}
		}else{
			try {
				JSONObject json = new JSONObject(result);
				
				balance = json.getString("result");
			} catch (JSONException e) {
				if(context!=null){
					Toast.makeText(context, "ERROR: Unable to parse response from server", Toast.LENGTH_LONG).show();
				}
			}
		}
		
		for(TextView textView : textViews){
			textView.setText(balance);
		}
	}
}
