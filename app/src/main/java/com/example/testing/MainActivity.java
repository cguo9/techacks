package com.example.testing;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;
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

    static final String[] images = {"tom", "felix","hello_kitty", "pusheen", "garfield", "cat_hat",
    "puss_boots", "chesire", "catdog"};
    static boolean[] found = {false,false,false,false,false,false,false,false,false};
    Spinner time_select;
    Dialog pop;
    WebView web_left;
    WebView web_right;
    protected static boolean isVisible = false;

    @Override
    public void onResume() {
        super.onResume();
        setVisible(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisible(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_left = findViewById(R.id.gif_l);
        web_right = findViewById(R.id.gif_r);
        WebSettings settings_left = web_left.getSettings();
        WebSettings settings_right = web_right.getSettings();
        settings_left.setJavaScriptEnabled(true);
        settings_right.setJavaScriptEnabled(true);
        String file_l = "file:android_asset/cat_right.gif";

        String file_r = "file:android_asset/gif_l.gif";
        web_right.loadUrl(file_r);
        web_left.loadUrl(file_l);


        ImageButton imgbtn_c;
        imgbtn_c = (ImageButton) findViewById(R.id.imgbtn_collections);
        imgbtn_c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Collections.class);
                startActivity(intent);
            }
        });

        ImageButton imgbtn_h;
        imgbtn_h = (ImageButton) findViewById(R.id.imgbtn_home);
        imgbtn_h.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
        });


        pop = new Dialog(this);
        time_select = (Spinner) findViewById(R.id.dropdown);
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
    private boolean hasLeft(){
        ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(myProcess);
        Boolean isInBackground = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
        return isInBackground;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountDownText();
                if (hasLeft()){
//                    Intent intent = new Intent(MainActivity.this, Collections.class);
//                    startActivity(intent);
//                    ShowPopupLeave(null);
//                    System.out.println("displaying");
                    openDialog();
                }
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //popup? about the cat image
                mTextViewCountDown.setText("Finished!");
                mButtonClaim.setVisibility(View.VISIBLE);

            }

        }.start();
        mTimerRunning = true;
    }

    private void resetTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStart.setVisibility(View.VISIBLE);
        time_select.setVisibility(View.VISIBLE);
        mButtonClaim.setVisibility(View.INVISIBLE);
        mTextViewCountDown.setText("30:00");
    }

    private void updateCountDownText() {
        int min = (int) (mTimeLeftInMillis / 1000) / 60;
        int sec = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d", min, sec);
        mTextViewCountDown.setText(timeLeftFormat);
    }

    public void openDialog(){
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.sad_cat);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("You've abandoned your search...");
        builder.setMessage("");
        builder.setPositiveButton("Go to Collections", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, Collections.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Go to Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //go home
            }
        });

        builder.setView(img);
        builder.create().show();

    }


    public void ShowPopupLeave(View v){
        TextView txtclose;
        Button btn_home;
        Button btn_collect;
        pop.setContentView(R.layout.custom_popup);
        ImageView cat = (ImageView) pop.findViewById(R.id.cat_img);
        TextView cat_label = (TextView) pop.findViewById(R.id.cat_label);
        cat.setImageResource(R.drawable.sad_cat);
        cat_label.setText("You've abandoned your search...");
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
        txtclose.setVisibility(View.INVISIBLE);
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
            case "tom":
                cat.setImageResource(R.drawable.tom);
                cat_label.setText("Tom cat");
                found[i] = true;
                break;
            case "felix":
                cat.setImageResource(R.drawable.felix);
                cat_label.setText("Felix");
                found[i] = true;
                break;
            case "hello_kitty":
                cat.setImageResource(R.drawable.hello_kitty);
                cat_label.setText("Hello Kitty");
                found[i] = true;
                break;
            case "pusheen":
                cat.setImageResource(R.drawable.pusheen);
                cat_label.setText("Pusheen");
                found[i] = true;
                break;
            case "garfield":
                cat.setImageResource(R.drawable.garfield);
                cat_label.setText("Garfield");
                found[i] = true;
                break;
            case "cat_hat":
                cat.setImageResource(R.drawable.cat_hat);
                cat_label.setText("Cat in the Hat");
                found[i] = true;
                break;
            case "puss_boots":
                cat.setImageResource(R.drawable.puss_boots);
                cat_label.setText("Puss in Boots");
                found[i] = true;
                break;
            case "chesire":
                cat.setImageResource(R.drawable.chesire);
                cat_label.setText("Chesire Cat");
                found[i] = true;
                break;
            case "catdog":
                cat.setImageResource(R.drawable.cat_dog);
                cat_label.setText("Catdog");
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
                resetTimer();
                pop.dismiss();
            }
        });
        pop.show();
    }



}


