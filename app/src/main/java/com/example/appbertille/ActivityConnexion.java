package com.example.appbertille;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import static com.example.appbertille.ActivityInscription.createSecretKey;

public class ActivityConnexion extends AppCompatActivity {

    ArrayList pseudo_list, pass_list, lastname_list, firstname_list;
    ProgressDialog loading;
    EditText pseudo, pass;
    String main_user;
    byte[] salt;
    SecretKeySpec Key;
    String Key2;

    int iterationCount;
    int keyLength;


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        pseudo = (EditText) findViewById(R.id.pseudo);
        pass = (EditText) findViewById(R.id.mdp);

        pseudo_list = new ArrayList<String>();
        pass_list = new ArrayList<String>();
        lastname_list = new ArrayList<String>();
        firstname_list = new ArrayList<String>();

        salt = new String("12345678").getBytes();
        iterationCount = 40000;
        keyLength = 128;


        getItems();
    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbztwvR_TWE3B7N_LsPH8UsghrqOymACP1rJed6BYWEdSzz2kKTC4UW5cb9akYs5BKNm/exec?action=getItems", new Response.Listener<String>() {
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

                pass_list.add(pass);

                lastname_list.add(lastname);
                firstname_list.add(firstname);

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

        try {
            this.Key=createSecretKey(chaine2.toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("Key "+Key);

        boolean trouve = false;
        int i =0;


        if (chaine1.equals("") || chaine2.equals("")) {
            Toast.makeText(getApplicationContext(), "Champ manquant", Toast.LENGTH_SHORT).show();
        } else {
            while ((!trouve)&&(i<pseudo_list.size())) {

                this.Key2=String.valueOf(pass_list.get(i));

                System.out.println("Key2 "+Key2);


                if (chaine1.equalsIgnoreCase(String.valueOf(pseudo_list.get(i))) &&  String.valueOf(Key).equals(Key2)) {
                    main_user=firstname_list.get(i)+" "+lastname_list.get(i);
                    trouve = true;
                    Intent intent = new Intent(this, ActivityMenu.class);
                    intent.putExtra("main_user", main_user);
                    startActivity(intent);
                    loading = ProgressDialog.show(this, "Connexion...", " Veuillez patienter", false, true);
                }
                i++;
            }
            if (!trouve) {
                Toast.makeText(getApplicationContext(), "Erreur, mot de passe ou pseudo incorrect", Toast.LENGTH_SHORT).show();
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

    /*public String decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
    }*/


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }

    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

}
