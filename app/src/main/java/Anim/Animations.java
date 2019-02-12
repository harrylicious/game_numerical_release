package Anim;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import id.haqiqi_studio.gamenumeric.R;

public class Animations {
    public void shake(Context context, View v) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);
        v.startAnimation(animation);
    }


}
