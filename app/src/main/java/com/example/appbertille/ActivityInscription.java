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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class ActivityInscription extends AppCompatActivity {

    ArrayList pseudo_list, nom_list, prenom_list, mail_list;
    ProgressDialog loading;
    EditText new_pseudo, new_firstname, new_lastname, new_mail, pass, confirm_pass;
    byte[] salt;
    int iterationCount;
    int keyLength;
    SecretKeySpec key;

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

        salt = new String("12345678").getBytes();
        iterationCount = 40000;
        keyLength = 128;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creer(View view) throws GeneralSecurityException, UnsupportedEncodingException {
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
                    Toast.makeText(getApplicationContext(), "Adresse mail déjà utilisée", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if (!pass1.equals(pass2)){
                    Toast.makeText(getApplicationContext(), "Mots de passe non identiques", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                if (chaine4.length()>7){
                    if (!chaine4.substring(chaine4.length()-8, chaine4.length()-0).equals("@upmc.fr")){
                        System.out.println(chaine4.substring(chaine4.length()-8, chaine4.length()-0));
                        Toast.makeText(getApplicationContext(), "Adresse mail non valide", Toast.LENGTH_SHORT).show();
                        trouve = true;
                }
                }

                if (chaine4.length()<=7){
                    Toast.makeText(getApplicationContext(), "Adresse mail non conforme", Toast.LENGTH_SHORT).show();
                    trouve = true;
                }

                i++;
            }
            if (!trouve){
                key = null;
                try {
                    key = createSecretKey(pass1.toCharArray(), salt, iterationCount, keyLength);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
                //String password = encrypt(pass1, key);
                String first = chaine2.substring(0, 1);
                String rest = chaine2.substring(1, chaine2.length());
                first=first.toUpperCase();
                String res = first+rest;
                WriteOnSheetUser.writeData(this, chaine1, res, chaine3.toUpperCase(), chaine4, String.valueOf(key));
                Intent intent = new Intent(this, ActivityMenu.class);
                intent.putExtra("main_user", res + " "+chaine3.toUpperCase());
                startActivity(intent);
                loading = ProgressDialog.show(this, "Inscription...", " Veuillez patienter", false, true);
            }
        }
    }

    public void lancer_connexion(View view){
        Intent intent=new Intent(this, ActivityConnexion.class);
        startActivity(intent);
    }

   /* public String encrypt(String password){
        //SecretKeySpec key = createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);


        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            crypte=crypte+(char)c;
        }
        return crypte;
    }*/



    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }


}
