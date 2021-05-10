package com.example.endpointapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.Button;


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
import android.widget.AdapterView;

import static android.app.AlertDialog.*;


public class ActivityHistoriqueMorts extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String main_user;

    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    ActivityHistoriqueMorts activity;
    //Builder myPopup;

    Date date = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
        //setContentView(R.layout.activity_recherche_morts);

        listView = findViewById(R.id.lv_items);
        editTextSearchItem = findViewById(R.id.et_search);
        listView.setOnItemClickListener(this);
        getItems();

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");

        activity = this;



    }




    private void getItems() {

        // réponse JSON sous forme de String
        //récupère les données de google sheet par le Script Code.gs
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzSXjNNtsM3NJelWZ-xMcRKsxtJ_IUZ4iS7bRukE_gTw0W-wk_YsOz0i3DetWLRq-BIfQ/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // response en String va dans parseItems pour mettre les données en listView
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

        //temps de connexion
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
                String SiAccouplement = jo.getString("SiAccouplement");
                String SumMorts = jo.getString("SumMorts");
                String Key = jo.getString("Key");

                HashMap<String, String> item = new HashMap<>();

                item.put("Date", Date);
                item.put("Age", Age);
                item.put("Bac", Bac);
                item.put("Lignee", Lignee);
                item.put("Lot", Lot);
                item.put("Responsable", Responsable);
                item.put("SiAccouplement", SiAccouplement);
                item.put("SumMorts", SumMorts);
                item.put("Key", Key);

                list.add(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this, list, R.layout.list_item_historique_morts, new String[]{"Date", "Age", "Bac", "Lignee", "Lot", "Responsable", "SiAccouplement", "SumMorts"}, new int[]{R.id.tv_date, R.id.tv_age, R.id.tv_bac, R.id.tv_lignee, R.id.tv_lot, R.id.tv_responsable, R.id.tv_SiAccouplement, R.id.tv_summorts});

        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueMorts.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }

    /*----------------------- BUTTON DELETE MORT------------------------*/
    /*------------------------------------------------------------------*/

    //lancer un popup de validation
   /* public void lancer_popup(View view){
        myPopup = new Builder(activity,R.style. AlertDialogTheme);
        myPopup.setTitle("Supprimer");
        myPopup.setMessage("Etes-vous sûr de vouloir supprimer cet élément ?");
                myPopup.setPositiveButton("Oui", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwrIkiWyrx91-SDn0uVHPOg_vcceXWd1W9b44HWQmW2LNfjFqeqmg5hwIjYp3PK7Jne/exec?action=delItem&Key="+ Key_sup, new Response.Listener<String>() {
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

                            // public void onItemClick(AdapterView a, View v, int position, long id) {

                        //suppression dans la base de données google sheet

                        Toast.makeText(getApplicationContext(), "Suppression", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.setNegativeButton("Annuler", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Annuler", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();

    }*/




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

        Intent intent = new Intent(this, CustomPopup.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        String Key = map.get("Key").toString();
        intent.putExtra("main_user", main_user);
        intent.putExtra("Key", Key);
        //Toast.makeText(ActivityHistoriqueMorts.this, Key, Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }


//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


       // Toast.makeText(getApplicationContext(), "Coucou", Toast.LENGTH_SHORT).show();


        // Intent intent = new Intent();


        //HashMap map = (HashMap) parent.getItemAtPosition(position);

       // final String Key = map.get("Key").toString();
        //intent.putExtra("Key", Key);

       /* myPopup = new Builder(activity,R.style. AlertDialogTheme);
        myPopup.setTitle("Supprimer");
        myPopup.setMessage("Etes-vous sûr de vouloir supprimer cet élément ?");
        myPopup.setPositiveButton("Oui", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwrIkiWyrx91-SDn0uVHPOg_vcceXWd1W9b44HWQmW2LNfjFqeqmg5hwIjYp3PK7Jne/exec?action=delItem&Key="+ Key, new Response.Listener<String>() {
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

                // public void onItemClick(AdapterView a, View v, int position, long id) {

                //suppression dans la base de données google sheet

                Toast.makeText(getApplicationContext(), "Suppression", Toast.LENGTH_SHORT).show();
            }
        });
        myPopup.setNegativeButton("Annuler", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Annuler", Toast.LENGTH_SHORT).show();
            }
        });
        myPopup.show();*/

    //}
}
