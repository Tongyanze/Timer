package xin.dspwljsyxgs.www.timer;

/**
 * Created by TYZ on 2018/3/12.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.*;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import xin.dspwljsyxgs.www.timer.ScreenObserver.ScreenStateListener;

public class ScreenObserverActivity extends Activity {
    private String TAG = "ScreenObserverActivity";
    private ScreenObserver mScreenObserver;
    private Vibrator vibrator=null;
    private int label=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(ScreenObserverActivity.this,"Welcome!",Toast.LENGTH_SHORT).show();
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);



        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label=1;
                Toast.makeText(ScreenObserverActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label=0;
                Toast.makeText(ScreenObserverActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
            }
        });
        mScreenObserver = new ScreenObserver(this);
        mScreenObserver.requestScreenStateUpdate(new ScreenStateListener() {
            @Override
            public void onScreenOn() {
                doSomethingOnScreenOn();
            }

            @Override
            public void onScreenOff() {
                doSomethingOnScreenOff();
            }
        });
    }

    private void doSomethingOnScreenOn() {
        Log.i(TAG, "Screen is on");
        vibrator.cancel();
        Toast.makeText(ScreenObserverActivity.this,"Screen is on",Toast.LENGTH_SHORT).show();
    }

    private void doSomethingOnScreenOff() {
            if (label == 1) {
                try {

                        Thread.sleep(5000);
                } catch (Exception e) {

                }
                Log.i(TAG, "Screen is off");
                vibrator.vibrate(new long[]{100, 100}, 0);
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止监听screen状态
        mScreenObserver.stopScreenStateUpdate();
    }
}
