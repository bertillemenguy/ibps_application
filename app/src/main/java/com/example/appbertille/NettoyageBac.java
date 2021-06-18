package com.example.appbertille;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NettoyageBac extends AppCompatActivity implements View.OnClickListener{
    String main_user = "";
    ProgressDialog loading;
    EditText editTextSearchItem;
    Button button;
    List<BacRegistre> list_bac;
    PopupRecapBacs popup;

    private NettoyageBac activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nettoyage_bac);

        this.activity=this;
        editTextSearchItem = findViewById(R.id.et_search);
        button =findViewById(R.id.voir_recap);
        button.setOnClickListener(this);

        getItems();

        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");
    }



    //lancer récapiculatif
    @Override
    public void onClick(View view) {

        System.out.println("Coucou ici le récapitulatif");
        getHistorique();

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


                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateToStr = dateFormat.format(date);

                WriteOnSheetBacLaver.updateData(this, list_key.get(j).getKey(), main_user);
                //Temps d'attente !!! IMPORTANT
                WriteOnSheetBacLaver.addItem(this, dateToStr, main_user, list_key.get(j).getKey(), list_key.get(j).getNum());


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



    private void getHistorique() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxIMBg1cdYUlfbnJ3tHx9y5JnpLGNq3XEybV70yZnL9T8YLc72bgx-pxcWflldiFEHLEA/exec?action=getItemsHistorique", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItemsHistorique(response);
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


    private void parseItemsHistorique(String jsonResponce) {

        List<String> list_nom= new ArrayList<>();
        String [] array;

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String Operateur = jo.getString("operateur");

                //list_bac.add(new BacRegistre(Bac, Operateur, DateNaissance, key, DateLavage));

                list_nom.add(Operateur);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        array=new String[list_nom.size()];

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        for (int i=0; i<list_nom.size(); i++){
            array[i]=list_nom.get(i);
        }

        List asList = Arrays.asList(array);
        Set<String> mySet = new HashSet<String>(asList);
        for(String s: mySet){

            System.out.println(s + " " + Collections.frequency(asList,s));

            HashMap<String, String> item = new HashMap<>();

            item.put("Operateur", s);
            item.put("Lavage", String.valueOf(Collections.frequency(asList,s)));
            list.add(item);
        }


        popup = new PopupRecapBacs(activity, list);
        popup.build();



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list_bac);

        /*__________________nombre de cases sur 1 ligne____________________*/
        recyclerView.setLayoutManager(new GridLayoutManager(this,7));
        recyclerView.setAdapter(adapter);

        loading.dismiss();

    }

    public void close_popup(View view) {
        popup.dismiss();
    }


}
