package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class ActivityRas extends AppCompatActivity {
    String operateur = "";
    String Bac, Lot, Lignee, Age, Responsable, Ras;
    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap_souffrance_ras);
        Intent intent = getIntent();
        Bac = intent.getStringExtra("Bac");
        operateur = intent.getStringExtra("operateur");
        Age = intent.getStringExtra("Age");
        Lignee = intent.getStringExtra("Lignee");
        Responsable = intent.getStringExtra("Responsable");
        Lot = intent.getStringExtra("Lot");
        
        Ras = intent.getStringExtra("Ras");
    
    
        textViewitemName = findViewById(R.id.datee);
        textViewBac = findViewById(R.id.tv_Bac);
        textViewprice = findViewById(R.id.ligneemale);
        textViewAge = findViewById(R.id.ligneefemelle);
        textViewResponsable = findViewById(R.id.tv_responsable);
        
        
        textViewitemName.setText(Lot);
        textViewBac.setText(Bac);
        textViewprice.setText(Lignee);
        textViewAge.setText(Age);
        textViewResponsable.setText(Responsable);
        
    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    public void lancerrecherche(View view) {
        Intent intent = new Intent(this, ActivityRechercheRegistreSouffrance.class);
        intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    
}
