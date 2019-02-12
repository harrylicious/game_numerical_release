package id.haqiqi_studio.gamenumeric;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import id.haqiqi_studio.gamenumeric.Math.SimpleQuiz;
import pl.droidsonroids.gif.GifImageButton;
import tyrantgit.explosionfield.ExplosionField;


public class GamePlay extends FragmentActivity {

    //region Declaration Variables
    ExplosionField explode;
    SimpleQuiz quiz = new SimpleQuiz();
    GameReferences pref = new GameReferences();
    Button newButton, addition, allevation, multiplication, division, congrat;
    MediaPlayer mediaPlayer = new MediaPlayer(), playerBackground = new MediaPlayer();
    TimerConfig time = new TimerConfig();

    ViewGroup container;
    TextView txtScore, txtBestScore, txtTimer, salah, benar;
    GifImageButton btn;

    ProgressBar progress, prog1, prog2, prog3;
    int correct = 0, inCorrect = 0;
    String first, mid, last;
    int a, b, c, score, bestScore, childCount, counter = 0, timer = 0;

    //endregion

    //region onCreate()
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        playerBackground = MediaPlayer.create(getApplicationContext(), R.raw.game_play);
        playerBackground.start();

        init();
        retreiveFromSharedPreference();

        new CountDownTimer(50000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (counter >= 7 && counter <=10) {
                    allevation.setVisibility(View.INVISIBLE);
                }
                else if (counter >= 17 && counter <= 20) {
                    multiplication.setVisibility(View.INVISIBLE);
                }
                else if (counter >= 27 && counter <= 30){
                    division.setVisibility(View.INVISIBLE);
                }

                if (counter ==10) {
                    allevation.setVisibility(View.VISIBLE);
                    allevation.setBackground(getResources().getDrawable(R.drawable.layer_btn_green));

                    setExplode(addition);
                    container.removeAllViews();
                }
                else if (counter == 20) {
                    multiplication.setVisibility(View.VISIBLE);
                    multiplication.setBackground(getResources().getDrawable(R.drawable.layer_btn_blue));
                    prog1.setVisibility(View.INVISIBLE);
                    setExplode(allevation);
                    container.removeAllViews();
                }
                else if (counter ==30){
                    division.setVisibility(View.VISIBLE);
                    division.setBackground(getResources().getDrawable(R.drawable.layer_btn_red));
                    prog2.setVisibility(View.INVISIBLE);
                    setExplode(multiplication);
                    container.removeAllViews();
                }
                else if (counter ==40){
                    division.setVisibility(View.VISIBLE);
                    division.setBackground(getResources().getDrawable(R.drawable.layer_btn_red));
                    prog3.setVisibility(View.INVISIBLE);
                    setExplode(division);
                    congrat.setVisibility(View.VISIBLE);
                }

                if (counter >= 0 && counter <= 10) {
                    addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_orange), "+");
                }
                else if (counter >= 11 && counter <= 20) {
                    addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_green), "-");
                }
                else if (counter >= 21 && counter <= 30){
                    addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_blue), "*");
                }
                else if (counter >= 31 && counter <= 40){
                    addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_red), "/");
                }



                txtTimer.setText(String.valueOf(counter));
                counter++;
            }

            @Override
            public void onFinish() {
               startActivity(new Intent(getApplicationContext(), YourScore.class));

            }
        }.start();




        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_orange), "+");
            }
        });

        allevation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_green), "-");
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_red), "x");
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_blue), "/");
            }
        });


    }
    //endregion

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    //region Acitvity Circle
    //region onResume()
    @Override
    protected void onResume() {
        super.onResume();
        saveIntoSharedPreference();
    }
    //endregion

    //region onStart()
    @Override
    protected void onStart() {
        childCount = container.getChildCount();
        if (childCount <= 0) {
            addNewQuestion(getResources().getDrawable(R.drawable.layer_btn_orange), "+");
        }

        progress.setVisibility(View.INVISIBLE);
        super.onStart();
    }
    //endregion

    //region onDestroy()
    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
    //endregion
    //endregion

    //region SharedPreference for Scoring
    void saveIntoSharedPreference() {
       // score = Integer.parseInt(txtScore.getText().toString());
        bestScore = pref.getBestScore(getApplicationContext(), "best");
        if (score > bestScore) {
            bestScore = score;
            pref.setBestScore(getApplicationContext(), "best", bestScore);

            setSpinningAnimation(txtBestScore);
        }
        //txtBestScore.setText(String.valueOf(bestScore));

    }

    void retreiveFromSharedPreference() {
        bestScore = pref.getBestScore(getApplicationContext(), "best");
        score = pref.getBestScore(getApplicationContext(), "score");
        //txtBestScore.setText(String.valueOf(bestScore));
       // txtScore.setText(String.valueOf(score));
    }
    //endregion

    //region Initialize views
    void init() {
        container = findViewById(R.id.container);
        //btn = findViewById(R.id.btnRefresh);
        benar = findViewById(R.id.benar);
        salah = findViewById(R.id.salah);

        txtTimer = findViewById(R.id.label_timer);
        //txtScoreSalah = findViewById(R.id.txt);
        progress = findViewById(R.id.progressbar);

        addition = findViewById(R.id.btnAddition);
        allevation = findViewById(R.id.btnAlleviation);
        multiplication = findViewById(R.id.btnMultiplication);
        division = findViewById(R.id.btnDivision);
        congrat = findViewById(R.id.congrat);

        prog1 = findViewById(R.id.progressBar1);
        prog2 = findViewById(R.id.progressBar2);
        prog3 = findViewById(R.id.progressBar3);
    }


    //endregion

    //region Add Question
    void addNewQuestion(Drawable drawable, final String operator) {

        String[] arr = quiz.shuffleQuestions(quiz.mergeAndShuffleQuestion(operator));
        childCount = container.getChildCount();


        for (int i = 0; i < 1; i++) {
            newButton = new Button(getApplicationContext());
            //newButton.setId(i);

            newButton.setBackground(drawable);

            newButton.setText(arr[i]);
            newButton.setContentDescription(arr[i]);
            newButton.setBottom(5);

            setFallDownAnimation(newButton);
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

                    //Toast.makeText(getApplicationContext(), v.getContentDescription(), Toast.LENGTH_SHORT).show();

                    switch (operator) {
                        case "+" :
                            if (a + b == c) {
                                setExplode(v);
                                container.removeView(v);
                                correct++;
                                benar.setText(String.format(getResources().getString(R.string.scoreValue), correct));
                                mediaPlayer.stop();
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ting);
                                mediaPlayer.start();
                                break;
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
                                break;
                                //txtScoreSalah.setText(String.format(getResources().getString(R.string.scoreValue), inCorrect));
                            }

                        case "-" :
                            if (a - b == c) {
                                setExplode(v);
                                container.removeView(v);
                                correct++;
                                benar.setText(String.format(getResources().getString(R.string.scoreValue), correct));
                                mediaPlayer.stop();
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ting);
                                mediaPlayer.start();
                                break;
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
                                break;
                                 //txtScoreSalah.setText(String.format(getResources().getString(R.string.scoreValue), inCorrect));
                            }
                        case "x" :
                            if (a * b == c) {
                                setExplode(v);
                                container.removeView(v);
                                correct++;
                                benar.setText(String.format(getResources().getString(R.string.scoreValue), correct));
                                mediaPlayer.stop();
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ting);
                                mediaPlayer.start();
                                break;
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
                                break;
                             }
                        case "/" :
                            if (a / b == c) {
                                setExplode(v);
                                container.removeView(v);
                                correct++;
                                benar.setText(String.format(getResources().getString(R.string.scoreValue), correct));
                                mediaPlayer.stop();
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ting);
                                mediaPlayer.start();
                                break;
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
                                break;

                            }
                    }
                    saveIntoSharedPreference();
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

    void setFallDownAnimation(View v) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_from_top);
        v.startAnimation(animation);
    }

    void setSpinningAnimation(View v) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hyperspace_jump);
        v.startAnimation(animation);
    }
    //endregion


}


