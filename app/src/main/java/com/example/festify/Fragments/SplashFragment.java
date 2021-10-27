/*
package com.example.festify.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.example.festify.R;
import com.example.festify.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        FragmentSplashBinding splashBinding;

        splashBinding = FragmentSplashBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getInsetsController().hide(WindowInsets.Type.statusBars());
        } else getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


        Animation splashAnimation = AnimationUtils.loadAnimation(this,
                R.anim.anim_splash);
        splashBinding.ivAppName.startAnimation(splashAnimation);

        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                final Handler handler = new
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), FirstFragment.class));
                        setContentView(R.layout.fragment_first);
                        finish();
                    }
                }, 10000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
*/
