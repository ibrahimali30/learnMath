package com.ibrahim.a72appbrain;

import android.nfc.Tag;
import android.os.CountDownTimer;
import android.renderscript.Int3;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

TextView scoretextview ;
TextView resulttextview;
ArrayList<Integer> ansowers;
    TextView sumtextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Random random = new Random();
    int num1;
    int num2;
    int numberofansoweredquestion=0;
    int numberofcorrectansowers=0;
    int correctAnsowerPosition;
    boolean isrunning=false;

    //set next question

    public void setnewquestion(){
         num1 = random.nextInt(21)+1;
         num2 = random.nextInt(21)+1;
         Log.i(Integer.toString(num1),"///"+Integer.toString(num2));
        sumtextview=findViewById(R.id.sumtextView);
        sumtextview.setText(Integer.toString(num1)+" + "+Integer.toString(num2));

        //set the choices

        ansowers=new ArrayList<Integer>();
        correctAnsowerPosition=random.nextInt(4);
        int incorrectansower;
        for (int i=0;i<4;i++){
            if (i==correctAnsowerPosition){
                ansowers.add(num1+num2);
            }else {
                incorrectansower=random.nextInt(41);
                while (incorrectansower==(num1+num2)){
                    incorrectansower=random.nextInt(41);
                }
                ansowers.add(incorrectansower);

            }
        }
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button0.setText(Integer.toString(ansowers.get(0)));
        button1.setText(Integer.toString(ansowers.get(1)));
        button2.setText(Integer.toString(ansowers.get(2)));
        button3.setText(Integer.toString(ansowers.get(3)));


    }

    //get ansowers and update scores
    public void ansower(View view){
        if (isrunning) {
            Button button = (Button) findViewById(view.getId());
            int i = Integer.parseInt(button.getText().toString());
            numberofansoweredquestion++;
            if (i == num1 + num2) {
                numberofcorrectansowers++;
                resulttextview.setText("correct");
            } else {
                resulttextview.setText("incorrect");
            }
            setnewquestion();
            scoretextview.setText(Integer.toString(numberofcorrectansowers) + " / " + Integer.toString(numberofansoweredquestion));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/////////////////////////////////////////////////////////////////////////////////////////
        setnewquestion();
        scoretextview = (TextView) findViewById(R.id.scoretextView);
        resulttextview=(TextView) findViewById(R.id.resulttextview);
        final TextView timretextview = (TextView) findViewById(R.id.timetextView);
         final Button playagain  = (Button) findViewById(R.id.playagain);
         playagain.setVisibility(View.VISIBLE);

        //4*4 ansower button

//        button0 = findViewById(R.id.button0);
//        button1 = findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);



        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playagain.setVisibility(View.INVISIBLE);
                scoretextview.setText("0/0");
                timretextview.setText("30s");
                isrunning = true;
               CountDownTimer countDownTimer= new CountDownTimer(30000,1000){
                    @Override
                    public void onTick(long l) {
                        timretextview.setText(Integer.toString((int) l/1000));
                    }

                    @Override
                    public void onFinish() {
                        playagain.setVisibility(View.VISIBLE);
                        isrunning = false;
                        numberofansoweredquestion=0;
                        numberofcorrectansowers=0;
                    }
                }.start();

            }
        });





    }
}
