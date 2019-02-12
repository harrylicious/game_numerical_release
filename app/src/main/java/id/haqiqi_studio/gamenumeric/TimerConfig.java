package id.haqiqi_studio.gamenumeric;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TimerConfig extends AppCompatActivity {
    private int i;

    public void setTimer(int delay, final TextView txt) {
        i = delay;
        final Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                i++;
                                txt.setText(String.valueOf(i));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
