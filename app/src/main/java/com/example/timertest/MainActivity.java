package com.example.timertest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private Task1 task1;
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
        //千分之一秒(要做的事情,延遲幾秒)
        timer.schedule(new Task1(), 3*1000); //預約
    }

    public void test2(View view) {
        //(要做的事情,延遲幾秒,間隔多久再做一次)
        task1 = new Task1();
        timer.schedule(task1, 1*1000, 3*1000);
    }

    public void test3(View view) {
        if(task1 != null){
            task1.cancel();
            task1 = null;
        }
    }

    public class Task1 extends TimerTask{
        @Override
        public void run() {
            Log.v("brad", "OK");
        }
    }
}