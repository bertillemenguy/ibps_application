package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ActivityRechercheGestionBacs extends AppCompatActivity {
    
    
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    String main_user="";
    Date date=null;
    String Elimine = "";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_gestion_bacs_traite);
    
        listView=findViewById(R.id.lv_items);
    
        editTextSearchItem=findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent=getIntent();
        main_user=intent.getStringExtra("main_user");
    
        getItems();
    
    }
    
    
    private void getItems() {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbws-fhc9nsXmYFgqVL2K5UStbLoV43q9M1O_OCZ/exec?action=getItems", new Response.Listener<String>() {
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
                String Date=outputFormat.format(date);
                String Actions=jo.getString("Actions");
                String NouveauBac=jo.getString("NouveauBac");
                String Lignee=jo.getString("Lignee");
                String Bac=jo.getString("Bac");
                String Lignee2=jo.getString("Lignee2");
                String Bac2=jo.getString("Bac2");
    
                String remarque=jo.getString("remarque");
                String remarque2=jo.getString("remarque2");
                String Bac2b=jo.getString("Bac2b");
    
                String remarque3=jo.getString("remarque3");
                String Bac3=jo.getString("Bac3");
    
                String remarque4=jo.getString("remarque4");
                String Bac4=jo.getString("Bac4");
    
                String Lot=jo.getString("Lot");
                String Lot2=jo.getString("Lot2");
    
                HashMap<String, String> item=new HashMap<>();
    
                item.put("Date", Date);
                item.put("Actions", Actions);
                item.put("NouveauBac", NouveauBac);
                item.put("Lignee", Lignee);
                item.put("Bac", Bac);
                item.put("Lignee2", Lignee2);
                item.put("Bac2", Bac2);
    
                item.put("remarque", remarque);
                item.put("remarque2", remarque2);
                item.put("Bac2b", Bac2b);
    
    
                item.put("remarque3", remarque3);
                item.put("Bac3", Bac3);
                item.put("remarque4", remarque4);
                item.put("Bac4", Bac4);
    
                item.put("Lot", Lot);
                item.put("Lot2", Lot2);
    
                list.add(item);
    
    
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    
    
        adapter=new SimpleAdapter(this, list, R.layout.list_item_historique_bacs, new String[]{"Date", "Actions", "NouveauBac", "Lignee", "Bac", "Lignee2", "Bac2", "remarque", "remarque2", "Bac2b", "remarque3", "Bac3", "remarque4", "Bac4", "Lot", "Lot2"}, new int[]{R.id.tv_date, R.id.tv_actions, R.id.tv_nouveaubacEtDupliBac1, R.id.tv_lignee, R.id.tv_bac, R.id.tv_ReuniLignee2, R.id.tv_RuniBac2, R.id.tv_DupliRemarque1, R.id.tv_DupliRemarque2, R.id.tv_DupliBac2, R.id.tv_DupliRemarque3, R.id.tv_DupliBac3, R.id.tv_DupliRemarque4, R.id.tv_DupliBac4, R.id.tv_lot, R.id.tv_ReuniLot2});
        
        
        listView.setAdapter(adapter);
        loading.dismiss();
        
        
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheGestionBacs.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            
            }
        });
    }
    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        // intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    
    public void lancertraiter(View view) {
        //Intent intent = new Intent(this,ActivityMenu.class);
        //intent.putExtra("operateur", operateur);
        main_user="operateur";
        Elimine="<<<<<TRAITE>>>>>";
        
        WriteOnSheetTRAITE.writeData(this, main_user, Elimine);
        // startActivity(intent);
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
}

