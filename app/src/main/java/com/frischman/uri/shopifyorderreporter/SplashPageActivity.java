package com.frischman.uri.shopifyorderreporter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SplashPageActivity extends AppCompatActivity {

    private RelativeLayout mCircleImageViewContainer;
    private RelativeLayout mSplashTextContainer;
    private RelativeLayout mIconContaier;
    private ImageView mShopifyLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        Animation bottomUp1 = AnimationUtils.loadAnimation(this, R.anim.bottom_up);


        mCircleImageViewContainer = (RelativeLayout) findViewById(R.id.headShotContainer);
        mSplashTextContainer = (RelativeLayout) findViewById(R.id.splashParagraphContainer);
        mIconContaier = (RelativeLayout) findViewById(R.id.iconContainer);
        mIconContaier.setVisibility(View.GONE);
        mShopifyLogo = (ImageView) findViewById(R.id.shopifyIcon);

        bottomUp.setDuration(2000);
        bottomUp1.setDuration(1000);

        mCircleImageViewContainer.setAnimation(bottomUp);
        mSplashTextContainer.setAnimation(bottomUp);

        bottomUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIconContaier.setVisibility(View.VISIBLE);
                mIconContaier.setAnimation(bottomUp1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mShopifyLogo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}
