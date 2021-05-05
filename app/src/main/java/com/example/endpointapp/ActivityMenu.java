package com.example.endpointapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class ActivityMenu extends AppCompatActivity {

    /*---------------------ACCUEIL-------------------------*/
    /*-----------------------------------------------------*/

    ProgressDialog loading;
    String compteur_bacs;
    String compteur_incidents;

    int int_compteur_bacs=0;
    int int_compteur_incidents=0;
    TextView main_user_view, compteur_view_bacs, compteur_view_incidents;
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


        this.compteur_view_bacs=findViewById(R.id.compteur_bacs);
        this.compteur_view_incidents=findViewById(R.id.compteur_incidents);


        //this.compteur_incidents="3";
        //compteur_view_incidents.setText(compteur_incidents);

        main_user_view.setText(main_user);

        TextClock textClock;
        textClock=findViewById(R.id.textClock);
        //  textClock.setFormat12Hour(null);
        //textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");
        textClock.setFormat24Hour("hh:mm  EEE d MMM ");

        getItems_bacs();
        getItems_incidents();

    }

    private void getItems_bacs() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbws-fhc9nsXmYFgqVL2K5UStbLoV43q9M1O_OCZ/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems_bacs(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
       // loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void parseItems_bacs(String jsonResponce) {
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String SItraite=jo.getString("SItraite");
                if (SItraite.equalsIgnoreCase("En cours")){
                    // affectation compteur
                    int_compteur_bacs++;
                }
            }
            if (int_compteur_bacs>=1){
                this.compteur_bacs= String.valueOf(int_compteur_bacs);
                compteur_view_bacs.setText(compteur_bacs);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getItems_incidents() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxv_6vHp6__F0NdJ8BWtqWhhC9JgkpVcHpD0tfbT0ETgvQwunmAzXdtPfsVUqTKBYHinw/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems_incidents(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        //loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    private void parseItems_incidents(String jsonResponce) {
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String etat = jo.getString("etat");
                if (etat.equalsIgnoreCase("En cours")){
                    // affectation compteur
                    int_compteur_incidents++;
                }
            }
            if (int_compteur_incidents>=1){
                this.compteur_incidents= String.valueOf(int_compteur_incidents);
                compteur_view_incidents.setText(compteur_incidents);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void setCompteur(String compteur){this.compteur_bacs=compteur_bacs;}


}
