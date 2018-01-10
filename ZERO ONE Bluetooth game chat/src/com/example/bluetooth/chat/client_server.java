package com.example.bluetooth.chat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "ParserError", "ParserError" })
public class client_server extends Activity {
Button cln,svr,exit,set,remove;
TextView tv,limit;
EditText et;
String LM="0";
String st,tim;
ScrollView sc;
int a=0;
private BluetoothAdapter myBluetoothAdapter;
private static final int REQUEST_ENABLE_BT = 1;
    @SuppressLint("ParserError")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_server);
       
        final Animation anim9 = AnimationUtils.loadAnimation(this, R.anim.jelly);
/*        tv=(TextView)findViewById(R.id.textView1);
        et=(EditText)findViewById(R.id.editText1);
        sc=(ScrollView)findViewById(R.id.scrollView1);
        
       
        
        svr=(Button)findViewById(R.id.button1);
        sc.post(new Runnable() { 
            public void run() { 
                sc.fullScroll(ScrollView.FOCUS_DOWN); 
            } 
        }); 
        sc.scrollBy(0, 1000000);
        sc.scrollTo(0, sc.getBottom());
        svr.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 Time today = new Time(Time.getCurrentTimezone());
					today.setToNow();

			    
			               
					  tim=today.format("%k:%M:%S");
if(a==0){
	st=st+"\nMe :"+tim.toString()+" "+et.getText().toString();
	a=1;
}
else{

	st=st+"\nYou : "+et.getText().toString();
	a=0;
}
	
	
				
				tv.setText(st.toString());
				et.setText(" ");
				sc.post(new Runnable() { 
		            public void run() { 
		                sc.fullScroll(ScrollView.FOCUS_DOWN); 
		            } 
		        }); 
		        sc.scrollBy(0, 1000000);
		        sc.scrollTo(0, sc.getBottom());

		   }
		});*/
        cln=(Button)findViewById(R.id.button1);
        svr=(Button)findViewById(R.id.button2);
        exit=(Button)findViewById(R.id.button3);
        Button single=(Button)findViewById(R.id.Button01);
        
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!myBluetoothAdapter.isEnabled()) {
        	Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
            String address = myBluetoothAdapter.getAddress();
            Toast.makeText(getApplicationContext(),"Bluetooth turned on" +address,
           		 Toast.LENGTH_LONG).show();
        	 
         }
         else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on",
           		 Toast.LENGTH_LONG).show();
         }
        
        final Button onBtn = (Button)findViewById(R.id.button4);

      
	      onBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				on(v);
				onBtn.setAnimation(anim9);
			}
	      });
	      
	    Button  offBtn = (Button)findViewById(R.id.button5);
	      offBtn.setOnClickListener(new OnClickListener() {
	  		
	  		public void onClick(View v) {
	  			// TODO Auto-generated method stub
	  			off(v);
	  			//v.startAnimation(anim9);
	  		}
	      });
       
        exit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				off(v);
				System.exit(0);
				finish();
			}
		});
        cln.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent cl=new Intent(getApplicationContext(), Client.class);
				startActivity(cl);
				//cln.setAnimation(anim9);
			}
		});
        svr.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sv=new Intent(getApplicationContext(), Server.class);
				startActivity(sv);
				//svr.setAnimation(anim9);
			}
		});
   
    }
    public void on(View view){
        if (!myBluetoothAdapter.isEnabled()) {
           Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
           startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
           String address = myBluetoothAdapter.getAddress();
           Toast.makeText(getApplicationContext(),"Bluetooth turned on" +address,
          		 Toast.LENGTH_LONG).show();
        }
        else{
           Toast.makeText(getApplicationContext(),"Bluetooth is already on",
          		 Toast.LENGTH_LONG).show();
        }
     }
    public void off(View view){
  	  

  	  
        Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_LONG).show();
        myBluetoothAdapter.disable(); 
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
   	  Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_LONG).show();
      
   	   finish();
   	    new Handler().postDelayed(new Runnable() {

   	        public void run() {
   	            doubleBackToExitPressedOnce=false;                
   	            
   	        }
   	    }, 2000);
   	} 
       
}
