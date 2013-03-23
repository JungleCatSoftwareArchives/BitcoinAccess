package com.junglecatsoftware.bitcoinaccess.BitcoinRPC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitcoinRPC {
	private static String sendRPC(String method, String params){
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
	public static String getBalance(){
		return sendRPC("getbalance","");
	}
}
