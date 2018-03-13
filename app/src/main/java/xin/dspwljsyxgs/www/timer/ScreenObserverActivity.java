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
    private Vibrator vibrator=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(ScreenObserverActivity.this,"Welcome!",Toast.LENGTH_SHORT).show();
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
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

            try{
                Thread.sleep(15000);
            }
            catch (Exception e){

            }
            Log.i(TAG, "Screen is off");
            vibrator.vibrate(new long[]{100, 100}, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止监听screen状态
        mScreenObserver.stopScreenStateUpdate();
    }
}
