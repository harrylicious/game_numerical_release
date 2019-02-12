package id.haqiqi_studio.gamenumeric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Statistik extends AppCompatActivity {
    GameReferences ref = new GameReferences();

    int scoreAddition, scoreAllevation, scoreMultiplication, scoreDivision;
    TextView addition, allevation, multiplication, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        scoreAddition = ref.getScorePenjumlahan(getApplicationContext(), "addition");
        scoreAllevation = ref.getScorePenjumlahan(getApplicationContext(), "allevation");
        scoreMultiplication = ref.getScorePenjumlahan(getApplicationContext(), "multiplication");
        scoreDivision = ref.getScorePenjumlahan(getApplicationContext(), "division");

        addition = findViewById(R.id.addition);
        allevation = findViewById(R.id.allevation);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);

        addition.setText(String.valueOf(scoreAddition));
        allevation.setText(String.valueOf(scoreAllevation));
        multiplication.setText(String.valueOf(scoreMultiplication));
        division.setText(String.valueOf(scoreDivision));
    }
}
