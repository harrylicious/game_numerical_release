package id.haqiqi_studio.gamenumeric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    LinearLayout line1, line2;
    Animation uptodown, downtotop;
    TextView counter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtotop = AnimationUtils.loadAnimation(this, R.anim.downtotop);
        counter = (TextView) findViewById(R.id.counter);

        TimerConfig time = new TimerConfig();
        time.setTimer(1000, counter);


        line1.setAnimation(uptodown);
        line2.setAnimation(downtotop);


    }
}
