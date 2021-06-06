package com.example.endpointapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityEcrirRecapSouffrance extends AppCompatActivity {
    
    Spinner PoissonSouffranceSpinner;
    
    //  String Bac, Lot, Lignee, Age, Responsable;
    
    
    String Bac="";
    String main_user="";
    String Lignee="";
    String Lot="";
    String Age="";
    String Responsable="";
    String Key="";
    
    
    String Euthanasie="";
    String Surveillance="";
    String Isolement="";
    String Ras="";
    String PointLimite="3";
    
    String Position="0";
    String Nage="0";
    String Malnutrition="0";
    String Prostration="0";

    String Nageoire = "0";
    String Maigreur = "0";
    String Obesite = "0";
    String Blessure = "0";
    String Ulcere="0";
    String Scoliose="0";
    String Exophtalmie="0";
    String Opercules="0";
    String Couleur="0";
    
    int score=0;
    
    //ajout
    
    //  TextView textViewitemDate, textViewBac, textViewLigneeMale, textViewLigneeFemelle, textViewResponsable;
    TextView textViewitemLot, textViewBac, textViewLignee, textViewAge, textViewResponsable;
    //ajout
    //Spinner OperateurSpinner;
    
    /**
     * String Position = "0";
     * String Nage = "0";
     * String Malnutrition = "0";
     * String Prostration = "0";
     * String operateur = "0";
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_souffrance);
        Intent intent=getIntent();

        Bac=intent.getStringExtra("Bac");
        main_user=intent.getStringExtra("main_user");
        Age=intent.getStringExtra("Age");
        Lignee=intent.getStringExtra("Lignee");
        Responsable=intent.getStringExtra("Responsable");
        Lot=intent.getStringExtra("Lot");
        Key=intent.getStringExtra("Key");
    
        // PointLimite = intent.getStringExtra("PointLimite");
    
    
        /**
         Position = intent.getStringExtra("Position");
         Nage = intent.getStringExtra("Nage");
         Malnutrition = intent.getStringExtra("Malnutrition");
         Prostration = intent.getStringExtra("Prostration");
     
         */
        // operateur
        //OperateurSpinner = findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter17);
    
        PoissonSouffranceSpinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonSouffranceSpinner.setAdapter(adapter);
        //  PoissonSouffranceSpinner.setOnItemSelectedListener(this);
    
    
        //ajout
        textViewitemLot=findViewById(R.id.tv_lot);
        textViewBac=findViewById(R.id.tv_Bac);
        textViewLignee=findViewById(R.id.lignee);
        textViewAge=findViewById(R.id.tv_age);
        textViewResponsable=findViewById(R.id.tv_responsable);
    
    
        textViewitemLot.setText(Lot);
        textViewBac.setText(Bac);
        textViewLignee.setText(Lignee);
        textViewAge.setText(Age);
        textViewResponsable.setText(Responsable);

    
    }
    
    /**
     * @param view
     */
    /*public void lancerresultat(View view) {
        int PL=Integer.parseInt(PointLimite);
        Intent intent=new Intent();
        score=Integer.parseInt(Position) + Integer.parseInt(Nage) + Integer.parseInt(Malnutrition) + Integer.parseInt(Prostration) + Integer.parseInt(Nageoire) + Integer.parseInt(Maigreur) + Integer.parseInt(Obesite) + Integer.parseInt(Blessure) + Integer.parseInt(Ulcere) + Integer.parseInt(Scoliose) + Integer.parseInt(Exophtalmie) + Integer.parseInt(Opercules) + Integer.parseInt(Couleur);
        if (score >= PL) {
            intent=new Intent(this, ActivityEuthanasie.class);
            Euthanasie="1";
        } else if ((score < PL) && (Ulcere.equals("3"))) {
            intent=new Intent(this, ActivityIsolement.class);
            Isolement="1";
        } else if ((score + 1) < PL) {
            intent = new Intent(this, ActivityRas.class);
            Ras = "1";
        } else if (score == (PL - 1)) {
            intent=new Intent(this, ActivitySurveillance.class);
            Surveillance="1";
        }
        intent.putExtra("main_user", main_user);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        intent.putExtra("Key", Key);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Euthanasie", Euthanasie);
        intent.putExtra("Isolement", Isolement);
        intent.putExtra("Surveillance", Surveillance);
        intent.putExtra("Ras", Ras);
        //ajout
        intent.putExtra("Position", Position);
        intent.putExtra("Nage", Nage);
        intent.putExtra("Malnutrition", Malnutrition);
        intent.putExtra("Prostration", Prostration);

        final String PoissonSouffrance = PoissonSouffranceSpinner.getSelectedItem().toString();
        startActivity(intent);
        WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, PoissonSouffrance, Key);
        
        
    }*/


    public void valider(View view){
        final String PoissonSouffrance = PoissonSouffranceSpinner.getSelectedItem().toString();
        if (PoissonSouffrance.equals("")){
            Toast.makeText(getApplicationContext(), "Champ manquant", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("Vous avez séléectionné : "+PoissonSouffrance+" poissons");
        }

    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancerrecherche(View view) {
        Intent intent = new Intent(this, ActivityRechercheRegistreMorts.class);
        intent.putExtra("main_user", main_user);
        /**
         intent.putExtra("operateur", operateur);
         intent.putExtra("Bac", Bac);
         intent.putExtra("Lot", Lot);
         intent.putExtra("Responsable", Responsable);
         intent.putExtra("Lignee", Lignee);
         intent.putExtra("Age", Age);
         intent.putExtra("PointLimite", PointLimite);
         */
        startActivity(intent);
    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        /**
         intent.putExtra("Bac", Bac);
         intent.putExtra("Lot", Lot);
         intent.putExtra("Responsable", Responsable);
         intent.putExtra("Lignee", Lignee);
         intent.putExtra("Age", Age);
         intent.putExtra("PointLimite", PointLimite);
         */
        startActivity(intent);
    }
    
    
    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkboxalterationnageoire:
                if (checked)
                    Nageoire = "1";
                else
                    Nageoire = "";
                break;
            case R.id.checkboxmaigreur:
                if (checked)
                    Maigreur = "1";
                else
                    Maigreur = "";
                break;
            case R.id.checkboxobesite:
                if (checked)
                    Obesite = "1";
                else
                    Obesite = "";
                break;
            case R.id.checkboxblessurelesion:
                if (checked)
                    Blessure = "1";
                else
                    Blessure = "";
                break;
            case R.id.checkboxulceremycosesaignement:
                if (checked)
                    Ulcere = "3";
                else
                    Ulcere = "";
                break;
            case R.id.checkboxscoliose:
                if (checked)
                    Scoliose = "1";
                else
                    Scoliose = "";
                break;
            case R.id.checkboxexophtalmiedeformation:
                if (checked)
                    Exophtalmie = "1";
                else
                    Exophtalmie = "";
                break;
            case R.id.checkboxoperculeouverte:
                if (checked)
                    Opercules = "1";
                else
                    Opercules = "";
                break;
            case R.id.checkboxchangementdecouleur:
                if (checked)
                    Couleur = "1";
                else
                    Couleur = "";
                
                //ajout
            case R.id.checkboxpositioninhabituelle:
                if (checked)
                    Position = "1";
                else
                    Position = "";
                break;
            case R.id.checkboxmalnutrition:
                if (checked)
                    Malnutrition = "1";
                else
                    Malnutrition = "";
                break;
            case R.id.checkboxnageanormale:
                if (checked)
                    Nage = "1";
                else
                    Nage = "";
                break;
            case R.id.checkboxprostration:
                if (checked)
                    Prostration = "1";
                else
                    Prostration = "";
                break;
        }
    }*/
}