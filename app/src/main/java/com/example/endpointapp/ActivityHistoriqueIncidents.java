package com.example.endpointapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.Locale;

public class ActivityHistoriqueIncidents extends AppCompatActivity implements AdapterView.OnItemClickListener{
    
    
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    String main_user = "";
    Date date = null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
        //setContentView(R.layout.activity_recherche_morts);
        
        listView = findViewById(R.id.lv_items);

        listView.setOnItemClickListener(this);

        editTextSearchItem = findViewById(R.id.et_search);
        
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user= intent.getStringExtra("main_user");
        
        getItems();
        
    }
    
    
    private void getItems() {
        
        
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxv_6vHp6__F0NdJ8BWtqWhhC9JgkpVcHpD0tfbT0ETgvQwunmAzXdtPfsVUqTKBYHinw/exec?action=getItems", new Response.Listener<String>() {
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
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            
            
            for (int i = 0; i < jarray.length(); i++) {
                
                JSONObject jo = jarray.getJSONObject(i);
    
                SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat=new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);
                
                try {
                    date = inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String Date = outputFormat.format(date);
                
                String operateur = jo.getString("operateur");
                String eaudeville = jo.getString("eaudeville");
                String electricite = jo.getString("electricite");
                String aircomprime = jo.getString("aircomprime");
                String climatisation = jo.getString("climatisation");
                String eaudusysteme = jo.getString("eaudusysteme");
                String systemeaquatique = jo.getString("systemeaquatique");
                String travaux = jo.getString("travaux");
                String nourrissage = jo.getString("nourrissage");
                String etat = jo.getString("etat");
                String key = jo.getString("key");



                HashMap<String, String> item = new HashMap<>();
                
                item.put("Date", Date);
                
                
                item.put("operateur", operateur);
                item.put("eaudeville", eaudeville);
                item.put("electricite", electricite);
                item.put("aircomprime", aircomprime);
                item.put("climatisation", climatisation);
                item.put("eaudusysteme", eaudusysteme);
                item.put("systemeaquatique", systemeaquatique);
                item.put("travaux", travaux);
                item.put("nourrissage", nourrissage);
                item.put("etat", etat);
                item.put("key", key);

                list.add(item);
    
    
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    
    
        adapter=new SimpleAdapter(this, list, R.layout.list_item_historique_incidents, new String[]{"Date", "operateur", "eaudeville", "electricite", "aircomprime", "climatisation", "eaudusysteme", "systemeaquatique", "travaux", "nourrissage", "etat"}, new int[]{
                R.id.tv_date, R.id.tv_operateur, R.id.tv_eaudeville, R.id.tv_electricite, R.id.tv_aircomprime, R.id.tv_climatisation, R.id.tv_eaudusysteme, R.id.tv_systemeAquatique, R.id.tv_travaux, R.id.tv_nourrissage, R.id.tv_etat});
    
        listView.setAdapter(adapter);
        loading.dismiss();
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueIncidents.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                
                
            }
        });
    }
    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
    
    
    public void fermeractivite(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CustomPopupIncident.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        String key = map.get("key").toString();
        intent.putExtra("key", key);
        intent.putExtra("main_user", main_user);
        //Toast.makeText(ActivityHistoriqueIncidents.this, key, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
    
    

