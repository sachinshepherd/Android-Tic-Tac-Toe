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
public class Splash extends Activity {
Button single,Double,exit;

int a=0;
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
    
}
