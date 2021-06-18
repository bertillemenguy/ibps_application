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

public class ActivityHistoriqueAnimalerieAlevins extends AppCompatActivity {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;

    String main_user = "";
    Date date = null;


    //int[] couleur_item= {R.color.jaune, R.color.vert, R.color.violet};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
        //setContentView(R.layout.activity_recherche_morts);

        listView = findViewById(R.id.lv_items);
        editTextSearchItem = findViewById(R.id.et_search);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user= intent.getStringExtra("main_user");

        getItems();

    }


    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbyN5ngADLEVHLYTaYBlXZ0Fl1LxsHDzdLsLE1jANGJG4S47rZaC-3q2iEe6DLc7WqYUrw/exec?action=getItems", new Response.Listener<String>() {
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat=new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);

                String today = dateFormat.format(new Date());


                try {
                    date = inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String Date = outputFormat.format(date);

                String Couleur = jo.getString("Couleur");
                String Lot = jo.getString("Lot");
                String Lignee = jo.getString("Lignee");

                HashMap<String, String> item = new HashMap<>();

                item.put("Date", Date);
                item.put("Couleur", Couleur);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);


                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //SimpleAdapter


        adapter=new MyAdapter(this, list, R.layout.list_item_historique_alevins, new String[]{"Date","Lignee", "Lot", "Couleur"}, new int[]{
                R.id.text2, R.id.tv_lignee, R.id.tv_lot});

        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueAnimalerieAlevins.this.adapter.getFilter().filter(charSequence);
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



