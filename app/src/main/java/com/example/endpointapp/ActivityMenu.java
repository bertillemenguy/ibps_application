package com.example.endpointapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextClock;


public class ActivityMenu extends AppCompatActivity {

    /*---------------------ACCUEIL-------------------------*/
    /*-----------------------------------------------------*/

    // String operateur = "";
    // Spinner OperateurSpinner;
    
    /**
     * @param savedInstanceState
     */
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        
        // Get the transferred data from source activity.
        //    Intent intent = getIntent();
        
        
        TextClock textClock;
        textClock=findViewById(R.id.textClock);
        //  textClock.setFormat12Hour(null);
        //textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");
        textClock.setFormat24Hour("hh:mm  EEE d MMM ");
        
        
    }
    
    /**
     * @param view
     */
    //HISTORIQUES
    //Registre
    public void lancerRegistre(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent=new Intent(this, ActivityRechercheRegistre.class);
        // intent.putExtra("operateur", operateur);
        startActivity(intent);
        
    }
    
    public void lancerHistoriqueAccouplments(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent=new Intent(this, ActivityHistoriqueAccouplements.class);
        //  intent.putExtra("operateur",operateur);
        startActivity(intent);
    }
    
    //Historique oeufs
    public void lancerHistoriqueOeufs(View view) { //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueOeufs.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //Historiques des morts
    public void lancerhistoriqueMorts(View view) { //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueMorts.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //Historique Souffrance
    public void lancerHistoriqueSouffrance(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueSouffrance.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //Historique Gestion des Bacs
    public void lancerHistoriqueBacs(View view) {// String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheGestionBacs.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    /**
     * public void lancerhistoriqueincidents(View view) {
     * //String operateur = OperateurSpinner.getSelectedItem().toString();
     * Intent intent = new Intent(this, ActivityHistoriqueIncidents.class);
     * //intent.putExtra("operateur", operateur);
     * startActivity(intent);
     * }
     */
    
    public void lancerHistoriqueIncidents(View view) {// String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueIncidents.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //historique reservation zebrafish
    public void lancerhistoriquereservationzebrafish(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueReservationZebrafish.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //historique reservation piece injection
    public void lancerhistoriquereservationinjection(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueReservationInjection.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //historique animalerie remplissage
    public void lancerhistoriqueanimalerieremplissage(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueAnimalerieRemplissage.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //historique animalerie Alevins
    public void lancerhistoriqueanimaleriealevins(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueAnimalerieAlevins.class);
        //intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    
    //ECRITURE
    //Ecriture un accouplements
    public void lancerAccouplements(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreAccouplementsMale.class);
        //  intent.putExtra("operateur",operateur);
        startActivity(intent);
    }
    
    //Ecriture Qualité et Quantite Oeufs
    public void lanceroeuf(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent=new Intent(this, ActivityHistoriqueAccouplementsPourOeufs.class);
        // intent.putExtra("operateur",operateur);
        startActivity(intent);
    }
    
    //Ecriture des morts
    public void lancerMorts(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreMorts.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //ecriture souffrance
    public void lancerSouffrance(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreSouffrance.class);
        // intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    //Ecriture Gestion des Bacs
    public void lancerBacs(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreBacs.class);
        //  intent.putExtra("operateur",operateur);
        startActivity(intent);
        
        // Historique des accouplements
    }
    
    //ecriture incidents
    public void lancerIncidents(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityEcrirRecapIncidents.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    
  
    
  
    
    
   
    
}
