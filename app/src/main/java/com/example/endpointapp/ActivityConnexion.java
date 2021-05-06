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

    ArrayList pseudo_list, pass_list;
    ProgressDialog loading;
    EditText pseudo, pass;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        pseudo = (EditText) findViewById(R.id.pseudo);
        pass = (EditText) findViewById(R.id.mdp);

        pseudo_list = new ArrayList<String>();
        pass_list = new ArrayList<String>();

        getItems();
    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxtcc-rjHk7zLyWba61dp7QHqv8i6SeOTan7NT-YUtLiNvkeqxJ-kvPPF3hsOi3mS0O/exec?action=getItems", new Response.Listener<String>() {
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
                String pseudo=jo.getString("pseudo");
                String firstname=jo.getString("firstname");
                String lastname=jo.getString("lastname");
                String mail=jo.getString("mail");
                String pass=jo.getString("pass");


                pseudo_list.add(pseudo);

                HashMap<String, String> item=new HashMap<>();

                item.put("key", key);
                item.put("pseudo", pseudo);
                item.put("firstname", firstname);
                item.put("lastname", lastname);
                item.put("mail", mail);
                item.put("pass", pass);

                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // listView.setAdapter(adapter);
        loading.dismiss();
    }


    public void connexion(View view){
        String chaine1 = pseudo.getText().toString();
        String chaine2 = pass.getText().toString();

        boolean trouve = false;
        int i =0;


        if (chaine1.equals("") || chaine2.equals("")) {
            Toast.makeText(getApplicationContext(), "Champ manquant", Toast.LENGTH_SHORT).show();
        } else {
            while ((!trouve)&&(i<pseudo_list.size())) {
                if (chaine1.equals(String.valueOf(pseudo_list.get(i))) &&  chaine2.equals(String.valueOf(pass_list.get(i)))) {
                    trouve = true;
                    Intent intent = new Intent(this, ActivityMenu.class);
                    intent.putExtra("main_user", String.valueOf(pseudo_list.get(i)));
                    startActivity(intent);
                    loading = ProgressDialog.show(this, "Connexion...", " Veuillez patienter", false, true);
                }
                i++;
            }
            if (!trouve) {
                Toast.makeText(getApplicationContext(), "Cet utilisateur n'existe pas", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void lancer_inscription(View view){
        Intent intent=new Intent(this, ActivityInscription.class);
        startActivity(intent);
    }

    public void lancermenu(View view) {

        Intent intent = new Intent(this, ActivityMenu.class);
        startActivity(intent);
    }


}
