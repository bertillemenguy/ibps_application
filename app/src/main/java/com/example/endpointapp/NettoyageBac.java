package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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
import java.util.List;
import java.util.Locale;

public class NettoyageBac extends AppCompatActivity {
    String main_user = "";
    ProgressDialog loading;
    EditText editTextSearchItem;

    List<BacRegistre> list_bac;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nettoyage_bac);


        editTextSearchItem = findViewById(R.id.et_search);

        getItems();

        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");

    }


    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwzu2u1Uvvv9DbE8Jr2VbwVJlr66utWNEcnNDYepdnm5ZliSNNPAPmktR-HhYtpR8ri4w/exec?action=getItems", new Response.Listener<String>() {
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

        list_bac = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String Bac = jo.getString("Bac");
                String key = jo.getString("key");
                String DateLavage = jo.getString("DateLavage");
                String DateNaissance = jo.getString("DateNaissance");

                String Operateur = jo.getString("Operateur");


                list_bac.add(new BacRegistre(Bac, Operateur, DateNaissance, key, DateLavage));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list_bac);

        /*__________________nombre de cases sur 1 ligne____________________*/
        recyclerView.setLayoutManager(new GridLayoutManager(this,7));
        recyclerView.setAdapter(adapter);

        loading.dismiss();

    }


    public void lancer_laver(View view){

        List<BacRegistre> list_key = new ArrayList<>();

            for (int i=0; i<list_bac.size();i++){
                if (list_bac.get(i).isSelected()){
                    list_key.add(list_bac.get(i));
                    System.out.println(list_bac.get(i).getNum());
                }
            }


            for (int j=0;j<list_key.size();j++){
                WriteOnSheetBacLaver.updateData(this, list_key.get(j).getKey(), main_user);
                //Temps d'attente !!! IMPORTANT
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        Intent intent = new Intent(this, NettoyageBac.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);

    }

        public void lancermenu(View view) {

        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }




}
