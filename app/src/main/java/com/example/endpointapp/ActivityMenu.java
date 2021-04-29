package com.example.endpointapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ActivityMenu extends AppCompatActivity {

    /*---------------------ACCUEIL-------------------------*/
    /*-----------------------------------------------------*/

    TextView main_user_view;
    String main_user = "";

    /**
     * @param savedInstanceState
     */
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");
        this.main_user_view = findViewById(R.id.main_user);

        main_user_view.setText(main_user);

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
        Intent intent=new Intent(this, ActivityRechercheRegistre.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
        
    }
    
    public void lancerHistoriqueAccouplments(View view) {
        Intent intent=new Intent(this, ActivityHistoriqueAccouplements.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Historique oeufs
    public void lancerHistoriqueOeufs(View view) { //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueOeufs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Historiques des morts
    public void lancerhistoriqueMorts(View view) { //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Historique Souffrance
    public void lancerHistoriqueSouffrance(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueSouffrance.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Historique Gestion des Bacs
    public void lancerHistoriqueBacs(View view) {// String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheGestionBacs.class);
        intent.putExtra("main_user", main_user);
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
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //historique reservation zebrafish
    public void lancerhistoriquereservationzebrafish(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueReservationZebrafish.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //historique reservation piece injection
    public void lancerhistoriquereservationinjection(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueReservationInjection.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //historique animalerie remplissage
    public void lancerhistoriqueanimalerieremplissage(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityWebHistoriqueAnimalerieRemplissage.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //historique animalerie Alevins
    public void lancerhistoriqueanimaleriealevins(View view) {
        //String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityHistoriqueAnimalerieAlevins.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    
    //ECRITURE
    //Ecriture un accouplements
    public void lancerAccouplements(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreAccouplementsMale.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Ecriture Qualité et Quantite Oeufs
    public void lanceroeuf(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent=new Intent(this, ActivityHistoriqueAccouplementsPourOeufs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Ecriture des morts
    public void lancerMorts(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //ecriture souffrance
    public void lancerSouffrance(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreSouffrance.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //Ecriture Gestion des Bacs
    public void lancerBacs(View view) {
        // String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityRechercheRegistreBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
        
        // Historique des accouplements
    }
    
    //ecriture incidents
    public void lancerIncidents(View view) {
        //  String operateur = OperateurSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, ActivityEcrirRecapIncidents.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    //deconnexion avec le logo en haut à droite
    public void deconnexion(View view){
        Intent intent = new Intent(this, ActivityConnexion.class);
        startActivity(intent);
    }

}
