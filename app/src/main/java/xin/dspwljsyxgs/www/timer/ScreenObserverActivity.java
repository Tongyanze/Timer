package xin.dspwljsyxgs.www.timer;

/**
 * Created by TYZ on 2018/3/12.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import xin.dspwljsyxgs.www.timer.ScreenObserver.ScreenStateListener;

public class ScreenObserverActivity extends Activity {
    private String TAG = "ScreenObserverActivity";
    private ScreenObserver mScreenObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        vibrator.cancel();
        Toast.makeText(ScreenObserverActivity.this,"Screen is on",Toast.LENGTH_SHORT).show();
    }

    private void doSomethingOnScreenOff() {
        Log.i(TAG, "Screen is off");
        Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{100,100},0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止监听screen状态
        mScreenObserver.stopScreenStateUpdate();
    }
}
