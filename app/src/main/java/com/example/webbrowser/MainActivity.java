package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgbtnsend,imgbtnweb,imgbtnback,imgbtnforward,imgbtnreload,imgbtnexit;
    private WebView mywebview;
    private EditText eurl;
    private LinearLayout panel;
    private String url="https://www.google.pl/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializecomponents();
    }

    private void initializecomponents(){
        eurl=findViewById(R.id.eurl);
        eurl.setText(url);
        imgbtnsend=findViewById(R.id.imgbtnsend);
        imgbtnweb=findViewById(R.id.imgbtnweb);
        imgbtnback=findViewById(R.id.imgbtnback);
        imgbtnforward=findViewById(R.id.imgbtnforward);
        imgbtnreload=findViewById(R.id.imgbtnreload);
        imgbtnexit=findViewById(R.id.imgbtnexit);
        panel=findViewById(R.id.panel);
        panel.setVisibility(View.GONE);
        mywebview = findViewById(R.id.webView);
        mywebview.setWebViewClient(new WebViewClient());
        WebSettings ws=mywebview.getSettings();
        ws.setJavaScriptEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        mywebview.setVerticalScrollBarEnabled(false);
        mywebview.setHorizontalScrollBarEnabled(false);
        mywebview.loadUrl(url);
        imgbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadwebpage();
            }
        });
        imgbtnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel.setVisibility(View.VISIBLE);
            }
        });
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mywebview.canGoBack();
                onBackPressed();
            }
        });
        imgbtnforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mywebview.canGoForward();
                onForwardPressed();
            }
        });
        imgbtnreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadPressed();
            }
        });
        imgbtnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(mywebview.canGoBack()){
            mywebview.goBack();
        }else {
            finish();
            //super.onBackPressed();
        }
    }

    public void onForwardPressed() {
        if(mywebview.canGoForward()){
            mywebview.goForward();
        }else {
            //super.onForwardPressed();
        }
    }

    public void onReloadPressed() {
        mywebview.reload();
    }

    public void loadwebpage(){
        try{
            url=eurl.getText().toString();
            //if(url.substring(0,6)!="http://" && url.substring(0,7)!="https://") url="https://"+url;
            mywebview.loadUrl(url);
            eurl.setText(url);
        }catch(Exception e){
            Toast.makeText(MainActivity.this,"Wprowadź poprawny adres url!",Toast.LENGTH_LONG).show();
        }finally {
            eurl.setText(null);
            panel.setVisibility(View.GONE);
        }
    }
}
