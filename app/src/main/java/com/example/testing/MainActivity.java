package com.example.testing;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 1800000;

    private TextView mTextViewCountDown;
    private Button mButtonStart;
    private Button mButtonReset;
    private Button mButtonClaim;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;  //default 30 mins

    static final String[] images = {"cat1","pusheen","tom"};
    static boolean[] found = {false,false,false};

    Dialog pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pop = new Dialog(this);
        final Spinner time_select = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_select.setAdapter(myAdapter);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStart = findViewById(R.id.btn_start);
        mButtonClaim = findViewById(R.id.claim_btn);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = time_select.getSelectedItem().toString();
                switch (text){
                    case "60:00":
                        mTimeLeftInMillis = 3600000;
                        break;
                    case "90:00":
                        mTimeLeftInMillis = 5400000;
                        break;
                    case "00:05":
                        mTimeLeftInMillis = 5000;
                        break;
                    default:
                }
                mButtonStart.setVisibility(View.INVISIBLE);
                time_select.setVisibility(View.INVISIBLE);
                startTimer();
            }
        });


        updateCountDownText();
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //popup? about the cat image
                mTextViewCountDown.setText("Congrats!");
                mButtonClaim.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    private void updateCountDownText() {
        int min = (int) (mTimeLeftInMillis / 1000) / 60;
        int sec = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d", min, sec);
        mTextViewCountDown.setText(timeLeftFormat);

        System.out.println("Hello");
    }

    public void ShowPopup(View v){
        TextView txtclose;
        Button btn_home;
        Button btn_collect;
        pop.setContentView(R.layout.custom_popup);
        ImageView cat = (ImageView) pop.findViewById(R.id.cat_img);
        TextView cat_label = (TextView) pop.findViewById(R.id.cat_label);
        Random rand = new Random();
        int i = rand.nextInt(images.length);

        switch(images[i]){
            case "cat1":
                cat.setImageResource(R.drawable.cat_1);
                cat_label.setText("You found a cute cat!");
                found[i] = true;
                break;
            case "pusheen":
                cat.setImageResource(R.drawable.cat2);
                cat_label.setText("You found a pusheen!");
                found[i] = true;
                break;
            case "tom":
                cat.setImageResource(R.drawable.cat3);
                cat_label.setText("You found a praying cat!");
                found[i] = true;
                break;
            default:
        }


        txtclose = (TextView) pop.findViewById(R.id.pop_close);
        btn_home = (Button) pop.findViewById(R.id.btn_home);
        btn_collect = (Button) pop.findViewById(R.id.btn_collect);
        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Collections.class);
                startActivity(intent);
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });
        //cat1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.show();
    }


}

