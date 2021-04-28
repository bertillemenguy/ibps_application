package com.example.endpointapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ActivityRechercheRegistre extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    String main_user;
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_item);



        //Get List View objet from xml
        listView=findViewById(R.id.lv_items);
        /** listView.setOnItemClickListener(this);
         * */
        /**
         * ajout de la barre recherche
         */

        editTextSearchItem=findViewById(R.id.et_search);



        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("user");


        getItems();



    }


    
    /**
     * charger le registre au niveau du menu. sans issu apres
     */
    
    private void getItems() {
        
        
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwqCJJWqCBXyVIatCzuiST50A0_kcxPXL-GH9BEYtBfk6y-aEHX/exec?action=getItems", new Response.Listener<String>() {
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
     * formater les donnees dans un nouveau layout (list_item_row)
     *
     * @param jsonResponce
     */





        private void parseItems(String jsonResponce) {
        

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String Bac = jo.getString("Bac");
                String Lot = jo.getString("Lot");
                String Lignee = jo.getString("Lignee");
                String Age = jo.getString("Age");
                String Responsable = jo.getString("Responsable");
                String Key = jo.getString("Key");


                HashMap<String, String> item = new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);

                /*---------------------METTRE IMAGE POISSON MORT---------------------*/
                /*-------------------------------------------------------------------*/

                if (Integer.parseInt(Age)==21){
                    item.put("image", icon[0]+"");
                } else {
                    item.put("image", icon[1]+"");
                }

                list.add(item);
            }
        }

     catch(JSONException e){
                e.printStackTrace();

            }

            /* faire apparaître logo quand utilisateur = 'JH'*/




            adapter=new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "image"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort});

            //listView.getId(3).setImage(R.drawable.fish_bones);


            //ImageView icon=(ImageView) findViewById(R.id.icon_mort);

            //icon.setImageDrawable(getResources().getDrawable(R.drawable.fish_bones));

          //assign adapter to list view

            listView.setAdapter(adapter);
            loading.dismiss();



            //Toast
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheRegistre.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            
            }
        });
    }
    
    /**
     * public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     * Intent intent = new Intent(this, ActivityItemdetails3.class);
     * HashMap map =(HashMap)parent.getItemAtPosition(position);
     * <p>
     * String Bac = map.get("Bac").toString();
     * String Lot = map.get("Lot").toString();
     * String Lignee = map.get("Lignee").toString();
     * String Age = map.get("Age").toString();
     * String Responsable = map.get("Responsable").toString();
     * String PointLimite = map.get("PointLimite").toString();
     * <p>
     * <p>
     * intent.putExtra("Bac",Bac);
     * intent.putExtra("Lot",Lot);
     * intent.putExtra("Lignee",Lignee);
     * intent.putExtra("operateur", operateur);
     * intent.putExtra("Age",Age);
     * intent.putExtra("Responsable",Responsable);
     * intent.putExtra("PointLimite", PointLimite);
     * <p>
     * startActivity(intent);
     * }
     * <p>
     * <p>
     * <p>
     * <p>
     * public void fermeractivite(View view) {
     * this.finish();
     * }
     * <p>
     * <p>
     * public void lancerhistoriqueMorts(View view)
     * { //String operateur = OperateurSpinner.getSelectedItem().toString();
     * Intent intent = new Intent(this,ActivityRechercheMorts.class);
     * //intent.putExtra("operateur", operateur);
     * startActivity(intent);
     * }
     */


    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        //  intent.putExtra("operateur", operateur);
        startActivity(intent);
    }


    public void lancertri(View view) {

        Intent intent = new Intent(this, ActivityRechercheRegistreTrier.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    // public void onPointerCaptureChanged(boolean hasCapture) {
    
    // }




}
