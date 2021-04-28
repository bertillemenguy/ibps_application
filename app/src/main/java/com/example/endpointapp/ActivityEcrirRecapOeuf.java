package com.example.endpointapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class ActivityEcrirRecapOeuf extends AppCompatActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {
    
    private static final String TAG="ActivityOeuf";

    String main_user="";

    // String Nbac1 = "";
    //String Nbac2 = "";
    
    //  String Couleur1 = "";
    //  String Couleur2 = "";
    
    String NbBac="";
    String NbMale="";
    String NbFemelle="";
    String Bac2="";
    String Lot2="";
    
    String Key="";
    String Key2="";
    String LigneeF="";
    String Age2="";
    String Bac="";
    String operateur="";
    String LigneeM="";
    String Lot="";
    String Age="";
    String Date="";
    
    String Qualite="0";
    String Quantite="0";
    
    // String Responsable2 = "";
    // ajout
    
    
    // String Responsable = "";
    //ajout
    //TextView textView43;
    //TextView textView41;
    
    TextView date;
    TextView operateurAccouplement;
    TextView ligneemale;
    TextView ligneefemelle;
    TextView bacmale;
    TextView bacfemelle;
    TextView nbmale;
    TextView nbbac;
    TextView nbfemelle;
    TextView age;
    TextView age2;
    TextView lot;
    TextView lot2;
    
    
    Spinner spnrnbremale;
    Spinner spnnbrefemelles;
    
    
    //Spinner OperateurSpinner;
    private SmileRating mSmileRating;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_oeuf);
        mSmileRating = findViewById(R.id.ratingView);
        mSmileRating.setOnSmileySelectionListener(this);
        //   mSmileRating.setOnRatingSelectedListener(this);
        mSmileRating.setShowLine(false);
        
        
        //à ajouter
        spnrnbremale = findViewById(R.id.spnrnbremale);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrnbremale.setAdapter(adapter2);
        
        //à ajouter
        spnnbrefemelles = findViewById(R.id.spnnbrefemelles);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnnbrefemelles.setAdapter(adapter11);
        
        
        // operateur
       // OperateurSpinner = findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter);
        
        
        Intent intent = getIntent();
        
        Bac=intent.getStringExtra("Bac");
        Bac2=intent.getStringExtra("Bac2");
        Lot=intent.getStringExtra("Lot");
        Lot2=intent.getStringExtra("Lot2");
        NbMale=intent.getStringExtra("NbMale");
        NbFemelle=intent.getStringExtra("NbFemelle");
        operateur=intent.getStringExtra("operateur");
        NbBac=intent.getStringExtra("NbBac");
        Date=intent.getStringExtra("Date");
        LigneeM=intent.getStringExtra("LigneeM");
        Age=intent.getStringExtra("Age");
        LigneeF=intent.getStringExtra("LigneeF");
        Age2=intent.getStringExtra("Age2");
        Key=intent.getStringExtra("Key");
        Key2=intent.getStringExtra("Key2");
        
        // Couleur1 = intent.getStringExtra("Couleur1");
        // Couleur2 = intent.getStringExtra("Couleur2");
        
        //  Nbac1 = intent.getStringExtra("Nbac1");
        // Nbac2 = intent.getStringExtra("Nbac2");
        
        //ajout
        
        // Responsable = intent.getStringExtra("Responsable");
        
        
        // Responsable2 = intent.getStringExtra("Responsable2");
        //  NbFemelle = intent.getStringExtra("NbFemelle");
        
        
        //ajout
        
        
        date=findViewById(R.id.tv_date);
        operateurAccouplement=findViewById(R.id.tv_operateurAccouplement);
        nbmale=findViewById(R.id.tv_nbmale);
        ligneemale=findViewById(R.id.ligneemale);
        ligneefemelle=findViewById(R.id.ligneefemelle);
        bacmale=findViewById(R.id.bacmale);
        bacfemelle=findViewById(R.id.bacfemelle);
        nbbac=findViewById(R.id.tv_nbbac);
        nbfemelle=findViewById(R.id.nbfemelle);
        age=findViewById(R.id.tv_age);
        age2=findViewById(R.id.tv_age2);
        lot=findViewById(R.id.tv_lot);
        lot2=findViewById(R.id.tv_lot2);
        // textView43 = findViewById(R.id.textView43);
        // textView41 = findViewById(R.id.textView41);
        
        
        date.setText(Date);
        operateurAccouplement.setText(operateur);
        ligneemale.setText(LigneeM);
        ligneefemelle.setText(LigneeF);
        bacmale.setText(Bac);
        bacfemelle.setText(Bac2);
        nbbac.setText(NbBac);
        nbmale.setText(NbMale);
        nbfemelle.setText(NbFemelle);
        age.setText(Age);
        age2.setText(Age2);
        lot.setText(Lot);
        lot2.setText(Lot2);
        //  textView43.setText(Couleur1);
        //   textView41.setText(Couleur2);


       main_user=intent.getStringExtra("main_user");


    }
    
    @Override
    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Mauvais", Toast.LENGTH_SHORT).show();
                Quantite="2";
                break;
            case SmileRating.GOOD:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Bon", Toast.LENGTH_SHORT).show();
                Quantite="4";
                break;
            case SmileRating.GREAT:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Excellent", Toast.LENGTH_SHORT).show();
                Quantite="5";
                break;
            case SmileRating.OKAY:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Correct", Toast.LENGTH_SHORT).show();
                Quantite="3";
                break;
            case SmileRating.TERRIBLE:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Decevant", Toast.LENGTH_SHORT).show();
                Quantite="1";
                break;
            case SmileRating.NONE:
                Toast.makeText(ActivityEcrirRecapOeuf.this, "Rien", Toast.LENGTH_SHORT).show();
                Quantite="0";
                
                
                break;
        }
    }
    
    
    @Override
    public void onRatingSelected(int level, boolean reselected) {
        Log.i(TAG, "Rated as: " + level + " - " + reselected);
    }
    
    //Ajout
    public void lancersauvegarde(View view) {
        final String NbMalesFeconde = spnrnbremale.getSelectedItem().toString();
        final String NbfemellesFeconde = spnnbrefemelles.getSelectedItem().toString();

    
        WriteOnSheetOeuf.writeData(this, main_user, Qualite, Quantite, NbBac, NbMale, NbFemelle, Bac, Bac2, LigneeM, LigneeF, Age, Age2, Lot, Lot2, Date, NbMalesFeconde, NbfemellesFeconde, Key, Key2);
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("operateur", operateur);
        intent.putExtra("Quantite", Quantite);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
        
        
        /**
         public void lanceroeuf2(View view) {
         Intent intent = new Intent(this,ActivityOeuf2.class);
         intent.putExtra("operateur", operateur);
         intent.putExtra("Quantite", Quantite);
         intent.putExtra("Bac2",Bac2);
         intent.putExtra("Lot2",Lot2);
         intent.putExtra("LigneeF",LigneeF);
         intent.putExtra("Age2",Age2);
         intent.putExtra("Responsable2",Responsable2);
         intent.putExtra("NbFemelle", NbFemelle);
         intent.putExtra("Bac", Bac);
         intent.putExtra("Lot", Lot);
         intent.putExtra("Responsable", Responsable);
         intent.putExtra("LigneeM", LigneeM);
         intent.putExtra("Age", Age);
         intent.putExtra("NbMale", NbMale);
         intent.putExtra("Couleur1", Couleur1);
         intent.putExtra("Couleur2", Couleur2);
         intent.putExtra("NbBac", NbBac);
         intent.putExtra("Nbac1", Nbac1);
         intent.putExtra("Nbac2", Nbac2);
         
         
         startActivity(intent);
         */
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
}