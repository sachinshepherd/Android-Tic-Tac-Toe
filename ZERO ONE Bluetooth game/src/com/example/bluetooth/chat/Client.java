package com.example.bluetooth.chat;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "ParserError", "ParserError" })
public class Client extends Activity {
	
	private boolean CONTINUE_READ_WRITE = true;
	Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,play,exit,send,move;
	int b1,b2,b3,b4,b5,b6,b7,b8,b9,TimeCounter=100;
	int s1,s2,s3,s4,s5,s6,s7,s8,s9;
	String count=" ",count1;
	ScrollView sc;
	Timer tm;
	Gallery g,g1,g2,g3,g4,g5,g6,g7,g8,g9;
	String gole="01",msg,a="0";
EditText et;
TextView tv,tv2;
private BluetoothAdapter myBluetoothAdapter;
private static final int REQUEST_ENABLE_BT = 1;
	TextView result1;
	int num=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new1);
		registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
		creTE();

		move.setBackgroundResource(R.drawable.okkkk);
		move.setTextSize(25);
		move.setText("100");
		imagemove();
        
		g1.setVisibility(View.INVISIBLE);
		g2.setVisibility(View.INVISIBLE);
		g3.setVisibility(View.INVISIBLE);
		g4.setVisibility(View.INVISIBLE);
		g5.setVisibility(View.INVISIBLE);
		g6.setVisibility(View.INVISIBLE);
		g7.setVisibility(View.INVISIBLE);
		g8.setVisibility(View.INVISIBLE);
		g9.setVisibility(View.INVISIBLE);
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if(adapter != null && adapter.isDiscovering()){
			adapter.cancelDiscovery();
		}
		adapter.startDiscovery();
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try{unregisterReceiver(discoveryResult);}catch(Exception e){e.printStackTrace();}
		if(socket != null){
			try{
				is.close();
				os.close();
				socket.close();
				CONTINUE_READ_WRITE = false;
			}catch(Exception e){}
		}
	}
	
	private BluetoothSocket socket;
	private OutputStreamWriter os;
	private InputStream is;
	private BluetoothDevice remoteDevice;
	private BroadcastReceiver discoveryResult = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			android.util.Log.e("TrackingFlow", "WWWTTTFFF");
			unregisterReceiver(this);
			remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			new Thread(reader).start();
		}
	};
	
	private Runnable reader = new Runnable() {
		
		public void run() {
			try {
				android.util.Log.e("TrackingFlow", "Found: " + remoteDevice.getName());
				UUID uuid = UUID.fromString("4e5d48e0-75df-11e3-981f-0800200c9a66");
				socket = remoteDevice.createRfcommSocketToServiceRecord(uuid);
				socket.connect();
				android.util.Log.e("TrackingFlow", "Connected...");
				os = new OutputStreamWriter(socket.getOutputStream());
				is = socket.getInputStream();
				android.util.Log.e("TrackingFlow", "WWWTTTFFF34243");
				new Thread(writter).start();
				android.util.Log.e("TrackingFlow", "WWWTTTFFF3wwgftggggwww4243: " + CONTINUE_READ_WRITE);
				int bufferSize = 1024;
				int bytesRead = -1;
				byte[] buffer = new byte[bufferSize];
				//Keep reading the messages while connection is open...
				while(CONTINUE_READ_WRITE){
					android.util.Log.e("TrackingFlow", "WWWTTTFFF3wwwww4243");
					final StringBuilder sb = new StringBuilder();
					bytesRead = is.read(buffer);
					if (bytesRead != -1) {
					String result = "";
					while ((bytesRead == bufferSize) && (buffer[bufferSize-1] != 0)){
						result = result + new String(buffer, 0, bytesRead - 1);
						bytesRead = is.read(buffer);
						}
						result = result + new String(buffer, 0, bytesRead - 1);
						sb.append(result);
					}
					
					android.util.Log.e("TrackingFlow", "Read: " + sb.toString());
					
					//Show message on UIThread
					runOnUiThread(new Runnable() {	
						public void run() {
							
							
							all(sb.toString());
							String s="n";
							//if(String.valueOf(gole).equals("1")){
							s=sb.toString();
							if(sb.toString().equals("Message From Serverexit") || 
									sb.toString().equals("Message From Serverplay")||
									sb.toString().equals("Message From Server1")||
									sb.toString().equals("Message From Server2")||
									sb.toString().equals("Message From Server3")||
									sb.toString().equals("Message From Server4")||
									sb.toString().equals("Message From Server5")||
									sb.toString().equals("Message From Server6")||
									sb.toString().equals("Message From Server7")||
									sb.toString().equals("Message From Server8")||
									sb.toString().equals("Message From Server9")
									)
							{
								enable();
							}
							else{
								
								msg=msg+"\nYou :"+sb.toString();
								tv.setText(msg);
								sc.post(new Runnable() { 
						            public void run() { 
						                sc.fullScroll(ScrollView.FOCUS_DOWN); 
						            } 
						        }); 
						        sc.scrollBy(0, 1000000);
						        sc.scrollTo(0, sc.getBottom());
							}
							//}
							//all(s);
							if(sb.toString().equals("Connected Server") ||sb.toString().equals("Connected Serve") || sb.toString().equals("Message From Serverplay")){
							Toast.makeText(Client.this, s.toString(), Toast.LENGTH_SHORT).show();
							timm();
							}
						a="1";
						
						}
					});
				}
			} catch (IOException e) {e.printStackTrace();}
		}
	};
	private void all(final String aaaa){
	     creTE();
	     if(aaaa.toString().equals("Message From Serverexit")){
	    	// exit();
	    	 
	    	 finish();
	    	 
	     }
	     if(aaaa.toString().equals("Message From Serverplay")){
	    	 playbotn();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server1")){
	    	 bton1();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server2")){
	    	 bton2();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server3")){
	    	 bton3();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server4")){
	    	 bton4();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server5")){
	    	 bton5();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server6")){
	    	 bton6();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server7")){
	    	 bton7();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server8")){
	    	 bton8();
	    	 ;
	     }
	     if(aaaa.toString().equals("Message From Server9")){
	    	 bton9();
	    	 ;
	     }
	        exit.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
				exit();
				myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		    	  myBluetoothAdapter.disable();
					count="exit"; 
    				gole="1";
    				
				}
			});
	        play.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					playbotn();
					count="play"; 
    				gole="1";
					 
				}
			});
	        bt1.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    				bton1();
	    				//Toast.makeText(getApplicationContext(), aaaa.toString(), Toast.LENGTH_SHORT).show();
	    				count="1";
	    				gole="1";
	    				s1=1;
	    					disable(); // TODO Auto-generated method stub
	    				}

	    			
	    		
	    		});
	        bt2.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton2();
	    				gole="1";
	    				count="2";
	    				s2=1;
	    			}
	    		});
	        bt3.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton3();
	    				gole="1";
	    				count="3";
	    				s3=1;
	    			}
	    		});
	        bt4.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton4();
	    				gole="1";
	    				count="4";
	    				s4=1;
	    			}
	    		});
	        bt5.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton5();
	    				gole="1";
	    				count="5";
	    			
	    			}
	    		});
	        bt6.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton6();
	    				gole="1";
	    				count="6";
	    			}
	    		});
	        bt7.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton7();
	    				gole="1";
	    				count="7";
	    			}
	    		});
	        bt8.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton8();
	    				gole="1";
	    				count="8";
	    			}
	    		});
	        bt9.setOnClickListener(new View.OnClickListener() {
				
	    			public void onClick(View v) {
	    					disable(); // TODO Auto-generated method stub
	    				bton9();
	    				gole="1";
	    				count="9";
	    				
	    			}
	    		});
	        send.setEnabled(false);
	        send.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					gole="sn";
					count1=et.getText().toString();
					msg=msg+"\nMe :"+count1.toString();
					tv.setText(msg);
					
					et.setText("");
					sc.post(new Runnable() { 
			            public void run() { 
			                sc.fullScroll(ScrollView.FOCUS_DOWN); 
			            } 
			        }); 
			        sc.scrollBy(0, 1000000);
			        sc.scrollTo(0, sc.getBottom());
					
				}
			});
	}
	void exit() {
		 final Timer timer = new Timer();
         timer.schedule((TimerTask)(new TimerTask(){
             

             public void run() {
            	 finish();
 				android.os.Process.killProcess(android.os.Process.myPid());
 			     System.exit(1);
                
                }
         }), 2000);
		
	}
	
	private Runnable writter = new Runnable() {

		@SuppressLint("ParserError")
		public void run() {
			
			while (CONTINUE_READ_WRITE)  {
				try {
					//gole="0";
					if(gole.toString().equals("01")){
						os.write("Connected Client " );
						gole="0";
						os.flush();
						Thread.sleep(2000);
					}
					if(gole.toString().equals("1")){
						gole="nnnnnn";
						os.write("Message From client"+count.toString()+"\n");
						gole="0";
						os.flush();
						Thread.sleep(2000);
					}
					if(gole.toString().equals("sn")){
						os.write(count1.toString()+"\n");
						gole="0";
						os.flush();
						Thread.sleep(2000);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

    private void bton1() {
		// TODO Auto-generated method stub

    	bt1.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt1.setBackgroundResource(R.drawable.zero);
			
			g1.setVisibility(View.VISIBLE);
			g1.setBackgroundResource(R.drawable.zero);
		num++;
		b1=2;}
		else
		{

			bt1.setBackgroundResource(R.drawable.crose);
			g1.setVisibility(View.VISIBLE);
			g1.setBackgroundResource(R.drawable.crose);
			num++;
			b1=1;
		}
		right();bt1.setEnabled(false);
	}
	private void bton2() {
		 //TODO Auto-generated method stub
		bt2.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt2.setBackgroundResource(R.drawable.zero);
			g2.setVisibility(View.VISIBLE);
			g2.setBackgroundResource(R.drawable.zero);
			num++;
			b2=2;}
		else
		{

			bt2.setBackgroundResource(R.drawable.crose);
			g2.setVisibility(View.VISIBLE);
			g2.setBackgroundResource(R.drawable.crose);
			num++;
			b2=1;
		}
		right();bt2.setEnabled(false);
	}
	private void bton3() {
		 //TODO Auto-generated method stub
		bt3.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt3.setBackgroundResource(R.drawable.zero);
			g3.setVisibility(View.VISIBLE);
			g3.setBackgroundResource(R.drawable.zero);
		num++;
			b3=2;}
		else
		{

			bt3.setBackgroundResource(R.drawable.crose);
			num++;
		
			g3.setVisibility(View.VISIBLE);
			g3.setBackgroundResource(R.drawable.crose);
			b3=1;
		}
		
		right();bt3.setEnabled(false);
	}
	private void bton4() {
		 //TODO Auto-generated method stub
		bt4.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt4.setBackgroundResource(R.drawable.zero);
		num++;
		g4.setVisibility(View.VISIBLE);
		g4.setBackgroundResource(R.drawable.zero);
		b4=2;}
		else
		{

			bt4.setBackgroundResource(R.drawable.crose);
			num++;
			g4.setVisibility(View.VISIBLE);
			g4.setBackgroundResource(R.drawable.crose);
			b4=1;}
		right();bt4.setEnabled(false);;
	}
	private void bton5() {
		 //TODO Auto-generated method stub
		
		bt5.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt5.setBackgroundResource(R.drawable.zero);
			g5.setVisibility(View.VISIBLE);
			g5.setBackgroundResource(R.drawable.zero);
		num++;
		b5=2;}
		else
		{

			bt5.setBackgroundResource(R.drawable.crose);
			num++;
			g5.setVisibility(View.VISIBLE);
			g5.setBackgroundResource(R.drawable.crose);
			b5=1;
		}
		right();bt5.setEnabled(false);;
	}
	private void bton6() {
		 //TODO Auto-generated method stu6
		bt6.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt6.setBackgroundResource(R.drawable.zero);
		num++;
		g6.setVisibility(View.VISIBLE);
		g6.setBackgroundResource(R.drawable.zero);
		b6=2;}
		else
		{

			bt6.setBackgroundResource(R.drawable.crose);
			g6.setVisibility(View.VISIBLE);
			g6.setBackgroundResource(R.drawable.crose);
			num++;
			b6=1;
		}
		right();bt6.setEnabled(false);
	}
	private void bton7() {
		 //TODO Auto-generated method stub
		bt7.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt7.setBackgroundResource(R.drawable.zero);
		num++;
		g7.setVisibility(View.VISIBLE);
		g7.setBackgroundResource(R.drawable.zero);
		b7=2;}
		else
		{

			bt7.setBackgroundResource(R.drawable.crose);
			num++;
			g7.setVisibility(View.VISIBLE);
			g7.setBackgroundResource(R.drawable.crose);
			b7=1;
		}
		right();bt7.setEnabled(false);
	}
	private void bton8() {
		 //TODO Auto-generated method stub
		bt8.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt8.setBackgroundResource(R.drawable.zero);
		num++;
		g8.setVisibility(View.VISIBLE);
		g8.setBackgroundResource(R.drawable.zero);
		
		b8=2;}
		else
		{

			bt8.setBackgroundResource(R.drawable.crose);
			num++;
			g8.setVisibility(View.VISIBLE);
			g8.setBackgroundResource(R.drawable.crose);
			b8=1;
		}
		right();bt8.setEnabled(false);
	}
	private void bton9() {
		 //TODO Auto-generated method stub
		bt9.setVisibility(View.INVISIBLE);
		if(num%2==0){
			bt9.setBackgroundResource(R.drawable.zero);
			g9.setVisibility(View.VISIBLE);
			g9.setBackgroundResource(R.drawable.zero);
			num++;
		b9=2;}
		else
		{

			bt9.setBackgroundResource(R.drawable.crose);
			g9.setVisibility(View.VISIBLE);
			g9.setBackgroundResource(R.drawable.crose);
			num++;
			b9=1;
		}
		right();bt9.setEnabled(false);
	}
	private void playbotn(){
		bt1.setVisibility(View.VISIBLE);
		bt2.setVisibility(View.VISIBLE);
		bt3.setVisibility(View.VISIBLE);
		bt4.setVisibility(View.VISIBLE);
		bt5.setVisibility(View.VISIBLE);
		bt6.setVisibility(View.VISIBLE);
		bt7.setVisibility(View.VISIBLE);
		bt8.setVisibility(View.VISIBLE);
		bt9.setVisibility(View.VISIBLE);
		g1.setVisibility(View.INVISIBLE);
		g2.setVisibility(View.INVISIBLE);
		g3.setVisibility(View.INVISIBLE);
		g4.setVisibility(View.INVISIBLE);
		g5.setVisibility(View.INVISIBLE);
		g6.setVisibility(View.INVISIBLE);
		g7.setVisibility(View.INVISIBLE);
		g8.setVisibility(View.INVISIBLE);
		g9.setVisibility(View.INVISIBLE);
		tm.cancel();
		TimeCounter=100;
		timm();
	bt1.setBackgroundResource(R.drawable.all);
	bt2.setBackgroundResource(R.drawable.all);
	bt3.setBackgroundResource(R.drawable.all);
	bt4.setBackgroundResource(R.drawable.all);
	bt5.setBackgroundResource(R.drawable.all);
	bt6.setBackgroundResource(R.drawable.all);
	bt7.setBackgroundResource(R.drawable.all);
	bt8.setBackgroundResource(R.drawable.all);
	bt9.setBackgroundResource(R.drawable.all);
	sc.post(new Runnable() { 
        public void run() { 
            sc.fullScroll(ScrollView.FOCUS_DOWN); 
        } 
    }); 
    sc.scrollBy(0, 1000000);
    sc.scrollTo(0, sc.getBottom());
	
	g1.setBackgroundResource(R.drawable.all);
	g2.setBackgroundResource(R.drawable.all);
	g3.setBackgroundResource(R.drawable.all);
	g4.setBackgroundResource(R.drawable.all);
	g5.setBackgroundResource(R.drawable.all);
	g6.setBackgroundResource(R.drawable.all);
	g7.setBackgroundResource(R.drawable.all);
	g8.setBackgroundResource(R.drawable.all);
	g9.setBackgroundResource(R.drawable.all);
	bt1.setEnabled(true);bt2.setEnabled(true);bt3.setEnabled(true);bt4.setEnabled(true);bt5.setEnabled(true);bt6.setEnabled(true);bt7.setEnabled(true);bt8.setEnabled(true);bt9.setEnabled(true);
	num=0;b1=0;b2=0;b3=0;b4=0;b5=0;b6=0;b7=0;b8=0;b9=0;
	}
	private void creTE(){
		 bt1=(Button)findViewById(R.id.Button01);
	        bt2=(Button)findViewById(R.id.Button02);
	        bt3=(Button)findViewById(R.id.Button03);
	        bt4=(Button)findViewById(R.id.Button04);
	        bt5=(Button)findViewById(R.id.Button05);
	        bt6=(Button)findViewById(R.id.Button06);
	        bt7=(Button)findViewById(R.id.Button07);
	        bt8=(Button)findViewById(R.id.Button08);
	        bt9=(Button)findViewById(R.id.Button09);
	        play=(Button)findViewById(R.id.button1);
	        exit=(Button)findViewById(R.id.button2);
	        move=(Button)findViewById(R.id.button4);

	        g1=(Gallery)findViewById(R.id.Gallery01);
	        g2=(Gallery)findViewById(R.id.Gallery02);
	        g3=(Gallery)findViewById(R.id.Gallery03);
	        g4=(Gallery)findViewById(R.id.Gallery04);
	        g5=(Gallery)findViewById(R.id.Gallery05);
	        g6=(Gallery)findViewById(R.id.Gallery06);
	        g7=(Gallery)findViewById(R.id.Gallery07);
	        g8=(Gallery)findViewById(R.id.Gallery08);
	        g9=(Gallery)findViewById(R.id.Gallery09);
	        et=(EditText)findViewById(R.id.editText1);
	        tv=(TextView)findViewById(R.id.textView1);
	        send=(Button)findViewById(R.id.button3);

	        sc=(ScrollView)findViewById(R.id.scrollView1);
	        
	}
	public void disable(){
		bt1.setEnabled(false);bt2.setEnabled(false);bt3.setEnabled(false);bt4.setEnabled(false);bt5.setEnabled(false);bt6.setEnabled(false);bt7.setEnabled(false);bt8.setEnabled(false);bt9.setEnabled(false);	
		
	}
	public void enable(){
		bt1.setEnabled(true);bt2.setEnabled(true);bt3.setEnabled(true);bt4.setEnabled(true);bt5.setEnabled(true);bt6.setEnabled(true);bt7.setEnabled(true);bt8.setEnabled(true);bt9.setEnabled(true);	
		
	}

	public void right() {

		// TODO Auto-generated method stub
		if(b1==2 && b2==2 &&b3==2 || b1==2 && b5==2 &&b9==2 || b1==2 && b4==2 &&b7==2 ||b5==2 && b2==2 &&b8==2 ||b3==2 && b6==2 &&b9==2 ||b3==2 && b5==2 &&b7==2 ||b4==2 && b5==2 &&b6==2 ||b7==2 && b8==2 &&b9==2){
			
			
			if(b1==2 && b2==2 &&b3==2){g1.setBackgroundResource(R.drawable.zerored);g2.setBackgroundResource(R.drawable.zerored);g3.setBackgroundResource(R.drawable.zerored);}
			if(b1==2 && b5==2 &&b9==2){g1.setBackgroundResource(R.drawable.zerored);g5.setBackgroundResource(R.drawable.zerored);g9.setBackgroundResource(R.drawable.zerored);}
			if(b1==2 && b4==2 &&b7==2){g1.setBackgroundResource(R.drawable.zerored);g4.setBackgroundResource(R.drawable.zerored);g7.setBackgroundResource(R.drawable.zerored);}
			if(b5==2 && b2==2 &&b8==2){g5.setBackgroundResource(R.drawable.zerored);g2.setBackgroundResource(R.drawable.zerored);g8.setBackgroundResource(R.drawable.zerored);}
			if(b3==2 && b6==2 &&b9==2){g3.setBackgroundResource(R.drawable.zerored);g6.setBackgroundResource(R.drawable.zerored);g9.setBackgroundResource(R.drawable.zerored);}
			if(b3==2 && b5==2 &&b7==2){g3.setBackgroundResource(R.drawable.zerored);g5.setBackgroundResource(R.drawable.zerored);g7.setBackgroundResource(R.drawable.zerored);}
			if(b4==2 && b5==2 &&b6==2){g4.setBackgroundResource(R.drawable.zerored);g5.setBackgroundResource(R.drawable.zerored);g6.setBackgroundResource(R.drawable.zerored);}
			if(b7==2 && b8==2 &&b9==2){g7.setBackgroundResource(R.drawable.zerored);g8.setBackgroundResource(R.drawable.zerored);g9.setBackgroundResource(R.drawable.zerored);}
			all("Win not side");
			Toast.makeText(getApplicationContext(), "Win not side", Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "Press play button", Toast.LENGTH_LONG).show();
			bt1.setEnabled(false);bt2.setEnabled(false);bt3.setEnabled(false);bt4.setEnabled(false);bt5.setEnabled(false);bt6.setEnabled(false);bt7.setEnabled(false);bt8.setEnabled(false);bt9.setEnabled(false);	
			bt1.setVisibility(View.INVISIBLE);g1.setVisibility(View.VISIBLE);
			bt2.setVisibility(View.INVISIBLE);g2.setVisibility(View.VISIBLE);
			bt3.setVisibility(View.INVISIBLE);g3.setVisibility(View.VISIBLE);
			bt4.setVisibility(View.INVISIBLE);g4.setVisibility(View.VISIBLE);
			bt5.setVisibility(View.INVISIBLE);g5.setVisibility(View.VISIBLE);
			bt6.setVisibility(View.INVISIBLE);g6.setVisibility(View.VISIBLE);
			bt7.setVisibility(View.INVISIBLE);g7.setVisibility(View.VISIBLE);
			bt8.setVisibility(View.INVISIBLE);g8.setVisibility(View.VISIBLE);
			bt9.setVisibility(View.INVISIBLE);g9.setVisibility(View.VISIBLE);
		}
		if(b1==1 && b2==1 &&b3==1 || b1==1 && b5==1 &&b9==1 || b1==1 && b4==1 &&b7==1 ||b5==1 && b2==1 &&b8==1 ||b3==1 && b6==1 &&b9==1 ||b3==1 && b5==1 &&b7==1 ||b4==1 && b5==1 &&b6==1 ||b7==1 && b8==1 &&b9==1){
			
		
			if(b1==1 && b2==1 &&b3==1){g1.setBackgroundResource(R.drawable.crosered);g2.setBackgroundResource(R.drawable.crosered);g3.setBackgroundResource(R.drawable.crosered);}
			if(b1==1 && b5==1 &&b9==1){g1.setBackgroundResource(R.drawable.crosered);g5.setBackgroundResource(R.drawable.crosered);g9.setBackgroundResource(R.drawable.crosered);}
			if(b1==1 && b4==1 &&b7==1){g1.setBackgroundResource(R.drawable.crosered);g4.setBackgroundResource(R.drawable.crosered);g7.setBackgroundResource(R.drawable.crosered);}
			if(b5==1 && b2==1 &&b8==1){g5.setBackgroundResource(R.drawable.crosered);g2.setBackgroundResource(R.drawable.crosered);g8.setBackgroundResource(R.drawable.crosered);}
			if(b3==1 && b6==1 &&b9==1){g3.setBackgroundResource(R.drawable.crosered);g6.setBackgroundResource(R.drawable.crosered);g9.setBackgroundResource(R.drawable.crosered);}
			if(b3==1 && b5==1 &&b7==1){g3.setBackgroundResource(R.drawable.crosered);g5.setBackgroundResource(R.drawable.crosered);g7.setBackgroundResource(R.drawable.crosered);}
			if(b4==1 && b5==1 &&b6==1){g4.setBackgroundResource(R.drawable.crosered);g5.setBackgroundResource(R.drawable.crosered);g6.setBackgroundResource(R.drawable.crosered);}
			if(b7==1 && b8==1 &&b9==1){g7.setBackgroundResource(R.drawable.crosered);g8.setBackgroundResource(R.drawable.crosered);g9.setBackgroundResource(R.drawable.crosered);}
			all("Win cross side");
			Toast.makeText(getApplicationContext(), "Win cross side", Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "Press play button", Toast.LENGTH_LONG).show();
			bt1.setEnabled(false);bt2.setEnabled(false);bt3.setEnabled(false);bt4.setEnabled(false);bt5.setEnabled(false);bt6.setEnabled(false);bt7.setEnabled(false);bt8.setEnabled(false);bt9.setEnabled(false);
			bt1.setVisibility(View.INVISIBLE);g1.setVisibility(View.VISIBLE);
			bt2.setVisibility(View.INVISIBLE);g2.setVisibility(View.VISIBLE);
			bt3.setVisibility(View.INVISIBLE);g3.setVisibility(View.VISIBLE);
			bt4.setVisibility(View.INVISIBLE);g4.setVisibility(View.VISIBLE);
			bt5.setVisibility(View.INVISIBLE);g5.setVisibility(View.VISIBLE);
			bt6.setVisibility(View.INVISIBLE);g6.setVisibility(View.VISIBLE);
			bt7.setVisibility(View.INVISIBLE);g7.setVisibility(View.VISIBLE);
			bt8.setVisibility(View.INVISIBLE);g8.setVisibility(View.VISIBLE);
			bt9.setVisibility(View.INVISIBLE);g9.setVisibility(View.VISIBLE);
		}
	}
	private boolean doubleBackToExitPressedOnce = false;

	public void onBackPressed() {
		
	    if (doubleBackToExitPressedOnce) {
	    	return;
	    }

	    this.doubleBackToExitPressedOnce = true;
	    if(a.toString().equals("0")){
	    	finish();
	    }
	    if(a.toString().equals("1")){
	    	 Toast.makeText(this, "Press EXIT button", Toast.LENGTH_SHORT).show();

	    }
	   
	    new Handler().postDelayed(new Runnable() {

	        public void run() {
	            doubleBackToExitPressedOnce=false;                
	            
	        }
	    }, 2000);
	} 
	 @Override
	    protected void onStart() {
	        super.onStart();
	        //onStart();
	        
	        //mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
	        //Utils.printStatus(mStatusView, mStatusAllView);
	    }

	    @Override
	    protected void onRestart() {
	        super.onRestart();
	        //onRestart();
	        //mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
	       // Utils.printStatus(mStatusView, mStatusAllView);
	      
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	       // mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
	        //Utils.printStatus(mStatusView, mStatusAllView);
	  //  onResume();
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();
	       // mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
	       // Utils.printStatus(mStatusView, mStatusAllView);
	    //    onPause();
	    }

	    @Override
	    protected void onStop() {
	        super.onStop();
	     //   mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
	     //   onStop();
	    }

	 
	    void timm(){
	    	
	    	 tm = new Timer();
	    	 tm.scheduleAtFixedRate(new TimerTask() {

	    	     public void run() {
	    	         // TODO Auto-generated method stub
	    	         runOnUiThread(new Runnable() {
	    	             public void run() {
	move.setText(String.valueOf(TimeCounter));
	    	                  TimeCounter--;
	    	                  if(TimeCounter==0){
	    	                	  
	    	                	  TimeCounter=100;
	    	                	  playbotn();
	    	                  }
	    	                  
	    	             }
	    	         });

	    	     }
	    	 }, 1000, 1000);
	    	
	    	
	    	 
	     }
	    private void imagemove() {
	 		// TODO Auto-generated method stub
	         move.setOnTouchListener(new OnTouchListener() {

	             public boolean onTouch(View v, MotionEvent event) {
	                 // TODO Auto-generated method stub
	                 int eid = event.getAction();
	                 switch (eid) {
	                 case MotionEvent.ACTION_MOVE:

	                     RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) move.getLayoutParams();
	                     int x = (int) event.getRawX();
	                     int y = (int) event.getRawY();
	                     mParams.leftMargin = x-50;
	                     mParams.topMargin = y-50;
	                     move.setLayoutParams(mParams);


	                     break;

	                 default:
	                     break;
	                 }
	                 return true;
	             }
	         });
	 	}

}
