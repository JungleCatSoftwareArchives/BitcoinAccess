package com.junglecatsoftware.bitcoinaccess.BitcoinRPC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;

public class BitcoinRPC {
	private static String sendRPC(Context context, String method, String params){
		SharedPreferences prefs = context.getSharedPreferences("com.junglecatsoftware.bitcoinaccess-RPC_INFO", Context.MODE_PRIVATE);
		
		OutputStreamWriter writer=null;
		BufferedReader reader=null;
		int id = (new java.util.Random()).nextInt();
		try{
			String RPC_Protocol = "http";
			if(prefs.getBoolean("server_https", true)){
				RPC_Protocol += "s";
			}
			URL url = new URL(RPC_Protocol+"://"+prefs.getString("server_host", "")+":"+prefs.getString("server_port", "8332")+"/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Authorization", "Basic "+android.util.Base64.encodeToString((prefs.getString("server_username", "")+":"+prefs.getString("server_password", "")).getBytes(), android.util.Base64.NO_WRAP)); 
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/plain"); 
			String content="{\"jsonrpc\": \"1.0\", \"id\":\"BitcoinAccess-"+id+"\", \"method\": \""+method+"\", \"params\": ["+params+"] }";

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
			return "ERROR: "+e.getMessage();
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
	public static String getBalance(Context context){
		return getBalance(context, "*", 6);
	}
	public static String getBalance(Context context, String account){
		return getBalance(context, account, 6);
	}
	public static String getBalance(Context context, int minconf){
		return getBalance(context, "*", minconf);
	}
	public static String getBalance(Context context, String account, int minconf){
		return sendRPC(context, "getbalance", "\""+account+"\","+minconf+"");
	}
}
