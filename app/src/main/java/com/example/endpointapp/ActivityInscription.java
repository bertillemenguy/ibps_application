package com.example.endpointapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
import java.util.HashMap;

public class ActivityInscription extends AppCompatActivity {

    ArrayList pseudo_list, nom_list, prenom_list, mail_list;
    ProgressDialog loading;
    EditText new_pseudo, new_firstname, new_lastname, new_mail, pass, confirm_pass;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        new_pseudo = (EditText) findViewById(R.id.pseudo);
        new_firstname = (EditText) findViewById(R.id.firstname);
        new_lastname = (EditText) findViewById(R.id.lastname);
        new_mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass);
        pseudo_list = new ArrayList<String>();
        nom_list = new ArrayList<String>();
        prenom_list = new ArrayList<String>();
        mail_list = new ArrayList<String>();


        getItems();
    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbyTOk6zDTeck9RbyxGmDVIq4hKBWjYIVkWpPRnx1gg6-dbDMdWt_vekZTlUEuWUa7ai/exec?action=getItems", new Response.Listener<String>() {
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

                pseudo_list.add(pseudo);
                nom_list.add(firstname);
                prenom_list.add(lastname);
                mail_list.add(mail);

                HashMap<String, String> item=new HashMap<>();

                item.put("key", key);
                item.put("pseudo", pseudo);
                item.put("firstname", firstname);
                item.put("lastname", lastname);
                item.put("mail", mail);

                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // listView.setAdapter(adapter);
        loading.dismiss();

    }



    public void creer(View view){
        String chaine1 = new_pseudo.getText().toString();
        String chaine2 = new_firstname.getText().toString();
        String chaine3 = new_lastname.getText().toString();
        String chaine4 = new_mail.getText().toString();

        String pass1 = pass.getText().toString();
        String pass2 = confirm_pass.getText().toString();

        int i=0;
        boolean trouve = false;

        if (pass1.equals("") || pass2.equals("") || chaine1.equals("") || chaine2.equals("") || chaine3.equals("") || chaine4.equals("")){
            Toast.makeText(getApplicationContext(), "Champ manquant", Toast.LENGTH_SHORT).show();
        } else {

            while ((!trouve)&&(i<pseudo_list.size())) {
                if (chaine1.equalsIgnoreCase(String.valueOf(pseudo_list.get(i)))) {
                    Toast.makeText(getApplicationContext(), "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if ((chaine2.equalsIgnoreCase(String.valueOf(prenom_list.get(i))))&&(chaine3.equalsIgnoreCase(String.valueOf(nom_list.get(i))))) {
                    Toast.makeText(getApplicationContext(), "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if (chaine4.equalsIgnoreCase(String.valueOf(mail_list.get(i)))) {
                    Toast.makeText(getApplicationContext(), "Adresse mail non valide", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if (!pass1.equals(pass2)){
                    Toast.makeText(getApplicationContext(), "Mots de passe non identiques", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if (chaine4.length()>9){
                    if (!chaine4.substring(chaine4.length()-9, chaine4.length()-1).equals("@upmc.fr")){
                        Toast.makeText(getApplicationContext(), "Adresse mail non valide", Toast.LENGTH_SHORT).show();
                        trouve = true;
                }
                }

                if (chaine4.length()<=9){
                    Toast.makeText(getApplicationContext(), "Adresse mail non valide", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                i++;
            }
            if (!trouve){

                WriteOnSheetUser.writeData(this, chaine1, chaine2, chaine3, chaine4, pass1);
                Intent intent = new Intent(this, ActivityMenu.class);
                intent.putExtra("main_user", chaine1);
                startActivity(intent);
                loading = ProgressDialog.show(this, "Inscription...", " Veuillez patienter", false, true);
            }



        }

    }

    public void lancer_connexion(View view){
        Intent intent=new Intent(this, ActivityConnexion.class);
        startActivity(intent);
    }

}
