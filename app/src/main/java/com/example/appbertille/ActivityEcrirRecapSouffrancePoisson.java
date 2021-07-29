package com.example.appbertille;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ActivityEcrirRecapSouffrancePoisson extends AppCompatActivity {

    String main_user="";
    Button Valider;
    TextView textView;
    String num, nb_poisson_mort;
    Date date = null;

    SouffranceAdapter adapter;
    ProgressDialog loading;
    ArrayList<Souffrance> list_souffrance;
    String Bac="";
    String Lignee="";
    String Lot="";
    String Age="";
    String Responsable="";
    String Key="";

    String Id;
    String Euthanasie="0";
    String Surveillance="0";
    String Isolement="0";
    String Ras="0";

    Integer Icon , Liseret;

    int PointLimite=3;

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

    Poisson poisson;

    ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_poisson);
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
        num = intent.getStringExtra("num");
        nb_poisson_mort = intent.getStringExtra("nb_poisson_mort");
        Id = intent.getStringExtra("Id");

        getItems();

        Valider = findViewById(R.id.btn_valider);
        textView = findViewById(R.id.numero_poisson);

        Intent intent_2=getIntent();
        // récupération des poissons séléctionnés
        Bundle extra = getIntent().getBundleExtra("extra");

        poisson = (Poisson) extra.getSerializable("poisson");

        listView = findViewById(R.id.list_item_select);

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

        list.add(map);

        SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image", "Liseret"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort, R.id.color_poisson_peril});

        listView.setAdapter(sadapter);


        textView.setText("Entrer données poisson n°"+num);

        this.Valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerresultat(v);
                fermeractivite(v);
            }
        });

    }

    public void fermeractivite(View view) {
        this.finish();
    }


    public void lancerresultat(View view) {
        //Intent intent=new Intent();
        int score=Integer.parseInt(Position) + Integer.parseInt(Nage) + Integer.parseInt(Malnutrition) + Integer.parseInt(Prostration) + Integer.parseInt(Nageoire) + Integer.parseInt(Maigreur) + Integer.parseInt(Obesite) + Integer.parseInt(Blessure) + Integer.parseInt(Ulcere) + Integer.parseInt(Scoliose) + Integer.parseInt(Exophtalmie) + Integer.parseInt(Opercules) + Integer.parseInt(Couleur);
        if (score >= PointLimite) {
            //intent=new Intent(this, ActivityEuthanasie.class);
            Toast.makeText(getApplicationContext(), "Euthanasie", Toast.LENGTH_LONG).show();
            Euthanasie="1";

            boolean trouve=false;
            for (int i =0; list_souffrance.size()>i;i++){
                if(list_souffrance.get(i).getId().equals(Id)){
                    if (list_souffrance.get(i).getEuthanasie().equals("0")){
                        WriteOnSheetSouffrance.updateData(this, Euthanasie, list_souffrance.get(i).getIsolement(), list_souffrance.get(i).getSurveillance(), list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        int nb=Integer.parseInt(list_souffrance.get(i).getEuthanasie())+1;
                        Euthanasie= nb+"";
                        WriteOnSheetSouffrance.updateData(this, Euthanasie, list_souffrance.get(i).getIsolement(), list_souffrance.get(i).getSurveillance(), list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list_souffrance.get(i).setEuthanasie(Euthanasie);
                    trouve=true;
                }
            }

            if (!trouve){
                WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, nb_poisson_mort, Key, Id);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else if ((score < PointLimite) && (Ulcere.equals("3"))) {
            //intent=new Intent(this, ActivityIsolement.class);
            Toast.makeText(getApplicationContext(), "Isolement", Toast.LENGTH_LONG).show();
            Isolement="1";


            boolean trouve=false;
            for (int i =0; list_souffrance.size()>i;i++){
                if(list_souffrance.get(i).getId().equals(Id)){
                    if (list_souffrance.get(i).getIsolement().equals("0")){
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), Isolement, list_souffrance.get(i).getSurveillance(), list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        int nb=Integer.parseInt(list_souffrance.get(i).getIsolement())+1;
                        Isolement= nb+"";
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), Isolement, list_souffrance.get(i).getSurveillance(), list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list_souffrance.get(i).setIsolement(Isolement);
                    trouve=true;
                }
            }

            if (!trouve){
                WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, nb_poisson_mort, Key, Id);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else if ((score + 1) < PointLimite) {
            //intent = new Intent(this, ActivityRas.class);
            Toast.makeText(getApplicationContext(), "Ras", Toast.LENGTH_LONG).show();
            Ras = "1";

            boolean trouve=false;
            for (int i =0; list_souffrance.size()>i;i++){
                if(list_souffrance.get(i).getId().equals(Id)){
                    if (list_souffrance.get(i).getAucune_action_a_mener().equals("0")){
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), list_souffrance.get(i).getIsolement(), list_souffrance.get(i).getSurveillance(), Ras, Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        int nb=Integer.parseInt(list_souffrance.get(i).getAucune_action_a_mener())+1;
                        Ras= nb+"";
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), list_souffrance.get(i).getIsolement(), list_souffrance.get(i).getSurveillance(), Ras, Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list_souffrance.get(i).setAucune_action_a_mener(Ras);
                    trouve=true;
                }
            }

            if (!trouve){
                WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, nb_poisson_mort, Key, Id);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } else if (score == (PointLimite - 1)) {
            //intent=new Intent(this, ActivitySurveillance.class);
            Toast.makeText(getApplicationContext(), "Surveillance", Toast.LENGTH_LONG).show();
            Surveillance="1";

            boolean trouve=false;
            for (int i =0; list_souffrance.size()>i;i++){
                if(list_souffrance.get(i).getId().equals(Id)){
                    if (list_souffrance.get(i).getSurveillance().equals("0")){
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), list_souffrance.get(i).getIsolement(), Surveillance, list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        int nb=Integer.parseInt(list_souffrance.get(i).getSurveillance())+1;
                        Surveillance= nb+"";
                        WriteOnSheetSouffrance.updateData(this, list_souffrance.get(i).getEuthanasie(), list_souffrance.get(i).getIsolement(), Surveillance, list_souffrance.get(i).getAucune_action_a_mener(), Id);
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list_souffrance.get(i).setSurveillance(Surveillance);
                    trouve=true;
                }
            }

            if (!trouve){
                WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, nb_poisson_mort, Key, Id);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        /*intent.putExtra("main_user", main_user);
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
        intent.putExtra("Prostration", Prostration);*/

        //startActivity(intent);



    }


    public void onCheckboxClicked(View view) {
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
    }




    private void getItems() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycby83kflTnHKd3L0rn9KncsKif-vhhfRbmZrkuoP11g4ygspd5QH5yTDEEvPjXm0WPY/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response);
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


    private void parseItems(String jsonResponce) {
        list_souffrance = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);

                try {
                    date = inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String Date = outputFormat.format(date);

                String Age = jo.getString("Age");
                String Bac = jo.getString("Bac");
                String Lignee = jo.getString("Lignee");
                String Lot = jo.getString("Lot");
                String Responsable = jo.getString("Responsable");

                String Euthanasie = jo.getString("Euthanasie");
                String Isolement = jo.getString("Isolement");
                String Surveillance = jo.getString("Surveillance");
                String Aucune_action_a_mener = jo.getString("Aucune_action_a_mener");
                String PoissonSouffrance = jo.getString("PoissonSouffrance");

                String Id = jo.getString("Id");

                list_souffrance.add(new Souffrance(Date, Age, Bac, Lignee, Lot, Responsable, Euthanasie, Isolement, Surveillance, Aucune_action_a_mener, PoissonSouffrance, Id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}