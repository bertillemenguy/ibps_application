package com.example.endpointapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class ActivitySplash extends AppCompatActivity {
    
    private static final int SPLASH_TIME = 1000; // Le temps de l'animation du début d'application en millisecondes ici par défaut 2000 millisecondes donc 2 secondes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        
        // Code à lancer une fois l'activité finie
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(ActivitySplash.this, ActivityMenu.class);
                startActivity(mySuperIntent);
                // This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();
            }
        }, SPLASH_TIME);
    }
}













