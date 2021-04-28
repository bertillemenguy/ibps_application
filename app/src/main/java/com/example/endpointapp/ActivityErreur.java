package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class ActivityErreur extends AppCompatActivity {
    String main_user = " ";
    String Date = " ";
    String Bac=" ";
    String Lot=" ";
    String Lignee=" ";
    String Age=" ";
    String Action="";
    String Bac2="";
    String Lignee2="";
    String Lot2="";
    String NouveauBac="";
    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
    String Key2="";
    String Key="";
    Spinner SpinnerAlphabet;
    Spinner SpinnerNombre;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap_bacs_erreur);
        
        
        Intent intent = getIntent();
        Bac = intent.getStringExtra("Bac");
        main_user = intent.getStringExtra("main_user");
        Age = intent.getStringExtra("Age");
        Lignee = intent.getStringExtra("Lignee");
        Lot = intent.getStringExtra("Lot");
        Date=intent.getStringExtra("Date");
        Key=intent.getStringExtra("Key");
        
        SpinnerAlphabet=findViewById(R.id.spinnerb);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet.setAdapter(adapter);
    
    
        SpinnerNombre=findViewById(R.id.spinnerh);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre.setAdapter(adapter2);
        
        
    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void enregistrerdeplacement(View view) {
        final String Nombre = SpinnerNombre.getSelectedItem().toString();
        final String Lettre = SpinnerAlphabet.getSelectedItem().toString();
        final String NouveauBac = Lettre + Nombre;
        Action="Erreur Place";
    
    
        WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, main_user, NouveauBac, Action, Bac, Lignee, Lot, Bac2, Lignee2, Lot2, Key, Key2);
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
}
    

