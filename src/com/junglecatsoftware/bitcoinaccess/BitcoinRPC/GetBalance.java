package com.junglecatsoftware.bitcoinaccess.BitcoinRPC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
		
		
		OutputStreamWriter writer=null;
		BufferedReader reader=null;
		int id = (new java.util.Random()).nextInt();
		try{
			URL url = new URL("http://172.16.20.20:8332/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Authorization", "Basic "+android.util.Base64.encodeToString("test:testpass".getBytes(), android.util.Base64.NO_WRAP)); 
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/plain"); 
			String content="{\"jsonrpc\": \"1.0\", \"id\":\"BitcoinAccess-"+id+"\", \"method\": \"getbalance\", \"params\": [] }";

			writer = new OutputStreamWriter(conn.getOutputStream());

			writer.write(content);
			writer.flush();

			String line;
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			while ((line = reader.readLine()) != null) {
			    result+=line;
			}
			return result;
		}catch(Exception e){
			return "ERROR:"+e.getMessage();
		}finally{
			if(writer!=null){
				try{
					writer.close();
				}catch(Exception e){
				}
			}
			if(reader!=null){
				try{
					reader.close();
				}catch(Exception e){
				}

			}
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(context!=null){
			Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		}
		
		try {
			JSONObject json = new JSONObject(result);
			
			String balance = json.getString("result");
			
			for(TextView textView : textViews){
				textView.setText(balance);
			}
		} catch (JSONException e) {
			if(context!=null){
				Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	}
}
