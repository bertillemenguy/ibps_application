package com.example.endpointapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityConnexion extends AppCompatActivity {

    ArrayList user_list;
    ProgressDialog loading;
    EditText name_user, new_name_user;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        name_user = (EditText) findViewById(R.id.name_user);
        new_name_user = (EditText) findViewById(R.id.new_name_user);
        user_list = new ArrayList<String>();

        getItems();
    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxuUHvxkMogaEy8mAyVKjiEr3N8ItRn9D5XCkDcW7ZcZ-aJxQgdogtVulwcGjavC4Y/exec?action=getItems", new Response.Listener<String>() {
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

    /**
     * Récupere les inforamtions de ...
     *
     * @param jsonResponce la réponse du serveur X pour ...
     */
    private void parseItems(String jsonResponce) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo=jarray.getJSONObject(i);

                String key=jo.getString("key");
                String name=jo.getString("name");

                user_list.add(name);

                HashMap<String, String> item=new HashMap<>();

                item.put("key", key);
                item.put("name", name);

                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // listView.setAdapter(adapter);
        loading.dismiss();

    }


    public void connexion(View view){

        String chaine = name_user.getText().toString();
        boolean trouve = false;
        int i =0;

        while ((!trouve)&&(i<user_list.size())) {
            if (chaine.equalsIgnoreCase(String.valueOf(user_list.get(i)))) {
                trouve = true;
                Intent intent = new Intent(this, ActivityMenu.class);
                intent.putExtra("main_user", String.valueOf(user_list.get(i)));
                startActivity(intent);
                loading = ProgressDialog.show(this, "Connexion...", " Veuillez patienter", false, true);
            }
            i++;
        }
        if (!trouve) {
            Toast.makeText(getApplicationContext(), "Cet utilisateur n'existe pas", Toast.LENGTH_SHORT).show();
        }
    }

    public void creer(View view){
        String chaine = new_name_user.getText().toString();
        int i =0;
        boolean trouve = false;
        while ((!trouve)&&(i<user_list.size())) {
            if (chaine.equalsIgnoreCase(String.valueOf(user_list.get(i)))) {
                Toast.makeText(getApplicationContext(), "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                trouve = true;
            }
            i++;
        }

        if (!trouve){
            Intent intent=new Intent();
            intent.putExtra("main_user", chaine);
            WriteOnSheetUser.writeData(this, chaine);
            intent = new Intent(this, ActivityMenu.class);
            startActivity(intent);
            loading = ProgressDialog.show(this, "Inscription...", " Veuillez patienter", false, true);
        }

    }



}
