package com.example.timertest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer != null){
            timer.cancel(); //取消
            timer.purge(); //清除
            timer = null;
        }
    }

    public void test1(View view) {
        Log.v("brad", "start");
        //千分之一秒
        timer.schedule(new Test1(), 3*1000); //安排之日程
    }

    public class Test1 extends TimerTask{
        @Override
        public void run() {
            Log.v("brad", "OK");
        }
    }
}