package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NettoyageBac extends AppCompatActivity {
    String main_user = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nettoyage_bac);

        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");

    }
}
