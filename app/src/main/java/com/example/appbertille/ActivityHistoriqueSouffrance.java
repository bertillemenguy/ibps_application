package com.example.appbertille;

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

public class ActivityHistoriqueSouffrance extends AppCompatActivity {
    
    String main_user;

    ListView listView;
    SouffranceAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
//    String operateur = "";
    Date date = null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
        //setContentView(R.layout.activity_recherche_morts);
    
        listView=findViewById(R.id.lv_items);
    
        editTextSearchItem=findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
    
        getItems();
    
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
        loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);
        
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        
        stringRequest.setRetryPolicy(policy);
        
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        
    }
    
    
    private void parseItems(String jsonResponce) {
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ArrayList<String> list_Id = new ArrayList<>();
        ArrayList<Souffrance> list_souffrance = new ArrayList<>();


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

                list_souffrance.add(new Souffrance(Date,Age, Bac, Lignee, Lot,Responsable,Euthanasie,Isolement,Surveillance,Aucune_action_a_mener,PoissonSouffrance, Id));

                list_Id.add(Id);

                    HashMap<String, String> item = new HashMap<>();

                    item.put("Date", Date);

                    item.put("Age", Age);
                    item.put("Bac", Bac);
                    item.put("Lignee", Lignee);
                    item.put("Lot", Lot);
                    item.put("Responsable", Responsable);

                    item.put("Euthanasie", Euthanasie);
                    item.put("Isolement", Isolement);
                    item.put("Surveillance", Surveillance);
                    item.put("Aucune_action_a_mener", Aucune_action_a_mener);
                    item.put("PoissonSouffrance", PoissonSouffrance);
                    item.put("Id", Id);

                    list.add(item);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i=0; i<list_Id.size();i++){

            int euthanasie=0;
            int isolement=0;
            int surveillance=0;
            int ras=0;

            int nb_fois=0;

            ArrayList<Integer> pos= new ArrayList<>();

            for (int j=0;j<list.size();j++){
                if (list_Id.get(i).equals(list.get(j).get("Id"))){
                    nb_fois=nb_fois+1;
                    if(nb_fois>1){
                        pos.add(j);
                    }
                }
            }

            System.out.println(nb_fois);

            if (nb_fois>1){
                if(pos.size()>1){
                    for (int k=0;k<pos.size();k++){
                        System.out.println("Coucou");
                        int p= pos.get(k);
                        System.out.println(p);

                        list.remove(p);
                    }
                }

            }

        }




        adapter=new SouffranceAdapter(this, list_souffrance, main_user);
    
        listView.setAdapter(adapter);
        loading.dismiss();
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueSouffrance.this.adapter.getFilter().filter(charSequence);
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
}

