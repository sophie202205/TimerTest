package com.example.timertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private Task1 task1;
    private TextView msgs;
    private int counter;
    private UIhandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new UIhandler();
        msgs = findViewById(R.id.msg);
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
        timer.schedule(task1, 1*1000, 1*1000);
    }

    public void test3(View view) {
        if(task1 != null){
            task1.cancel();
            task1 = null;
        }
    }

    public void test4(View view) {
        Intent intent = new Intent(this, Page2Activity.class);
        startActivity(intent);
    }
    //週期任務(非在主序(main thread)下跑)
    public class Task1 extends TimerTask{
        @Override
        public void run() {
            //Log.v("brad", "OK");
            counter++;
           // msgs.setText("" + counter); //直接設counter會是設定resource的ID

            //透過handler去設定ui會比較好
            //Message mesg = new Message();
            //Bundle data = new Bundle();
            //data.putInt("counter", counter);
            //mesg.setData(data);
            //handler.sendMessage(mesg);

            //使用what設定，更快速簡單
            handler.sendEmptyMessage(0);
        }
    }
    //os的那個
    private class UIhandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
           // super.handleMessage(msg);

            // 透過handler去設定ui會比較好
           //int dateInt = msg.getData().getInt("counter");
           //msgs.setText("" + dateInt);

            //使用what設定，更快速簡單
            if(msg.what == 0){
                msgs.setText("" + counter);
            }
        }
    }
}