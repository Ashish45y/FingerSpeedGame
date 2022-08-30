package com.example.fingerspeedgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView timertxt;
    private TextView thousand;
    private Button btn;

    private CountDownTimer count;
    private long initialCountDown=60000;
    private int timerinterval=1000;
    private int remainingtime=60;

    private int athousand=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timertxt=findViewById(R.id.txt_timer);
        thousand=findViewById(R.id.txtThousand);
        btn=findViewById(R.id.btnTap);
        thousand.setText(athousand+"");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              athousand--;
              thousand.setText(athousand+"");

            }
        });

        count=new CountDownTimer(initialCountDown,timerinterval) {
            @Override
            public void onTick(long milliUntillFinished) {
                remainingtime=(int)milliUntillFinished/1000;
                timertxt.setText(remainingtime+"");
                if(remainingtime>0&&athousand<=0){
                    Toast.makeText(MainActivity.this,"congrates we won the game",Toast.LENGTH_SHORT);
                }



            }

            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this,"CountDownFinished",Toast.LENGTH_SHORT);
               showAlert("You won the game","would you like to reset the game!");
            }
        };

        count.start();

    }

    private  void resetGame(){
        athousand=1000;
        thousand.setText(Integer.toString(athousand));
        timertxt.setText(remainingtime+"");
        count=new CountDownTimer(initialCountDown,timerinterval) {
            @Override
            public void onTick(long millistofinish) {

                remainingtime =(int)millistofinish/1000;
                timertxt.setText(remainingtime+"");
            }

            @Override
            public void onFinish() {

          showAlert("Finished","Would you like to reset the game!");

            }
        };

    }
    private void showAlert(String title,String massage){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
//set icon

//set title
                .setTitle(title)
//set message
                .setMessage(massage)
//set positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                    }


                })
                .show();
    }

}