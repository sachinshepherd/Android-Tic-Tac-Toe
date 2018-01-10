package com.example.bluetooth.chat;




import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class oneplayer extends Activity {
	int TimeCounter=60;
	Context context;
     int c[][];
     Timer tm;
     int i, j, k = 0;
     Button b[][],bt,move,paly;
     TextView textView,score;
     AI ai;
     int myscrore=0,yourscore=0,draw=0;
     Animation anim1;


     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.player1);         
score=(TextView)findViewById(R.id.textView2);
bt=(Button)findViewById(R.id.button1);
paly=(Button)findViewById(R.id.Button10);
move=(Button)findViewById(R.id.button2);
move.setBackgroundResource(R.drawable.okkkk);
timm();
move.setTextSize(25);
imagemove();

anim1 = AnimationUtils.loadAnimation(this, R.anim.scale_zoom_in_out ); 
  move.startAnimation(anim1);

paly.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		play();
	}
});
bt.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		finish();
	}
});
   
          setBoard();
     }
     
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          super.onCreateOptionsMenu(menu);
          MenuItem item = menu.add("New Game");
          return true;
     }
     
     public boolean onOptionsItemSelected(MenuItem item) {
          setBoard();
          return true;
     }


     // Set up the game board.
     private void setBoard() {
          ai = new AI();
          b = new Button[4][4];
          c = new int[4][4];


          textView = (TextView) findViewById(R.id.textView1);


          b[1][3] = (Button) findViewById(R.id.Button03);
          b[1][2] = (Button) findViewById(R.id.Button02);
          b[1][1] = (Button) findViewById(R.id.Button01);


          b[2][3] = (Button) findViewById(R.id.Button06);
          b[2][2] = (Button) findViewById(R.id.Button05);
          b[2][1] = (Button) findViewById(R.id.Button04);


          b[3][3] = (Button) findViewById(R.id.Button09);
          b[3][2] = (Button) findViewById(R.id.Button08);
          b[3][1] = (Button) findViewById(R.id.Button07);
         
          for (i = 1; i <= 3; i++) {
               for (j = 1; j <= 3; j++)
                    c[i][j] = 2;
          }


          textView.setText("Click a button to start.");


          // add the click listeners for each button
          for (i = 1; i <= 3; i++) {
               for (j = 1; j <= 3; j++) {
                    b[i][j].setOnClickListener(new MyClickListener(i, j));
                    if(!b[i][j].isEnabled()) {
                         b[i][j].setText(" ");
                         b[i][j].setEnabled(true);
                    }
               }
          }
     }


     class MyClickListener implements View.OnClickListener {
          int x;
          int y;


          public MyClickListener(int x, int y) {
               this.x = x;
               this.y = y;
          }


          public void onClick(View view) {
               if (b[x][y].isEnabled()) {
                    b[x][y].setEnabled(false);
                    b[x][y].setBackgroundResource(R.drawable.zero);
                    c[x][y] = 0;
                    textView.setText("");
                    if (!checkBoard()) {
                         ai.takeTurn();
                    }
               }
          }
     }


     private class AI {
          public void takeTurn() {
          if(c[1][1]==2 &&
                    ((c[1][2]==0 && c[1][3]==0) ||
                     (c[2][2]==0 && c[3][3]==0) ||
                     (c[2][1]==0 && c[3][1]==0))) {
               markSquare(1,1);
          } else if (c[1][2]==2 &&
                    ((c[2][2]==0 && c[3][2]==0) ||
                     (c[1][1]==0 && c[1][3]==0))) {
               markSquare(1,2);
          } else if(c[1][3]==2 &&
                    ((c[1][1]==0 && c[1][2]==0) ||
                     (c[3][1]==0 && c[2][2]==0) ||
                     (c[2][3]==0 && c[3][3]==0))) {
               markSquare(1,3);
          } else if(c[2][1]==2 &&
                    ((c[2][2]==0 && c[2][3]==0) ||
                     (c[1][1]==0 && c[3][1]==0))){
               markSquare(2,1);
          } else if(c[2][2]==2 &&
                    ((c[1][1]==0 && c[3][3]==0) ||
                     (c[1][2]==0 && c[3][2]==0) ||
                     (c[3][1]==0 && c[1][3]==0) ||
                     (c[2][1]==0 && c[2][3]==0))) {
               markSquare(2,2);
          } else if(c[2][3]==2 &&
                    ((c[2][1]==0 && c[2][2]==0) ||
                     (c[1][3]==0 && c[3][3]==0))) {
               markSquare(2,3);
          } else if(c[3][1]==2 &&
                    ((c[1][1]==0 && c[2][1]==0) ||
                     (c[3][2]==0 && c[3][3]==0) ||
                     (c[2][2]==0 && c[1][3]==0))){
               markSquare(3,1);
          } else if(c[3][2]==2 &&
                    ((c[1][2]==0 && c[2][2]==0) ||
                     (c[3][1]==0 && c[3][3]==0))) {
               markSquare(3,2);
          }else if( c[3][3]==2 &&
                    ((c[1][1]==0 && c[2][2]==0) ||
                     (c[1][3]==0 && c[2][3]==0) ||
                     (c[3][1]==0 && c[3][2]==0))) {
               markSquare(3,3);
          } else {
               Random rand = new Random();
               
               int a = rand.nextInt(4);
               int b = rand.nextInt(4);
               while(a==0 || b==0 || c[a][b]!=2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
               }
               markSquare(a,b);
          }
     }


          private void markSquare(int x, int y) {
               b[x][y].setEnabled(false);
               b[x][y].setBackgroundResource(R.drawable.crose);
               c[x][y] = 1;
               checkBoard();
          }
     }


     // check the board to see if someone has won
     private boolean checkBoard() {
          boolean gameOver = false;
          
               
              
               if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                    || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                    || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                    || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                    || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                    || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                    || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                    || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            	   
            	   
             	   if(c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0){b[1][1].setBackgroundResource(R.drawable.zerored); b[2][2].setBackgroundResource(R.drawable.zerored);  b[3][3].setBackgroundResource(R.drawable.zerored);}
                   if     (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0){b[1][3].setBackgroundResource(R.drawable.zerored); b[2][2].setBackgroundResource(R.drawable.zerored);  b[3][1].setBackgroundResource(R.drawable.zerored);}
                     if   (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0){b[1][2].setBackgroundResource(R.drawable.zerored); b[2][2].setBackgroundResource(R.drawable.zerored);  b[3][2].setBackgroundResource(R.drawable.zerored);}
                      if  (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0){b[1][3].setBackgroundResource(R.drawable.zerored); b[2][3].setBackgroundResource(R.drawable.zerored);  b[3][3].setBackgroundResource(R.drawable.zerored);}
                       if (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0){b[1][1].setBackgroundResource(R.drawable.zerored); b[1][2].setBackgroundResource(R.drawable.zerored);  b[1][3].setBackgroundResource(R.drawable.zerored);}
                        if(c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0){b[2][1].setBackgroundResource(R.drawable.zerored); b[2][2].setBackgroundResource(R.drawable.zerored);  b[2][3].setBackgroundResource(R.drawable.zerored);}
                       if (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0){b[3][1].setBackgroundResource(R.drawable.zerored); b[3][2].setBackgroundResource(R.drawable.zerored);  b[3][3].setBackgroundResource(R.drawable.zerored);}
                        if(c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0){b[1][1].setBackgroundResource(R.drawable.zerored); b[2][1].setBackgroundResource(R.drawable.zerored);  b[3][1].setBackgroundResource(R.drawable.zerored);}
              
                       
            	   
        	    b[1][3].setEnabled(false);
                b[1][2].setEnabled(false);// = (Button) findViewById(R.id.Button02);
                b[1][1].setEnabled(false);// = (Button) findViewById(R.id.Button03);


                b[2][3].setEnabled(false);// = (Button) findViewById(R.id.Button04);
                b[2][2].setEnabled(false);// = (Button) findViewById(R.id.Button05);
                b[2][1].setEnabled(false);// = (Button) findViewById(R.id.Button06);


                b[3][3].setEnabled(false);// = (Button) findViewById(R.id.Button07);
                b[3][2].setEnabled(false);// = (Button) findViewById(R.id.Button08);
                b[3][1].setEnabled(false);// = (Button) findViewById(R.id.Button09);
               textView.setText("Game over. You win!");
               myscrore++;
               tm.cancel();
               
               score.setText("--Score-- \n"+"Win : "+String.valueOf(myscrore)+"\n"+"Lose: "+String.valueOf(yourscore)+"\n"+"Draw: "+String.valueOf(draw));
               gameOver = true;
               

allnew(textView.getText().toString());
               
          }
               	
               
               
               else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                    || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                    || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                    || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                    || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                    || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                    || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                    || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
        	  
        	   
            		if(c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1){b[1][1].setBackgroundResource(R.drawable.crosered); b[2][2].setBackgroundResource(R.drawable.crosered);  b[3][3].setBackgroundResource(R.drawable.crosered);}
                    if     (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1){b[1][3].setBackgroundResource(R.drawable.crosered); b[2][2].setBackgroundResource(R.drawable.crosered);  b[3][1].setBackgroundResource(R.drawable.crosered);}
                      if   (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1){b[1][2].setBackgroundResource(R.drawable.crosered); b[2][2].setBackgroundResource(R.drawable.crosered);  b[3][2].setBackgroundResource(R.drawable.crosered);}
                       if  (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1){b[1][3].setBackgroundResource(R.drawable.crosered); b[2][3].setBackgroundResource(R.drawable.crosered);  b[3][3].setBackgroundResource(R.drawable.crosered);}
                        if (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1){b[1][1].setBackgroundResource(R.drawable.crosered); b[1][2].setBackgroundResource(R.drawable.crosered);  b[1][3].setBackgroundResource(R.drawable.crosered);}
                         if(c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1){b[2][1].setBackgroundResource(R.drawable.crosered); b[2][2].setBackgroundResource(R.drawable.crosered);  b[2][3].setBackgroundResource(R.drawable.crosered);}
                        if (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1){b[3][1].setBackgroundResource(R.drawable.crosered); b[3][2].setBackgroundResource(R.drawable.crosered);  b[3][3].setBackgroundResource(R.drawable.crosered);}
                         if(c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1){b[1][1].setBackgroundResource(R.drawable.crosered); b[2][1].setBackgroundResource(R.drawable.crosered);  b[3][1].setBackgroundResource(R.drawable.crosered);}
              
                    
            	   
            	   b[1][3].setEnabled(false);
               b[1][2].setEnabled(false);// = (Button) findViewById(R.id.Button02);
               b[1][1].setEnabled(false);// = (Button) findViewById(R.id.Button03);


               b[2][3].setEnabled(false);// = (Button) findViewById(R.id.Button04);
               b[2][2].setEnabled(false);// = (Button) findViewById(R.id.Button05);
               b[2][1].setEnabled(false);// = (Button) findViewById(R.id.Button06);


               b[3][3].setEnabled(false);// = (Button) findViewById(R.id.Button07);
               b[3][2].setEnabled(false);// = (Button) findViewById(R.id.Button08);
               b[3][1].setEnabled(false);// = (Button) findViewById(R.id.Button09);
             
               textView.setText("Game over. You lost!");
               gameOver = true;
               yourscore++;
               tm.cancel();
               
               score.setText("--Score--\n"+"Win : "+String.valueOf(myscrore)+"\n"+"Lose: "+String.valueOf(yourscore)+"\n"+"Draw: "+String.valueOf(draw));
allnew(textView.getText().toString());
          } else {
               boolean empty = false;
               for(i=1; i<=3; i++) {
                    for(j=1; j<=3; j++) {
                         if(c[i][j]==2) {
                              empty = true;
                              break;
                         }
                    }
               }
               if(!empty) {
                    gameOver = true;
                    textView.setText("Game over. It's a draw!");
draw++;
tm.cancel();
     score.setText("--Score--\n"+"Win : "+String.valueOf(myscrore)+"\n"+"Lose: "+String.valueOf(yourscore)+"\n"+"Draw: "+String.valueOf(draw));
allnew(textView.getText().toString());
               }
          }
          return gameOver;
          
          
          
          
          
          
          
     }

	public void allnew(String str) {
		// TODO Auto-generated method stub
       	AlertDialog alertDialog = new AlertDialog.Builder(
                oneplayer.this).create();

// Setting Dialog Title
alertDialog.setTitle("Alert Dialog");

// Setting Dialog Message
alertDialog.setMessage(str);

// Setting Icon to Dialog
//alertDialog.setIcon(R.drawable.n1);

// Setting OK Button
alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int which) {
    // Write your code here to execute after dialog closed
    	play();
    	
    }
});


// Showing Alert Message
alertDialog.show();
	}
     void play(){
    	 TimeCounter=60;
    	 tm.cancel();
    	 timm();
    	 c[1][1]=2;
    	 c[1][2]=2;
    	 c[1][3]=2;
    	 c[2][1]=2;
    	 c[2][2]=2;
    	 c[2][3]=2;
    	 c[3][1]=2;
    	 c[3][2]=2;
    	 c[3][3]=2;
    	 b[1][3].setEnabled(true);
         b[1][2].setEnabled(true);// = (Button) findViewById(R.id.Button02);
         b[1][1].setEnabled(true);// = (Button) findViewById(R.id.Button03);


         b[2][3].setEnabled(true);// = (Button) findViewById(R.id.Button04);
         b[2][2].setEnabled(true);// = (Button) findViewById(R.id.Button05);
         b[2][1].setEnabled(true);// = (Button) findViewById(R.id.Button06);


         b[3][3].setEnabled(true);// = (Button) findViewById(R.id.Button07);
         b[3][2].setEnabled(true);// = (Button) findViewById(R.id.Button08);
         b[3][1].setEnabled(true);
         
         b[1][3].setBackgroundResource(R.drawable.all);
         b[1][2].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button02);
         b[1][1].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button03);


         b[2][3].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button04);
         b[2][2].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button05);
         b[2][1].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button06);


         b[3][3].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button07);
         b[3][2].setBackgroundResource(R.drawable.all);// = (Button) findViewById(R.id.Button08);
         b[3][1].setBackgroundResource(R.drawable.all);
    	
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
    	                	  
    	                	  TimeCounter=60;
    	                  }
    	                  
    	             }
    	         });

    	     }
    	 }, 1000, 1000);
    	
    	
    	 
     }
}
