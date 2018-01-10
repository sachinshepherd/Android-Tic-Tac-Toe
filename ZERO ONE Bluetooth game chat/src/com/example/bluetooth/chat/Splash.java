package com.example.bluetooth.chat;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

@TargetApi(9)
@SuppressLint({ "ParserError", "ParserError", "NewApi" })
public class Splash extends Activity {
Button single,Double,exit,send,check;
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
String st,macAddress;
int a=0;
JSONObject no;
private BluetoothAdapter myBluetoothAdapter;
private static final int REQUEST_ENABLE_BT = 1;
    @SuppressLint("ParserError")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation anim9 = AnimationUtils.loadAnimation(this, R.anim.jelly);
        single=(Button)findViewById(R.id.Button01);
        Double=(Button)findViewById(R.id.Button02);
        exit=(Button)findViewById(R.id.Button03);
        send=(Button)findViewById(R.id.button1);
        check=(Button)findViewById(R.id.button2);
        check.setVisibility(View.INVISIBLE);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		 WifiInfo wInfo = wifiManager.getConnectionInfo();
		 macAddress= wInfo.getMacAddress();
		 st=macAddress;
		 getvalue(0);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  single.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sn =new Intent(getApplicationContext(), oneplayer.class);
				startActivity(sn);
			}
		});
  Double.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent dl =new Intent(getApplicationContext(), client_server.class);
			startActivity(dl);
		}
	});
  exit.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 myBluetoothAdapter.disable(); 
finish();
		}
	});
  send.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent d1l =new Intent(getApplicationContext(), chat.class);
			startActivity(d1l);
//finish();
		}
	});
  check.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent d1l =new Intent(getApplicationContext(), Notion.class);
			startActivity(d1l);
//finish();
		}
	});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
    @Override
    protected void onStart() {
        super.onStart();
        //mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
       // Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
       // mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
       // Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStop() {
        super.onStop();
     //   mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
    }
    private boolean doubleBackToExitPressedOnce = false;

	public void onBackPressed() {
		
	    if (doubleBackToExitPressedOnce) {
	    	return;
	    }

	    this.doubleBackToExitPressedOnce = true;
	    myBluetoothAdapter.disable(); 
	   
	    new Handler().postDelayed(new Runnable() {

	        public void run() {
	            doubleBackToExitPressedOnce=false;                
	            
	        }
	    }, 2000);
	} 
	private void getvalue(int j) {
		// TODO Auto-generated method stub
		
		String result = null;
	   	InputStream is = null;
	   	String v1 = String.valueOf(j);
	  // 	String v2 = E_pass.getText().toString();
	   	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	   	nameValuePairs.add(new BasicNameValuePair("f1",st.toString()));
	   nameValuePairs.add(new BasicNameValuePair("f2","pass".toString()));
	  	//nameValuePairs.add(new BasicNameValuePair("f3",v3));

	   	StrictMode.setThreadPolicy(policy); 


//http post
try{
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://alldatabasesite.freeiz.com/tic_reg/insert.php");
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost); 
        HttpEntity entity = response.getEntity();
        is = entity.getContent();

        Log.e("log_tag", "connection success ");
        //toast.makeText(, "pass", //toast.LENGTH_SHORT).show();
   }


catch(Exception e)
{
        Log.e("log_tag", "Error in http connection "+e.toString());
        //toast.makeText(, "Connection fail", //toast.LENGTH_SHORT).show();

}
//convert response to string
try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) 
        {
                sb.append(line + "\n");
       	       
        }
        is.close();

        result=sb.toString();
}
catch(Exception e)
{
       Log.e("log_tag", "Error converting result "+e.toString());
	}


try{

	
	 JSONObject object = new JSONObject(result);
        String ch=object.getString("re");
    //  Toast.makeText(getApplicationContext(), ch+link.toString(), Toast.LENGTH_LONG).show();
     
     	   
     	   
     	   
     	   
     	   

     		 object = new JSONObject(result);
     	         ch=object.getString("re");
     	       
     	     	  
     	     	if(ch.equals("Record is repeated"))
     	     	{
     	     		 no = object.getJSONObject("0");
        	          	String o= no.getString("mac_get");
        	          	if(o!=null)
        	          	{

             	     		check.setVisibility(View.VISIBLE);
        	          	}
        	          	else {

						}
     	     	}
     	 		//Toast.makeText(getApplicationContext(), ch, Toast.LENGTH_LONG).show();

      
     }
catch(JSONException e)
   {
        Log.e("log_tag", "Error parsing data "+e.toString());
        //toast.makeText(, "JsonArray fail", //toast.LENGTH_SHORT).show();
    }
		
		
		
	}
}
