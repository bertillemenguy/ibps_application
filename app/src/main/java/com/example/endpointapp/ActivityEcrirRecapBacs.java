package com.example.endpointapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityEcrirRecapBacs extends AppCompatActivity implements Serializable {


    // élément checkbox
    List<Poisson> list_select;
    PoissonAdapter adapter_poisson;

    TextView textViewitemLignee, textViewBac, textViewLot, textViewAge, textViewResponsable;

    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;

    //String Bac,Lot,Lignee,Age,Responsable,PointLimite,Elimine,Bac2,NouveauBac,Lignee2, Lot2;
    String Key="";
    String Key2="";
    String Bac="";
    String Lot="";
    String Lignee="";
    String Age="";
    String Responsable="";
    String PointLimite="";
    String Action="";
    String Bac2="";
    String NouveauBac="";
    String Lignee2="";
    String Lot2 = "";


    String main_user = "";

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};


    //Spinner OperateurSpinner;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_ecrir_recap_bacs);

        listView = findViewById(R.id.listView);
        // operateur
        // operateur = intent.getStringExtra("operateur");
        //OperateurSpinner = findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter17);
        // operateur = intent.getStringExtra("operateur");
        
        Intent intent = getIntent();


        //Bac=intent.getStringExtra("Bac");
        //Lot=intent.getStringExtra("Lot");
        //Lignee=intent.getStringExtra("Lignee");
        //Age=intent.getStringExtra("Age");
        //Key=intent.getStringExtra("Key");
    
        //textViewitemLignee=findViewById(R.id.tv_lignee);
        //textViewBac=findViewById(R.id.tv_Bac);
        //textViewAge=findViewById(R.id.tv_age);
        //textViewLot=findViewById(R.id.tv_lot);
        //textViewResponsable=findViewById(R.id.tv_responsable);
    
    
        //textViewLot.setText(Lot);
        //textViewBac.setText(Bac);
        //textViewitemLignee.setText(Lignee);
        //textViewAge.setText(Age);
        //textViewResponsable.setText(Responsable);
    
        // Get the transferred data from source activity.
        main_user = intent.getStringExtra("main_user");

        //élément checkbox

        getItems();

    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    

    // Réunir
    public void lancerreunir(View view) {
        //Intent intent = new Intent(this, ActivityReunirBacs.class);

        Intent intent_2 = new Intent(this, ActivityReunificationBacs.class);

        System.out.println("Bouton cliqué___________________");
        System.out.println(adapter_poisson.getSelected());

        if ((adapter_poisson.getSelected().size()>2)||(adapter_poisson.getSelected().size()<=1)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 2 éléments", Toast.LENGTH_SHORT).show();
        } else{
            list_select = adapter_poisson.getSelected();
            System.out.println(list_select);

            intent_2.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent_2.putExtra("extra", extra);
            intent_2.putExtra("list_select", (Serializable) list_select);

            startActivity(intent_2);
        }

        //intent.putExtra("main_user", main_user);
        //intent.putExtra("Bac", Bac);
        //intent.putExtra("Lot", Lot);
        //intent.putExtra("Responsable", Responsable);
        //intent.putExtra("Lignee", Lignee);
        //intent.putExtra("Age", Age);
        //intent.putExtra("Key", Key);
        //startActivity(intent);
    }

    // Déplacer bac
    public void lancerdeplacer(View view) {

        Intent intent=new Intent(this, ActivityDeplacerBacs.class);

        list_select = adapter_poisson.getSelected();


        if ((list_select.size()>1)||(list_select.size()==0)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 éléments", Toast.LENGTH_SHORT).show();
        } else {

            intent.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent.putExtra("extra", extra);
            intent.putExtra("list_select", (Serializable) list_select);
            startActivity(intent);

        }
    }


    //Dupliquer
    public void lancerdupliquer(View view) {

        Intent intent=new Intent(this, ActivityDupliquerBacs.class);
        list_select = adapter_poisson.getSelected();

        if ((list_select.size()>1)||(list_select.size()==0)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 éléments", Toast.LENGTH_SHORT).show();
        } else {

            intent.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent.putExtra("extra", extra);
            intent.putExtra("list_select", (Serializable) list_select);
            startActivity(intent);

        }

    }
    
    /*public void lancersignaler(View view) {
        Intent intent=new Intent(this, ActivityErreur.class);
        intent.putExtra("main_user", main_user);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
        startActivity(intent);
    }*/


    // Eliminer bac(s)
    public void lancereliminer(View view) {
        Intent intent=new Intent(this, ActivityEcrirRecapBacs.class);
        list_select = adapter_poisson.getSelected();
        System.out.println(list_select);
        Action="Eliminé";

        if(list_select.size()==0){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 ou plusieurs éléments", Toast.LENGTH_SHORT).show();
        } else {
            for(int i=0; i<list_select.size();i++){
                WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, main_user, NouveauBac, Action, list_select.get(i).getBac(), list_select.get(i).getLignee(), list_select.get(i).getLot(), Bac2, Lignee2, Lot2, list_select.get(i).getKey(), Key2);

                //Temps d'attente !!! IMPORTANT
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startActivity(intent);
        }
    }



    private void getItems() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwqCJJWqCBXyVIatCzuiST50A0_kcxPXL-GH9BEYtBfk6y-aEHX/exec?action=getItems", new Response.Listener<String>() {
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
        loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }


    private void parseItems(String jsonResponce) {

        List<Poisson> data = new ArrayList<>();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo=jarray.getJSONObject(i);

                String Bac=jo.getString("Bac");
                String Lot=jo.getString("Lot");
                String Lignee=jo.getString("Lignee");
                String Age=jo.getString("Age");
                String Responsable=jo.getString("Responsable");
                String Key=jo.getString("Key");


                Integer Image;

                if (Integer.parseInt(Age)==22){
                    Image=icon[0];
                } else {
                    Image=icon[1];
                }

              /*  HashMap<String, String> item=new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);
                list.add(item);*/

                data.add(new Poisson(Lot,Bac,Responsable,Lignee,Age,Key,Image));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //adapter=new SimpleAdapter(this, list, R.layout.list_item_registre_checked, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable});
        //listView.setAdapter(adapter);


        adapter_poisson=new PoissonAdapter(this, data);
        listView.setAdapter(adapter_poisson);


        // clics sur les éléments, on les envoie à l'adaptateur
        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter_poisson.toggle(pos));


        loading.dismiss();


    }


}