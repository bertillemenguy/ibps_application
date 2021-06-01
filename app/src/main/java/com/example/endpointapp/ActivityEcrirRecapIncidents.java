package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ActivityEcrirRecapIncidents extends AppCompatActivity {
    
    /**
     * String Lignee = "";
     * String Lot = "";
     * String Age = "";
     * String Responsable = "";
     */
    /**
     
     String CoupureEau = "0";
     String Panne = "0";
     String CoupureProg = "0";
     String CoupureInop = "0";
     String ArretAir = "0";
     String ArretCTA = "0";
     String PH = "0";
     String Conductivite = "0";
     String NO2 = "0";
     String NO3 = "0";
     String NH4 = "0";
     String Temperature = "0";
     String ArretSysteme = "0";
     String Fuite = "0";
     String UV = "0";
     String Autre = "0";
     String Vibrations = "0";
     String Divers = "0";
     String operateur = "";
     */
    //  String Bac = "";

    String main_user = "";
    //Spinner OperateurSpinner;
    //  ProgressDialog loading;
    
    Spinner eauvilleSpinner;
    Spinner electriciteSpinner;
    Spinner aircomprimeSpinner;
    Spinner climatisationSpinner;
    Spinner eaudusystemeSpinner;
    Spinner systemeaquatiqueSpinner;
    Spinner travauxSpinner;
    
    Spinner nourrissageSpinner;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_incidents);
        //nourrissage
        
        nourrissageSpinner = findViewById(R.id.spnourrissage);
        ArrayAdapter<CharSequence> adapter28 = ArrayAdapter.createFromResource(this, R.array.nourrisage, android.R.layout.simple_spinner_item);
        adapter28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nourrissageSpinner.setAdapter(adapter28);
        
        
        // operateur
        //OperateurSpinner = findViewById(R.id.spoperateur);
        //ArrayAdapter<CharSequence> adapter27 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter27);
        
        
        // eau de ville
        eauvilleSpinner = findViewById(R.id.speauville);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this, R.array.eaudeville, android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eauvilleSpinner.setAdapter(adapter18);
        
        // electriciteSpinner
        electriciteSpinner = findViewById(R.id.spelectricite);
        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this, R.array.electricite, android.R.layout.simple_spinner_item);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        electriciteSpinner.setAdapter(adapter19);
        // aircomprimeSpinner
        aircomprimeSpinner = findViewById(R.id.spaircomprime);
        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this, R.array.aircomprime, android.R.layout.simple_spinner_item);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aircomprimeSpinner.setAdapter(adapter20);
        
        // climatisationSpinner
        climatisationSpinner = findViewById(R.id.spclim);
        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this, R.array.climatisation, android.R.layout.simple_spinner_item);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        climatisationSpinner.setAdapter(adapter21);
        
        // eaudusystemeSpinner
        eaudusystemeSpinner = findViewById(R.id.speausysteme);
        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this, R.array.eausysteme, android.R.layout.simple_spinner_item);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eaudusystemeSpinner.setAdapter(adapter22);
        
        // systemeaquatiqueSpinner
        systemeaquatiqueSpinner = findViewById(R.id.spsystemeaquatique);
        ArrayAdapter<CharSequence> adapter23 = ArrayAdapter.createFromResource(this, R.array.systemeaquatique, android.R.layout.simple_spinner_item);
        adapter23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemeaquatiqueSpinner.setAdapter(adapter23);
        
        // travauxSpinne
        travauxSpinner = findViewById(R.id.sptravaux);
        ArrayAdapter<CharSequence> adapter24 = ArrayAdapter.createFromResource(this, R.array.travaux, android.R.layout.simple_spinner_item);
        adapter24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travauxSpinner.setAdapter(adapter24);


        Intent intent = getIntent();
        main_user= intent.getStringExtra("main_user");
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancersauvegarde(View view) {
        final String eaudeville = eauvilleSpinner.getSelectedItem().toString();
        final String electricite = electriciteSpinner.getSelectedItem().toString();
        final String aircomprime = aircomprimeSpinner.getSelectedItem().toString();
        final String climatisation = climatisationSpinner.getSelectedItem().toString();
        final String eaudusysteme = eaudusystemeSpinner.getSelectedItem().toString();
        final String systemeaquatique = systemeaquatiqueSpinner.getSelectedItem().toString();
        final String travaux = travauxSpinner.getSelectedItem().toString();
        final String nourrissage = nourrissageSpinner.getSelectedItem().toString();
        final String etat = "En cours";
    
        WriteOnSheetSignaler.writeData(this, main_user, eaudeville, electricite, aircomprime, climatisation, eaudusysteme, systemeaquatique, travaux, nourrissage, etat);
        //CoupureEau, Panne, CoupureProg, CoupureInop, ArretAir, ArretCTA, PH, Conductivite, NO2, NO3, NH4, Temperature, ArretSysteme, Fuite, UV, Autre, Vibrations, Divers);

        //Temps d'attente !!! IMPORTANT
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    /**
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cbcoupureeau:
                if (checked)
                    CoupureEau = "1";
                else
                    CoupureEau = "";
                break;
            case R.id.cbpanneosmoseur:
                if (checked)
                    Panne = "1";
                else
                    Panne = "";
                break;
            case R.id.cbcoupureprogrammee:
                if (checked)
                    CoupureProg = "1";
                else
                    CoupureProg = "";
                break;
            case R.id.cbcoupureinopine:
                if (checked)
                    CoupureInop = "1";
                else
                    CoupureInop = "";
                break;
            case R.id.cbarretair:
                if (checked)
                    ArretAir = "1";
                else
                    ArretAir = "";
                break;
            case R.id.cbarretcta:
                if (checked)
                    ArretCTA = "1";
                else
                    ArretCTA = "";
                break;
            case R.id.cbph:
                if (checked)
                    PH = "1";
                else
                    PH = "";
                break;
            case R.id.cbconductivite:
                if (checked)
                    Conductivite = "1";
                else
                    Conductivite = "";
                break;
            case R.id.cbno2:
                if (checked)
                    NO2 = "1";
                else
                    NO2 = "";
                break;
            case R.id.cbno3:
                if (checked)
                    NO3 = "1";
                else
                    NO3 = "";
                break;
            case R.id.cbnh4:
                if (checked)
                    NH4 = "1";
                else
                    NH4 = "";
                break;
            case R.id.cbtemperature:
                if (checked)
                    Temperature = "1";
                else
                    Temperature = "";
                break;
            case R.id.cbarretsysteme:
                if (checked)
                    ArretSysteme = "1";
                else
                    ArretSysteme = "";
                break;
            case R.id.cbfuite:
                if (checked)
                    Fuite = "1";
                else
                    Fuite = "";
                break;
            case R.id.cbuv:
                if (checked)
                    UV = "1";
                else
                    UV = "";
                break;
            case R.id.cbautrepanne:
                if (checked)
                    Autre = "1";
                else
                    Autre = "";
                break;
            case R.id.cbvibrations:
                if (checked)
                    Vibrations = "1";
     else
     Vibrations = "";
     break;
     case R.id.cbdivers:
     if (checked)
     Divers = "1";
     else
     Divers = "";
     break;
     }
     
     }
     */
}
