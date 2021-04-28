package com.example.endpointapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityEcrirRecapBacs extends AppCompatActivity {
    
    
    TextView textViewitemLignee, textViewBac, textViewLot, textViewAge, textViewResponsable;
    
    
    //String Bac,Lot,Lignee,Age,Responsable,PointLimite,Elimine,Bac2,NouveauBac,Lignee2, Lot2;
    String Key="";
    String Key2="";
    String Bac="";
    String Lot="";
    String Lignee="";
    String Age="";
    String Responsable="";
    String PointLimite="";
    String Action="";
    String Bac2="";
    String NouveauBac="";
    String Lignee2="";
    String Lot2 = "";
    
    String operateur = "";


    //Spinner OperateurSpinner;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_ecrir_recap_bacs);
    
    
        // operateur
        // operateur = intent.getStringExtra("operateur");
        //OperateurSpinner = findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter17);
        // operateur = intent.getStringExtra("operateur");
        
        Intent intent = getIntent();


        Bac=intent.getStringExtra("Bac");
        Lot=intent.getStringExtra("Lot");
        Lignee=intent.getStringExtra("Lignee");
        Age=intent.getStringExtra("Age");
        Key=intent.getStringExtra("Key");
    
        textViewitemLignee=findViewById(R.id.tv_lignee);
        textViewBac=findViewById(R.id.tv_Bac);
        textViewAge=findViewById(R.id.tv_age);
        textViewLot=findViewById(R.id.tv_lot);
        textViewResponsable=findViewById(R.id.tv_responsable);
    
    
        textViewLot.setText(Lot);
        textViewBac.setText(Bac);
        textViewitemLignee.setText(Lignee);
        textViewAge.setText(Age);
        textViewResponsable.setText(Responsable);
    
        // Get the transferred data from source activity.
        operateur = intent.getStringExtra("operateur");
    
    
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
    
    public void lancerreunir(View view) {
        Intent intent = new Intent(this, ActivityReunirBacs.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Key", Key);
        startActivity(intent);
    }
    
    public void lancerdeplacer(View view) {
        Intent intent=new Intent(this, ActivityDeplacerBacs.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        //intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
    
        startActivity(intent);
    }
    
    public void lancerdupliquer(View view) {
        Intent intent=new Intent(this, ActivityDupliquerBacs.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
        startActivity(intent);
    }
    
    public void lancersignaler(View view) {
        Intent intent=new Intent(this, ActivityErreur.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
        startActivity(intent);
    }
    
    public void lancereliminer(View view) {
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        intent.putExtra("Key", Key);
    
        Action="Eliminé";
    
        WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, operateur, NouveauBac, Action, Bac, Lignee, Lot, Bac2, Lignee2, Lot2, Key, Key2);
        startActivity(intent);
    }
}