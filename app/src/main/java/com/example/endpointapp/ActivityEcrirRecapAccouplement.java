package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityEcrirRecapAccouplement extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    
    //Mixer les deux activity
    
    
    // String Bac, Lot, Lignee,Age,Responsable,NbMale,NbFemelle, Bac2, Lot2, Lignee2,Age2,Responsable2,operateur;
    
    Spinner PoissonMortSpinner;
    Spinner PoissonMortSpinner2;
    Spinner PoissonMortSpinner3;
    Spinner PoissonMortSpinner7;
    Spinner PoissonMortSpinner10;
    Spinner OperateurSpinner;
    
    String NbBac="";
    String Couleur1="";
    String Couleur2="";
    String Lot="";
    String Bac="";
    String Key="";
    String Key2="";
    String Lignee="";
    String Age="";
    String Responsable="";
    String NbMale="";
    String NbFemelle="";
    String Bac2="";
    String Lot2="";
    
    String Lignee2="";
    String Age2="";
    
    String Responsable2="";
    
    TextView textViewLot, textViewBac, textViewBac2, textViewLot2, textViewLignee, textViewLignee2, textViewAge, textViewAge2;
    
    //  String Nbac1 = "";
    // String Nbac2 = "";
    // String operateur = "";
    
    //Activity Recap
    
    // ajouter
    // String operateur = "";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_accouplement);
        
        //ajouter
        // operateur = intent.getStringExtra("operateur");
        OperateurSpinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OperateurSpinner.setAdapter(adapter);
        //operateur = intent.getStringExtra("operateur");
        
        //à ajouter
        PoissonMortSpinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner.setAdapter(adapter11);
        // PoissonMortSpinner.setOnItemSelectedListener(this);
        
        
        PoissonMortSpinner2 = findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.couleurs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner2.setAdapter(adapter2);
        //   PoissonMortSpinner2.setOnItemSelectedListener(this);
        
        
        //à ajouter
        PoissonMortSpinner3 = findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.couleurs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner3.setAdapter(adapter3);
        //   PoissonMortSpinner3.setOnItemSelectedListener(this);
        
        
        PoissonMortSpinner10 = findViewById(R.id.spinner10);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner10.setAdapter(adapter10);
        //  PoissonMortSpinner10.setOnItemSelectedListener(this);
        
        PoissonMortSpinner7=findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter7=ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner7.setAdapter(adapter7);
        //  PoissonMortSpinner7.setOnItemSelectedListener(this);
        
        
        Intent intent=getIntent();
        Key=intent.getStringExtra("Key");
        Key2=intent.getStringExtra("Key2");
        
        Bac=intent.getStringExtra("Bac");
        Bac2=intent.getStringExtra("Bac2");
        Couleur1=intent.getStringExtra("Couleur1");
        Couleur2=intent.getStringExtra("Couleur2");
        
        NbBac=intent.getStringExtra("NbBac");
        
        // Nbac1 = intent.getStringExtra("Nbac1");
        //  Nbac2 = intent.getStringExtra("Nbac2");
        
        Lot=intent.getStringExtra("Lot");
        Lot2=intent.getStringExtra("Lot2");
        NbMale=intent.getStringExtra("NbMale");
        NbFemelle=intent.getStringExtra("NbFemelle");
        
        
        //operateur = intent.getStringExtra("operateur");
        
        Lignee=intent.getStringExtra("Lignee");
        Age=intent.getStringExtra("Age");
        Responsable=intent.getStringExtra("Responsable");
        Lignee2=intent.getStringExtra("Lignee2");
        Age2=intent.getStringExtra("Age2");
        Responsable2=intent.getStringExtra("Responsable2");
        
        
        // vient accouplemet
        
        
        //a ajouter
        //textViewLot = findViewById(R.id.tv_Lot);
        textViewBac=findViewById(R.id.tv_Bac);
        
        // textViewNbMale = findViewById(R.id.tv_NbMale);
        //   textViewNbFemelle = findViewById(R.id.tv_NbFemelle);
        
        
        textViewBac2=findViewById(R.id.tv_Bac2);
        textViewLignee=findViewById(R.id.tv_lignee);
        textViewLot=findViewById(R.id.tv_lot);
        textViewLignee2=findViewById(R.id.tv_lignee2);
        textViewLot2=findViewById(R.id.tv_lot2);
        textViewAge=findViewById(R.id.tv_age);
        textViewAge2=findViewById(R.id.tv_age2);

//        textViewLot.setText(Lot);
        textViewBac.setText(Bac);
        
        //??
        //  textViewNbMale.setText(NbMale);
        // textViewNbFemelle.setText(NbFemelle);
        
        textViewBac2.setText(Bac2);
        textViewLignee.setText(Lignee);
        textViewLignee2.setText(Lignee2);
        textViewLot.setText(Lot);
        textViewLot2.setText(Lot2);
        textViewAge.setText(Age);
        textViewAge2.setText(Age2);
        
        
    }
    
    public void lancersauvegarde(View view) {
        final String NbBac = PoissonMortSpinner.getSelectedItem().toString();
        final String Couleur1 = PoissonMortSpinner2.getSelectedItem().toString();
        final String Couleur2 = PoissonMortSpinner3.getSelectedItem().toString();
        
        final String NbMale = PoissonMortSpinner10.getSelectedItem().toString();
        final String NbFemelle = PoissonMortSpinner7.getSelectedItem().toString();
        final String operateur = OperateurSpinner.getSelectedItem().toString();
    
    
        WriteOnSheetAccouplement.writeData(this, operateur, NbBac, Couleur1, Couleur2, NbMale, NbFemelle, Lot, Lot2, Bac, Bac2, Lignee, Lignee2, Age, Age2, Responsable, Responsable2, Key, Key2);
        Intent intent = new Intent(this, ActivityMenu.class);
        
        startActivity(intent);
    }
    
    /**
     * public void lanceraccouplement(View view){
     * Intent intent = new Intent(this,ActivityAccouplement.class);
     * //à ajouter
     * final String NbMale = PoissonMortSpinner10.getSelectedItem().toString();
     * final String NbFemelle = PoissonMortSpinner7.getSelectedItem().toString();
     * <p>
     * intent.putExtra("operateur", operateur);
     * intent.putExtra("Bac2",Bac2);
     * intent.putExtra("Lot2",Lot2);
     * intent.putExtra("Lignee2",Lignee2);
     * intent.putExtra("Age2",Age2);
     * intent.putExtra("Responsable2",Responsable2);
     * <p>
     * intent.putExtra("NbFemelle", NbFemelle);
     * <p>
     * intent.putExtra("Bac", Bac);
     * intent.putExtra("Lot", Lot);
     * intent.putExtra("Responsable", Responsable);
     * intent.putExtra("Lignee", Lignee);
     * intent.putExtra("Age", Age);
     * <p>
     * intent.putExtra("NbMale", NbMale);
     * <p>
     * <p>
     * startActivity(intent);
     * }
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    
    }
}