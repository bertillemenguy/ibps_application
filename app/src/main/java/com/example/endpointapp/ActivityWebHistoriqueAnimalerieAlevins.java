package com.example.endpointapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;


public class ActivityWebHistoriqueAnimalerieAlevins extends AppCompatActivity {
    String html="<iframe width=\"1000\" height=\"4000\" style=\"border: 1px solid #cccccc;\"  <iframe src=\"https://docs.google.com/spreadsheets/d/e/2PACX-1vTt-Llt4wpV-xc0Ece45IUBt1vIollaQt3nmn7lr2VconYfBQibKqTeYEWN7siSFWhUaWApX59pvPr6/pubhtml?gid=1354680660&amp;single=true&amp;widget=true&amp;headers=false\" style=\"border: 0\" width=\"400\" height=\"300\" frameborder=\"0\" scrolling=\"no\"></iframe>";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_historique);
        WebView webview;
        webview=findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        
        webview.loadData(html, "text/html", null);
        
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
}


