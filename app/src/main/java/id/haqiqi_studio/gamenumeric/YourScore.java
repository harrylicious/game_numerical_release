package id.haqiqi_studio.gamenumeric;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import Anim.Animations;

public class YourScore extends AppCompatActivity {
    GameReferences ref = new GameReferences();
    int counter = 0;
    TextView score;
    Animations anim;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_score);

        anim = new Animations();

        score = findViewById(R.id.your_score);



        score.setText(String.valueOf(ref.getScorePenjumlahan(getApplicationContext(), "addition")));

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getIntent().toString(), Toast.LENGTH_LONG).show();
            }
        });


        new CountDownTimer(50000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                counter++;
                if (counter % 3 == 0) {
                    anim.shake(getApplicationContext(), score);
                }

            }

            @Override
            public void onFinish() {
                //
            }
        }.start();
    }


}
