package com.kiwabolab.rappipeliculas.vista.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.kiwabolab.rappipeliculas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Video extends Activity {
    //----------------------------------------------------------------------------------------------
    //Variables
    @BindView(R.id.webvideo)
    WebView myWebView;
    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        String name= getIntent().getStringExtra("video");
        setVideo(name);
    }
    //----------------------------------------------------------------------------------------------
    //Cargar la pagina web con la url del video
    private void setVideo(String video){
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.youtube.com/embed/"+video);
        myWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                //animacion.setVisibility(View.GONE);
            }
        });
    }
}