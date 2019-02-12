package id.haqiqi_studio.gamenumeric;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gentong on 15/03/2018.
 */

public class GameReferences {
    public final String SCORING = "myScore";
    int score, highScore;

    public void setBestScore(Context context, String key, int score) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putInt(key, score).apply();
    }

    public int getBestScore(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }

    public void setScorePenjumlahan(Context context, String key, int score) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putInt(key, score).apply();
    }

    public void setScorePengurangan(Context context, String key, int score) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putInt(key, score).apply();
    }

    public void setScorePerkalian(Context context, String key, int score) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putInt(key, score).apply();
    }

    public void setScorePembagian(Context context, String key, int score) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        editor.putInt(key, score).apply();
    }

    public int getScorePenjumlahan(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }

    public int getScorePengurangan(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }

    public int getScorePerkalian(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }

    public int getScorePembagian(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }

    public int getScore(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SCORING, Context.MODE_PRIVATE);

        return pref.getInt(key, 0);
    }
}
