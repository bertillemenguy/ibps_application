package com.example.endpointapp;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class ActivityWebHistoriqueAnimalerieAlevins extends AppCompatActivity {
    
    //String html="<iframe width=\"1000\" height=\"4000\" style=\"border: 1px solid #cccccc;\"  <iframe src=\"https://docs.google.com/spreadsheets/d/e/2PACX-1vTt-Llt4wpV-xc0Ece45IUBt1vIollaQt3nmn7lr2VconYfBQibKqTeYEWN7siSFWhUaWApX59pvPr6/pubhtml?gid=1354680660&amp;single=true&amp;widget=true&amp;headers=false\" style=\"border: 0\" width=\"400\" height=\"300\" frameborder=\"0\" scrolling=\"no\"></iframe>";
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;


    String main_user;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_morts_item);

        listView = findViewById(R.id.lv_items);
        editTextSearchItem = findViewById(R.id.et_search);

        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");

        //WebView webview;
        //webview=findViewById(R.id.webview);
        //webview.getSettings().setJavaScriptEnabled(true);
        //webview.loadData(html, "text/html", null);

        getItems();

    }

    private void getItems() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxJ-vkiputqn_ktYULev3RA09VPkjRqkf2-L3DNug61mKmKgYdXVkPEaL4hi9sBPC-Pqw/exec?action=getItems", new Response.Listener<String>() {
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
     *
     *
     *@param jsonResponce la réponse du serveur X pour ...
     */

    private void parseItems(String jsonResponce) {


        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            Toast.makeText(ActivityWebHistoriqueAnimalerieAlevins.this, "Coucou", Toast.LENGTH_SHORT).show();

            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo=jarray.getJSONObject(i);

                //String Jour=jo.getString("Jour");
                //String Date=jo.getString("Date");
                //String Couleur=jo.getString("Couleur");
                String Lot=jo.getString("Lot");
                String Lignee=jo.getString("Lignee");

                HashMap<String, String> item=new HashMap<>();

                //item.put("Jour", Jour);
                //item.put("Date", Date);
                //item.put("Couleur", Couleur);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);

                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter=new SimpleAdapter(this, list, R.layout.list_item_registre_checked, new String[]{ "Lot", "Lignee"}, new int[]{R.id.tv_lot, R.id.tv_lignee});

        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityWebHistoriqueAnimalerieAlevins.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
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
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Canvas canvas = myPage.getCanvas();

        canvas.drawText("Coucou", 40, 50, myPaint);
        myPdfDocument.finishPage(myPage);

        File file = new File(Environment.getExternalStorageDirectory(), "/AnimalerieAlvins"+date+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e){
            e.printStackTrace();
        }

        myPdfDocument.close();
        Toast.makeText(getApplicationContext(), "Pdf enregistré dans Téléchargements", Toast.LENGTH_SHORT).show();

    }


    public void lancermenu(View view) {

        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //public void fermeractivite(View view) {
      //  this.finish();
    //}
}



