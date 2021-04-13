package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class ActivityReunificationBacs extends AppCompatActivity {
    String operateur=" ";
    String Date=" ";
    String Bac=" ";
    String Lot=" ";
    String Lignee=" ";
    
    String Action="";
    String Bac2="";
    String Lot2="";
    String Lignee2="";
    String Key2="";
    String Key="";
    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
    Spinner SpinnerAlphabet;
    Spinner SpinnerNombre;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deplacer_reunir);
        
        
        Intent intent=getIntent();
        Bac=intent.getStringExtra("Bac");
        operateur=intent.getStringExtra("operateur");
        
        Lignee=intent.getStringExtra("Lignee");
        Lot=intent.getStringExtra("Lot");
        // Date = intent.getStringExtra("Date");
        Bac2=intent.getStringExtra("Bac2");
        Lignee2=intent.getStringExtra("Lignee2");
        Lot2=intent.getStringExtra("Lot2");
        Key2=intent.getStringExtra("Key2");
        Key=intent.getStringExtra("Key");
        
        SpinnerAlphabet=findViewById(R.id.spinnerb);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet.setAdapter(adapter);
        
        
        SpinnerNombre=findViewById(R.id.spinnerh);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre.setAdapter(adapter2);
        
        
    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    public void enregistrerdeplacement(View view) {
        final String Nombre=SpinnerNombre.getSelectedItem().toString();
        final String Lettre=SpinnerAlphabet.getSelectedItem().toString();
        final String NouveauBac=Lettre + Nombre;
        Action="Réunis";
        WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, operateur, NouveauBac, Action, Bac, Lignee, Lot, Bac2, Lignee2, Lot2, Key, Key2);
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
}
