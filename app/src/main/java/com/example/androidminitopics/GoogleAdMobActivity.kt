package com.example.androidminitopics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class GoogleAdMobActivity : AppCompatActivity() {
    private var interstitialAd:InterstitialAd?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_ad_mob)
        val showInterAds = findViewById<Button>(R.id.showInterAds)

        //InterstitialAd setup code
        val adRequestInter = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequestInter,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(p0: InterstitialAd) {
                  this@GoogleAdMobActivity.interstitialAd=p0;
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Log.d("error loading ad",p0.message.toString())
                    this@GoogleAdMobActivity.interstitialAd=null
                }
            }
        )

        showInterAds.setOnClickListener {
            interstitialAd?.show(this)
        }

        //Banner Ad setup code
        val adBannerView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adBannerView.loadAd(adRequest)
    }
}