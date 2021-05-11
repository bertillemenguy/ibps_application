package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class ActivityTache extends AppCompatActivity {

    String main_user = "";
    ListView listView;
    ProgressDialog loading;
    SimpleAdapter adapter;

    EditText editTextSearchItem;
    Date date=null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_item);

        listView=findViewById(R.id.lv_items);
        editTextSearchItem=findViewById(R.id.et_search);


        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");

        Toast.makeText(this,main_user, Toast.LENGTH_LONG).show();
        getItems();
    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbyIfpDZXf4N0zg5BiEpgpUU-iMtNJE-y38HUdrOmlK014hFw_gZ5vkvIQL0Xu5nRBqC/exec?action=getItems", new Response.Listener<String>() {
            @RequiresApi(api= Build.VERSION_CODES.N)
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


    @RequiresApi(api=Build.VERSION_CODES.N)
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
                    date=inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String Date=outputFormat.format(date);
                Calendar c=Calendar.getInstance();
                c.setTime(outputFormat.parse(Date));
                c.add(Calendar.DATE, 1);  // number of days to add
                Date=outputFormat.format(c.getTime());  // dt is now the new date

                /**  Date=new Date();
                 Calendar c = Calendar.getInstance();
                 c.setTime(currentDate);
                 */

                String Tache = jo.getString("Tache");


                HashMap<String, String> item = new HashMap<>();

                item.put("Date", Date);
                item.put("Tache", Tache);

                list.add(item);


            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }


        adapter=new SimpleAdapter(this, list, R.layout.list_item_taches, new String[]{"Date", "Tache"}, new int[]{R.id.tv_date, R.id.tv_tache});


        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityTache.this.adapter.getFilter().filter(charSequence);
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

}
