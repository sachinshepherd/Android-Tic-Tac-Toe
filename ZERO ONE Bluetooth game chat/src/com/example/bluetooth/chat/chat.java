package com.example.bluetooth.chat;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@TargetApi(9)
@SuppressLint({ "NewApi", "NewApi" })
public class chat extends Activity {
	ListView lv;
	EditText et;

  
String pass,n,o,p,q,r,t,u,v1,v2,v3;
	Button bt2;
	ArrayList al;
	View view;
	 ListView l1;
	 JSONObject no;

	   	InputStream is = null;
	 @SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	int i=0;
	/*String t1[];
	String d1[];
	int i1[];*/
String st1,st2,macAddress;
ArrayList<String> t1 = new ArrayList<String>();

ArrayList<String> d1 = new ArrayList<String>();
ArrayList<String> num = new ArrayList<String>();

ArrayList<String> i1 = new ArrayList<String>();

int j;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notion);
		 et=(EditText)findViewById(R.id.editText1);
	        bt2=(Button)findViewById(R.id.button2);
	      
	    	WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			 WifiInfo wInfo = wifiManager.getConnectionInfo();
			 macAddress= wInfo.getMacAddress();

	        l1=(ListView)findViewById(R.id.list);
	       
	        bt2.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
			
					j=0;
					opq(v);
				 	 //getvalue(j);
				
				 	// l1.setAdapter(new dataListAdapter(t1,d1,i1));
					 
				}
			});
	        l1.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view, final int position,
	                    long id) {
	            	
	            	
	            	//String  selectedFromList = (String) dat.getItem(position );


	            	 LayoutInflater layoutInflater = (LayoutInflater)getBaseContext()
     		    	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
     		    	    View popupView = layoutInflater.inflate(R.layout.sendmsg, null);  
     		    	             final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
     		    	             popupWindow.setFocusable(true);
     		    	             popupWindow.update();
     		    	             View contentView = popupWindow.getContentView();

     		    	            et =(EditText)contentView.findViewById(R.id.editText1);
     		    	         TextView  tv =(TextView)contentView.findViewById(R.id.textView1);
     		    	         tv.setText("Sent to : "+t1.get(position));
      		    	             Button bd2 = (Button)popupView.findViewById(R.id.button2);
         		    	             Button bd1 = (Button)popupView.findViewById(R.id.button1);
         		    	             bd1.setOnClickListener(new View.OnClickListener() {
										
										public void onClick(View arg0) {
											// TODO Auto-generated method stub

	       		    	                	 popupWindow.dismiss();
										}
									});
     		    	             bd2.setOnClickListener(new Button.OnClickListener(){

     		    	                 @SuppressLint("NewApi")
									public void onClick(View v) {
     		    	                  // TODO Auto-generated method stub
     		    	                	
     		    	               
     		    	                	 String result = null;
     		    		            	   	 v1 =macAddress.toString(); //buerid send to
     		    		            	   	 v2 =t1.get(position);
     		    		            	   	v3="Message Start \n"+v2+" : "+et.getText().toString();
     		    		            	   	
     		    		            	   	
     		    		            		
     		    		            	    
     		    		            	
     		    		            	
     		    		             	
     		    		            	final ProgressDialog p = new ProgressDialog(v.getContext()).show(v.getContext(),"Waiting for Server", "Accessing Data");
     		    		               Thread thread = new Thread()
     		    		               {
     		    		                   @Override
     		    		                   public void run() {
     		    		                        try{
     		    		              	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

         		    		            	 	
         		    		            		nameValuePairs.add(new BasicNameValuePair("f1",macAddress.toString()));
          		    		            	   	nameValuePairs.add(new BasicNameValuePair("f2",v2.toString()));
          		    		            	  	nameValuePairs.add(new BasicNameValuePair("f3",".".toString())); 	
          		    		            	  	nameValuePairs.add(new BasicNameValuePair("f4",v3.toString())); 	
          		    		            	  
         		    		            	   	StrictMode.setThreadPolicy(policy); 


         		    		        		//http post
         		    		            	try{
         		    		            	        HttpClient httpclient = new DefaultHttpClient();
         		    		            	        HttpPost httppost = new HttpPost("http://alldatabasesite.freeiz.com/tic_notic/insert.php");
         		    		            	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
         		    		            	        HttpResponse response = httpclient.execute(httppost); 
         		    		            	        HttpEntity entity = response.getEntity();
         		    		            	        is = entity.getContent();

         		    		            	        Log.e("log_tag", "connection success ");
         		    		            	        Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
         		    		            	   }
         		    		            	
         		    		            	
         		    		            	catch(Exception e)
         		    		            	{
         		    		            	        Log.e("log_tag", "Error in http connection "+e.toString());
         		    		            	        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

         		    		            	}
         		    		            	runOnUiThread(new Runnable() {
     		    		                                   public void run() {
     		    		                                      p.dismiss();
     		    		                                       // bt2.setText("Response from PHP : " +t1.get(2));
     		    		                                        //l1.setAdapter(new dataListAdapter(t1,d1,i1));
     		    		                                     //  l1.setAdapter(new dataListAdapter(t1,d1,i1,tm));
     		    		                                   }
     		    		                               });
     		    		                            
     		    		                        }catch(Exception e){
     		    		                             
     		    		                            runOnUiThread(new Runnable() {
     		    		                               public void run() {
     		    		                                   p.dismiss();
     		    		                               }
     		    		                           });
     		    		                            System.out.println("Exception : " + e.getMessage());
     		    		                        }
     		    		                   }
     		    		               };

     		    		               thread.start();
     		    		               
     		    	                	 popupWindow.dismiss();
     		    	                	 
     		    	                 }}); 
     		    	             
     		    	             popupWindow.showAsDropDown(bt2, -100, 100);
	            	
	            	
      		    	           
	            	
	            }
	        });
	        
	        
	        
	        
	 
	        
	      
		}
		private void getvalue(int j) {
			// TODO Auto-generated method stub
			
			String result = null;
		   	InputStream is = null;
		   	String v1 = String.valueOf(j);
		  // 	String v2 = E_pass.getText().toString();
		   	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		  // 	nameValuePairs.add(new BasicNameValuePair("f0",st.toString()));
		   //	nameValuePairs.add(new BasicNameValuePair("f2",pass));
		  	//nameValuePairs.add(new BasicNameValuePair("f3",v3));

		   	StrictMode.setThreadPolicy(policy); 


	//http post
	try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://alldatabasesite.freeiz.com/tic_reg/Select_all.php");
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
	      Toast.makeText(getApplicationContext(), ch, Toast.LENGTH_LONG).show();
	     
	     	   
	     	   
	     	   
	     	   
	     	   

	     		 object = new JSONObject(result);
	     	         ch=object.getString("re");
	     	       
	     	     	   for(i=0;i<20;i++)
	     	     	   {
	     	     		   String st=String.valueOf(i);
	     	          	   no = object.getJSONObject(st);
	     	          	o= no.getString("mac");
	     	            p= no.getString("name");
	     	            r=no.getString("id");
	     	            t1.add(o.toString());
	     	        	d1.add(p.toString());
	     	        	i1.add("okkkk");
	     	        	num.add(r);
	     	     	   }
	     	 		//long q=object.getLong("f1");
	     	              
	     	             //toast.makeText(getActivity(), o.toString(), //toast.LENGTH_LONG).show();
	     	           
	     	    // editText1.setText(w);
	     	   //String myString = NumberFormat.getInstance().format(e);

	     	    
	     	    //editText2.setText(myString);

	     	     

	 		//long q=object.getLong("f1");
	              
	             //toast.makeText(, o.toString(), //toast.LENGTH_LONG).show();
	           
	    // editText1.setText(w);
	   //String myString = NumberFormat.getInstance().format(e);

	    
	    //editText2.setText(myString);

	     	


	      
	     }
	catch(JSONException e)
	   {
	        Log.e("log_tag", "Error parsing data "+e.toString());
	        //toast.makeText(, "JsonArray fail", //toast.LENGTH_SHORT).show();
	    }
			
			
			
		}
		
		
		void opq(View v)
		{
			 final ProgressDialog p = new ProgressDialog(v.getContext()).show(v.getContext(),"Waiting for Server", "Accessing Data");
             Thread thread = new Thread()
             {
                 @Override
                 public void run() {
                      try{
                    	  HttpPost httppost;

                    	    HttpResponse response;
                    	    HttpClient httpclient;
                    	    String str;
                          httpclient=new DefaultHttpClient();
                          httppost= new HttpPost("http://alldatabasesite.freeiz.com/tic_reg/Select_all.php"); // make sure the url is correct.
                          //add your data
                         // $Edittext_value = $_POST['Edittext_value'];
                          //Execute HTTP Post Request
                         ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                          // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                         
                          
                      	//nameValuePairs.add(new BasicNameValuePair("f0","1".toString()));
                 	 	
                          
                           // $Edittext_value = $_POST['Edittext_value'];
                         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    //     //Execute HTTP Post Request
                          response=httpclient.execute(httppost);
                       
                           


                         
                          ResponseHandler<String> responseHandler = new BasicResponseHandler();
                          final String response1 = httpclient.execute(httppost, responseHandler);
                          JSONObject object = new JSONObject(response1);
                         
                          JSONObject sys  = object.getJSONObject("0");

                          //str = sys.getString("F_name");
                          final String ch=object.getString("re");
//Toast.makeText(getApplicationContext(), ch, Toast.LENGTH_LONG).show();
                         // str=ch; 
                          try 
                          {
                        	  for(i=0;i<20;i++)
       	     	     	   {
       	     	     		   String st=String.valueOf(i);
       	     	          	   no = object.getJSONObject(st);
       	     	          	o= no.getString("mac");
       	     	            //p= no.getString("F_name");
       	     	            r=no.getString("id");
       	     	            t1.add(o.toString());
       	     	        	d1.add(no.getString("name"));
       	     	        	i1.add("okkkk");
       	     	        	num.add(r);
       	     	     	   }
                         	
                            
                          }
                          catch(Exception e)
                          {
                       	   
                          }System.out.println("Response : " + response1);
                          runOnUiThread(new Runnable() {
                                 public void run() {
                                     p.dismiss();
                                     // bt2.setText("Response from PHP : " +t1.get(2));
                                     l1.setAdapter(new dataListAdapter(t1,d1,i1));

                                     // String ch=object.getString("re");
          //  Toast.makeText(getApplicationContext(), ch, Toast.LENGTH_LONG).show();
                                 }
                             });
                          
                      }catch(Exception e){
                           
                          runOnUiThread(new Runnable() {
                             public void run() {
                                 p.dismiss();
                             }
                         });
                          System.out.println("Exception : " + e.getMessage());
                      }
                 }
             };

             thread.start();
		}
		class dataListAdapter extends BaseAdapter {
	        ArrayList<String> Title;
			ArrayList<String> Detail;
	        ArrayList<String> imge; 

	        dataListAdapter() {
	            Title = null;
	            Detail = null;
	            imge=null;
	        }

	        public dataListAdapter(ArrayList<String> t1, ArrayList<String> d1,ArrayList<String> i1) {
	            Title = t1;
	            Detail = d1;
	            imge = i1;

	        }

	        public int getCount() {
	            // TODO Auto-generated method stub
	            return Title.size();
	        }

	        public Object getItem(int arg0) {
	            // TODO Auto-generated method stub
	            return null;
	        }

	        public long getItemId(int position) {
	            // TODO Auto-generated method stub
	            return position;
	        }

	        public View getView(int position, View convertView, ViewGroup parent) {

	            LayoutInflater inflater = getLayoutInflater();
	            View row;
	            row = inflater.inflate(R.layout.cust, parent, false);
	            TextView title, detail;
	            ImageView i1;
	            title = (TextView) row.findViewById(R.id.title);
	            detail = (TextView) row.findViewById(R.id.detail);
	            
	            title.setText(Title.get(position));
	            detail.setText(Detail.get(position));
	           
	            return (row);
	        }
	    }
		

		
	}
	      
