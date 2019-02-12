package id.haqiqi_studio.gamenumeric.Category;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Anim.Animations;
import id.haqiqi_studio.gamenumeric.ChooseQuiz;
import id.haqiqi_studio.gamenumeric.GameReferences;
import id.haqiqi_studio.gamenumeric.Math.SimpleQuiz;
import id.haqiqi_studio.gamenumeric.R;
import id.haqiqi_studio.gamenumeric.TimerConfig;
import id.haqiqi_studio.gamenumeric.YourScore;
import pl.droidsonroids.gif.GifImageButton;
import tyrantgit.explosionfield.ExplosionField;

public class Multiplication extends AppCompatActivity {

    //region Declaration Variables
    ExplosionField explode;
    SimpleQuiz quiz = new SimpleQuiz();
    GameReferences pref = new GameReferences();
    Button newButton, addition, allevation, multiplication, division, congrat, step1, step2, step3, step4, step5;
    MediaPlayer mediaPlayer = new MediaPlayer(), playerBackground = new MediaPlayer();
    TimerConfig time = new TimerConfig();
    Animations anim = new Animations();
    RelativeLayout panel;

    ViewGroup container;
    TextView txtScore, txtBestScore, txtTimer, salah, benar, jeda;
    GifImageButton btn;

    ProgressBar progress, prog1, prog2, prog3;
    int correct = 0, inCorrect = 0;
    String first, mid, last;
    int a, b, c, score, bestScore, childCount, counter = 0, delay = 5;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        init();
        playerBackground = MediaPlayer.create(getApplicationContext(), R.raw.game_play);
        jeda.setText(String.valueOf(delay) + " detik");
        playerBackground.setLooping(true);
        playerBackground.start();
        //retreiveFromSharedPreference();

        congrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChooseQuiz.class));
            }
        });

        new CountDownTimer(500000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (counter <= 30) {
                    addNewQuestion(400);
                } else if (counter >= 30 && counter <= 35) {
                    anim.shake(getApplicationContext(), step2);
                    step2.setBackground(getResources().getDrawable(R.drawable.circle_btn_blue));
                    panel.setVisibility(View.VISIBLE);
                    jeda.setText(String.valueOf(delay--) + " detik");
                    container.removeAllViews();
                } else if (counter <= 65) {
                    addNewQuestion(700);
                    panel.setVisibility(View.INVISIBLE);
                } else if (counter >= 65 && counter <= 70) {
                    anim.shake(getApplicationContext(), step3);
                    step3.setBackground(getResources().getDrawable(R.drawable.circle_btn_blue));
                    delay = 5;
                    panel.setVisibility(View.VISIBLE);
                    jeda.setText(String.valueOf(delay--) + " detik");
                    container.removeAllViews();
                } else if (counter <= 100) {
                    addNewQuestion(1200);
                    panel.setVisibility(View.INVISIBLE);
                } else if (counter >= 100 && counter <= 105) {
                    anim.shake(getApplicationContext(), step4);
                    step4.setBackground(getResources().getDrawable(R.drawable.circle_btn_blue));
                    delay = 5;
                    panel.setVisibility(View.VISIBLE);
                    jeda.setText(String.valueOf(delay--) + " detik");
                    container.removeAllViews();
                } else if (counter <= 135) {
                    addNewQuestion(1800);
                    panel.setVisibility(View.INVISIBLE);
                } else if (counter >= 135 && counter <= 140) {
                    anim.shake(getApplicationContext(), step5);
                    step5.setBackground(getResources().getDrawable(R.drawable.circle_btn_blue));
                    delay = 5;
                    panel.setVisibility(View.VISIBLE);
                    jeda.setText(String.valueOf(delay--) + " detik");
                    container.removeAllViews();
                } else if (counter <= 170 ) {
                    addNewQuestion(3000);
                    panel.setVisibility(View.INVISIBLE);
                }
                else if (counter >= 170) {
                    congrat.setVisibility(View.VISIBLE);
                }

                txtTimer.setText(String.valueOf(counter));
                counter++;
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), YourScore.class));
            }
        }.start();
    }

    //region Initialize views
    void init() {
        container = findViewById(R.id.container);
        panel = findViewById(R.id.panel);
        salah = findViewById(R.id.salah);
        benar = findViewById(R.id.benar);

        txtTimer = findViewById(R.id.label_timer);
        jeda = findViewById(R.id.jeda);
        progress = findViewById(R.id.progressbar);

        addition = findViewById(R.id.btnAddition);
        allevation = findViewById(R.id.btnAlleviation);
        multiplication = findViewById(R.id.btnMultiplication);
        division = findViewById(R.id.btnDivision);
        congrat = findViewById(R.id.congrat);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);

        prog1 = findViewById(R.id.progressBar1);
        prog2 = findViewById(R.id.progressBar2);
        prog3 = findViewById(R.id.progressBar3);
    }


    //endregion

    //region Add Question
    void addNewQuestion(int duration) {

        String[] arr = quiz.shuffleQuestions(quiz.mergeAndShuffleQuestion("x"));
        childCount = container.getChildCount();


        for (int i = 0; i < 1; i++) {
            newButton = new Button(getApplicationContext());
            //newButton.setId(i);

            newButton.setBackground(getResources().getDrawable(R.drawable.layer_btn_blue));

            newButton.setText(arr[i]);
            newButton.setContentDescription(arr[i]);
            newButton.setBottom(5);

            setFallDownAnimation(duration, newButton);
            container.addView(newButton);

            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String txt = (String) v.getContentDescription();
                    if (txt.trim().length() == 9) {
                        first = (String) txt.subSequence(0, 1);
                        mid = (String) txt.subSequence(4, 5);
                        last = (String) txt.subSequence(8, 9);
                    } else {
                        first = (String) txt.subSequence(0, 1);
                        mid = (String) txt.subSequence(4, 5);
                        last = (String) txt.subSequence(8, 10);
                    }

                    a = Integer.parseInt(first);
                    b = Integer.parseInt(mid);
                    c = Integer.parseInt(last);

                    if (a * b == c) {
                        setExplode(v);
                        container.removeView(v);
                        correct++;
                        benar.setText(String.format(getResources().getString(R.string.scoreValue), correct));
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ting);
                        mediaPlayer.start();
                    } else {
                        v.setBackground(getResources().getDrawable(R.drawable.layer_btn_black));
                        Boolean checkState = v.getBackground().equals(getResources().getDrawable(R.drawable.layer_btn_black));
                        if (!checkState) {
                            inCorrect++;
                            salah.setText(String.format(getResources().getString(R.string.scoreValue), inCorrect));
                            mediaPlayer.stop();
                            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tong);
                            mediaPlayer.start();
                        }
                    }
                    //saveIntoSharedPreference();
                }
            });
        }

    }
    //endregion

    //region Animation
    void setExplode(View v) {
        explode = ExplosionField.attach2Window(this);
        explode.explode(v);
    }

    void setFallDownAnimation(int duration, View v) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_from_top);
        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    void setSpinningAnimation(View v) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hyperspace_jump);
        v.startAnimation(animation);
    }
    //endregion
}
