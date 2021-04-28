
package com.example.endpointapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Button;
import android.graphics.pdf.PdfDocument;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.pm.PackageManager;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ActivityRechercheRegistreMorts extends AppCompatActivity implements AdapterView.OnItemClickListener{
    
    String main_user;

    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    Button button;

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_morts_item);
        
        listView = findViewById(R.id.lv_items);
        listView.setOnItemClickListener(this);
        button=findViewById(R.id.btn_valider);
        editTextSearchItem = findViewById(R.id.et_search);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
        
        getItems();


    }




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
                String Responsable=jo.getString("Responsable");
                String Key=jo.getString("Key");
                
                
                HashMap<String, String> item = new HashMap<>();
                
                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);


                if (Integer.parseInt(Age)==21){
                    item.put("image", icon[0]+"");
                } else {
                    item.put("image", icon[1]+"");
                }
                
                list.add(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter=new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "image"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort});

        listView.setAdapter(adapter);
        loading.dismiss();

        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheRegistreMorts.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    /*-----------------------------SELECTIONNER POISSON(S)-------------------------------*/
    /*-----------------------------------------------------------------------------------*/


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Intent intent = new Intent(this, ActivityItemdetails.class);
        Intent intent = new Intent(this, ActivityEcrirRecapMort.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        
        String Bac = map.get("Bac").toString();
        String Lot = map.get("Lot").toString();
        String Lignee = map.get("Lignee").toString();
        String Age = map.get("Age").toString();
        String Responsable = map.get("Responsable").toString();
        String Key = map.get("Key").toString();
        
        
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Key", Key);

        intent.putExtra("main_user", main_user);


        startActivity(intent);
    }
    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void lancertrie(View view) {
        Intent intent = new Intent(this, ActivityRechercheRegistreMortsTrier.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    public void lancerPDF(View view){

        Toast.makeText(getApplicationContext(), "Chargement ... ", Toast.LENGTH_SHORT).show();

        SimpleDateFormat formater = null;

        Date aujourdhui = new Date();

        formater = new SimpleDateFormat("dd-MM-yy_hh-mm-ss");
        String date = formater.format(aujourdhui);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Canvas canvas = myPage.getCanvas();

        canvas.drawText("Coucou", 40, 50, myPaint);
        myPdfDocument.finishPage(myPage);

        File file = new File(Environment.getExternalStorageDirectory(), "/Registre_morts"+date+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e){
            e.printStackTrace();
        }

        myPdfDocument.close();
        Toast.makeText(getApplicationContext(), "Pdf enregistré dans Téléchargements", Toast.LENGTH_SHORT).show();

    }


    public void onPointerCaptureChanged(boolean hasCapture) {
    
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id= item.getItemId();
        if (id==R.id.btn_valider){
            String itemSelected = "Selected items: ";
            for(int i=0; i<listView.getCount();i++){
                if(listView.isItemChecked(i)){
                    itemSelected +=  listView.getItemAtPosition(i);
                }
            }
            Toast.makeText(this,itemSelected,Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
