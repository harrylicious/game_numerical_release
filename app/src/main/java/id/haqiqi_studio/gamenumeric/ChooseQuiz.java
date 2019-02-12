package id.haqiqi_studio.gamenumeric;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import Anim.Animations;
import id.haqiqi_studio.gamenumeric.Category.Addition;
import id.haqiqi_studio.gamenumeric.Category.Allevation;
import id.haqiqi_studio.gamenumeric.Category.Division;
import id.haqiqi_studio.gamenumeric.Category.Multiplication;

public class ChooseQuiz extends AppCompatActivity {

    Button addition, alleviation, multiplication, divisison;
    Animations anim;
    int counter = 0;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Main.class));
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        anim = new Animations();

        addition = findViewById(R.id.addition);
        alleviation = findViewById(R.id.alleviation);
        multiplication = findViewById(R.id.multiplication);
        divisison = findViewById(R.id.division);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Addition.class));
            }
        });

        alleviation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Allevation.class));
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Multiplication.class));
            }
        });

        divisison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Division.class));
            }
        });

        new CountDownTimer(50000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                counter++;
                if (counter == 3) {
                    anim.shake(getApplicationContext(), addition);
                }
                if (counter == 4 ) {
                    anim.shake(getApplicationContext(), alleviation);
                }
                if (counter == 5 ) {
                    anim.shake(getApplicationContext(), multiplication);
                }
                if (counter == 6 ) {
                    anim.shake(getApplicationContext(), divisison);
                }
                if (counter == 6) {
                    counter = 0;
                }

            }

            @Override
            public void onFinish() {
                //
            }
        }.start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
