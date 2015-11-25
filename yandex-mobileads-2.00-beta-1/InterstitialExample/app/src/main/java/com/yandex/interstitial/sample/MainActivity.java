/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2015 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */

package com.yandex.interstitial.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private static final String QUERY = "Minsk";

    private AdRequest mAdRequest;
    private InterstitialAd mInterstitialAd;

    private Button mLoadInterstitialAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadInterstitialAdButton = (Button) findViewById(R.id.load_interstitial_button);
        mLoadInterstitialAdButton.setOnClickListener(mInterstitialClickListener);

        initInterstitialAd();
    }

    private void initInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);

        /*
        * Replace demo R-M-DEMO-320x480 with actual Block ID
        * Following demo Block IDs may be used for testing:
        * R-M-DEMO-320x480
        * R-M-DEMO-480x320
        * R-M-DEMO-400x240-context
        * R-M-DEMO-240x400-context
        */
        mInterstitialAd.setBlockId("R-M-DEMO-320x480");

        mAdRequest = AdRequest.builder().withContextQuery(QUERY).build();
        mInterstitialAd.setAdEventListener(mInterstitialAdEventListener);
    }

    @Override
    protected void onDestroy() {
        mInterstitialAd.destroy();
        super.onDestroy();
    }

    private View.OnClickListener mInterstitialClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mLoadInterstitialAdButton.setEnabled(false);
            mLoadInterstitialAdButton.setText(getResources().getText(R.string.start_load_interstitial_button));

            mInterstitialAd.loadAd(mAdRequest);
        }
    };

    private AdEventListener mInterstitialAdEventListener = new AdEventListener.SimpleAdEventListener() {
        @Override
        public void onAdLoaded() {
            mInterstitialAd.show();

            mLoadInterstitialAdButton.setEnabled(true);
            mLoadInterstitialAdButton.setText(getResources().getText(R.string.load_interstitial_button));
        }

        @Override
        public void onAdFailedToLoad(AdRequestError error) {
            mLoadInterstitialAdButton.setEnabled(true);
            mLoadInterstitialAdButton.setText(getResources().getText(R.string.load_interstitial_button));
        }
    };
}
