package com.example.appbertille;

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

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityReunirBacs extends AppCompatActivity implements AdapterView.OnItemClickListener {
    
    
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    String Bac = "";
    String Lot = "";
    String Lignee = "";
    String Age = " ";
    String main_user="";
    String Key="";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_morts_item);
        
        listView = findViewById(R.id.lv_items);
        listView.setOnItemClickListener(this);
        editTextSearchItem = findViewById(R.id.et_search);
        
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
        
        Bac = intent.getStringExtra("Bac");
        Lot = intent.getStringExtra("Lot");
        Lignee = intent.getStringExtra("Lignee");
        Age=intent.getStringExtra("Age");
        Key=intent.getStringExtra("Key");
        getItems();
        
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
                // String PointLimite = jo.getString("PointLimite");
                String Key=jo.getString("Key");

                System.out.println(Age);

                HashMap<String, String> item=new HashMap<>();
    
                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                //item.put("PointLimite", PointLimite);
                item.put("Key", Key);
                list.add(item);
    
    
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    
    
        adapter=new SimpleAdapter(this, list, R.layout.list_item_registre_checked, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable});
    
    
        listView.setAdapter(adapter);
        loading.dismiss();
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityReunirBacs.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            
            }
        });
    }
    
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ActivityReunificationBacs.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        
        String Bac2 = map.get("Bac").toString();
        String Lot2 = map.get("Lot").toString();
        String Lignee2 = map.get("Lignee").toString();
        String Age2=map.get("Age").toString();
        String Key2=map.get("Key").toString();
        
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("main_user", main_user);
        intent.putExtra("Age", Age);
        intent.putExtra("Key", Key);
        
        intent.putExtra("Bac2", Bac2);
        intent.putExtra("Lot2", Lot2);
        intent.putExtra("Lignee2", Lignee2);
        intent.putExtra("Age2", Age2);
        intent.putExtra("Key2", Key2);
    
        startActivity(intent);
    }
    
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancermenu(View view) {
        
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void onPointerCaptureChanged(boolean hasCapture) {
    
    }
}

