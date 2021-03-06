package com.example.appbertille;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


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




    Poisson poisson;


    int score=0;



    //ajout
    
    //  TextView textViewitemDate, textViewBac, textViewLigneeMale, textViewLigneeFemelle, textViewResponsable;
    TextView textViewitemLot, textViewBac, textViewLignee, textViewAge, textViewResponsable;
    Integer Icon;
    Integer Liseret;

    Context context;

    Intent intent_poisson;
    ListView listView;

    Button button_souffrance;

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

        intent_poisson =  new Intent(this, ActivityEcrirRecapSouffrancePoisson.class);


        context=this;
        Intent intent_2=getIntent();


        main_user = getIntent().getStringExtra("main_user");

        // r??cup??ration des poissons s??l??ctionn??s
        Bundle extra = getIntent().getBundleExtra("extra");

        poisson = (Poisson) extra.getSerializable("poisson");


        listView = findViewById(R.id.list_poisson);

        /*Bac=intent.getStringExtra("Bac");
        main_user=intent.getStringExtra("main_user");
        Age=intent.getStringExtra("Age");
        Lignee=intent.getStringExtra("Lignee");
        Responsable=intent.getStringExtra("Responsable");
        Lot=intent.getStringExtra("Lot");
        Key=intent.getStringExtra("Key");*/
    
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

        //System.out.println("Bonjour ici l'image : "+poisson.getImage() );

        //Drawable drawable = getResources().getDrawable(poisson.getImage());
        //Icon.setImageDrawable(drawable);

        //Liseret.setImageDrawable(R.drawable.liseret_rouge);


        List<HashMap<String, String>> list = new ArrayList<>();


        Bac = poisson.getBac();
        Lot = poisson.getLot();
        Lignee = poisson.getLignee();
        Age = poisson.getAge();
        Responsable=poisson.getResponsable();
        Key=poisson.getKey();
        Icon=poisson.getImage();
        Liseret=poisson.getColor();

        HashMap<String,String> map= new HashMap<>();

        map.put("Bac", Bac);
        map.put("Lot", Lot);
        map.put("Lignee", Lignee);
        map.put("Age", Age);
        map.put("Responsable", Responsable);
        map.put("Key", Key);
        map.put("Image", Icon+"");
        map.put("Liseret", Liseret +"");


        // for (String pair:pairs){

        //String[] entry = pair.split("=");
        //map.put(entry[0].trim(), entry[1].trim());
        //System.out.println(entry[0]+"="+entry[1]);

        //}

        list.add(map);


        button_souffrance= findViewById(R.id.button_souffrance);

        SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image", "Liseret"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort, R.id.color_poisson_peril});

        listView.setAdapter(sadapter);

        Intent intent = new Intent(this, ActivityEcrirRecapSouffrancePoisson.class);


        this.button_souffrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String PoissonSouffrance = PoissonSouffranceSpinner.getSelectedItem().toString();
                int i=Integer.parseInt(PoissonSouffrance);
                int nb_poisson_mort=Integer.parseInt(PoissonSouffrance);

                if (PoissonSouffrance.equals("")){
                    Toast.makeText(getApplicationContext(), "Champ manquant", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Vous avez s??l??ctionn?? : "+PoissonSouffrance+" poissons", Toast.LENGTH_SHORT).show();



                    String caracteres = "0123456789abcdefghijklmnopqrstuvwxyz";
                    int longueur = 6;

                    StringBuilder Id = new StringBuilder(longueur);

                    Id.append("SO");

                    for (int j = 0; j < longueur; j++) {
                        int index = (int)(caracteres.length() * Math.random());
                        Id.append(caracteres.charAt(index));
                    }

                    System.out.println("ID:"+Id);


                    while(1<=i){

                        intent.putExtra("num", i+"");

                        intent.putExtra("nb_poisson_mort", nb_poisson_mort+"");
                        intent.putExtra("main_user", main_user);
                        intent.putExtra("Id", (CharSequence) Id);

                        Bundle extra = new Bundle();
                        extra.putSerializable("poisson", (Serializable) poisson);
                        intent.putExtra("extra", extra);

                        startActivity(intent);
                        i--;

                    }



                }
            }
        });

    }


    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancerrecherche(View view) {
        Intent intent = new Intent(this, ActivityRechercheRegistreMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }





}