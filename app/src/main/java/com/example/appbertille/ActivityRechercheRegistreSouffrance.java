package com.example.appbertille;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Date;
import java.util.List;

public class ActivityRechercheRegistreSouffrance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<HashMap<String, String>> list_pdf = new ArrayList<>();
    ListView listView;
    PoissonAdapter2 adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    String main_user = "";


    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};


    String[] SpecialChars = { "<", "(" ,"[" ,"{" ,"\\" , "^" , "-","=","$","!" ,"|" ,"]" ,"}" ,")","?","*","+",".",">",";"};
    // Convertir le tableau en liste
    List<String> list = Arrays.asList(SpecialChars);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_item);
        
        
        listView = findViewById(R.id.lv_items);


        editTextSearchItem = findViewById(R.id.et_search);



        Spinner spinner = findViewById(R.id.spinner_tris);
        ArrayAdapter<CharSequence> adapter_tris= ArrayAdapter.createFromResource(this, R.array.tris, android.R.layout.simple_spinner_item);
        adapter_tris.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter_tris);
        spinner.setOnItemSelectedListener(this);

        listView.setOnItemSelectedListener(this);


        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");

        Intent intent_2=new Intent(this, ActivityEcrirRecapSouffrance.class);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Intent intent = new Intent(this, ActivityItemdetails2.class);

                System.out.println("onItemClick: " +position);

                Poisson poisson = (Poisson) listView.getItemAtPosition(position);

                System.out.println(poisson);

                intent_2.putExtra("main_user", main_user);
                Bundle extra = new Bundle();
                extra.putSerializable("poisson", (Serializable) poisson);
                intent_2.putExtra("extra", extra);
                startActivity(intent_2);



            }
        });

       // getItems();


        
    }
    
    
    /*private void getItems() {
        
        
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
     * R??cupere les inforamtions de ...
     *
     * @param jsonResponce la r??ponse du serveur X pour ...
     */
    /*private void parseItems(String jsonResponce) {
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            
            
            for (int i = 0; i < jarray.length(); i++) {
    
                JSONObject jo=jarray.getJSONObject(i);
    
                String Bac=jo.getString("Bac");
                String Lot=jo.getString("Lot");
                String Lignee=jo.getString("Lignee");
                String Age=jo.getString("Age");
                String Responsable=jo.getString("Responsable");
                // String PointLimite = jo.getString("PointLimite");
                String Key=jo.getString("Key");
    
                HashMap<String, String> item=new HashMap<>();
    
                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                // item.put("PointLimite", PointLimite);
                item.put("Key", Key);
                list.add(item);
    
    
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list_pdf=list;
        //cr??ation ..
        adapter=new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable});
        
        
        listView.setAdapter(adapter);
        loading.dismiss();
        
        
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheRegistreSouffrance.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
    }*/
    
    /*public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Intent intent = new Intent(this, ActivityItemdetails2.class);
        Intent intent=new Intent(this, ActivityEcrirRecapSouffrance.class);
        HashMap map=(HashMap) parent.getItemAtPosition(position);
    
        String Bac=map.get("Bac").toString();
        String Lot=map.get("Lot").toString();
        String Lignee=map.get("Lignee").toString();
        String Age=map.get("Age").toString();
        String Responsable=map.get("Responsable").toString();
        // String PointLimite = map.get("PointLimite").toString();
        String Key=map.get("Key").toString();
    
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("main_user", main_user);
        intent.putExtra("Age", Age);
        intent.putExtra("Responsable", Responsable);
        //  intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
    
        startActivity(intent);
    }*/


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

        int scaledSize = getResources().getDimensionPixelSize(R.dimen.myFontSize);
        myPaint.setTextSize(scaledSize);

        for(int i = 0 ; i < list_pdf.size(); i++){
            canvas.drawText(String.valueOf(list_pdf.get(i)), 20, 2+i*5, myPaint);
        }

        myPdfDocument.finishPage(myPage);

        File file = new File(Environment.getExternalStorageDirectory(), "/Registre_Souffrance"+date+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e){
            e.printStackTrace();
        }

        myPdfDocument.close();
        Toast.makeText(getApplicationContext(), "Pdf enregistr?? dans T??l??chargements", Toast.LENGTH_SHORT).show();
    }


    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    


    private void getItems_tri(final int num_tri) {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwqCJJWqCBXyVIatCzuiST50A0_kcxPXL-GH9BEYtBfk6y-aEHX/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems_tri(response, num_tri);
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


    private void parseItems_tri(String jsonResponce, int num_tri) {

        ArrayList<Poisson> age_sup_24 = new ArrayList<>();
        ArrayList<Poisson> age_inf_24 = new ArrayList<>();
        ArrayList<Poisson> data = new ArrayList<>();

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



                /*HashMap<String, String> item = new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);*/


                /*---------------------METTRE IMAGE POISSON MORT---------------------*/
                /*-------------------------------------------------------------------*/

                // enlever les bugs quand les poissons sont trop ag??
                Integer Image;
                if (Integer.parseInt(Age)==22){
                    Image=icon[0];
                } else {
                    Image=icon[1];
                }

                Integer Color=0;

                if(24<=Integer.parseInt(Age)){
                    age_sup_24.add(new Poisson(Lot, Bac,  Responsable, Lignee,  Age, Key,  Image, Color));
                } else {
                    age_inf_24.add(new Poisson(Lot, Bac,  Responsable, Lignee,  Age, Key,  Image, Color));
                }

                if (Integer.parseInt(Age)<1000){
                    data.add(new Poisson( Lot, Bac, Responsable, Lignee, Age, Key, Image, Color));
                }
            }



            ArrayList<Poisson> list_poisson_peril=new ArrayList<>();
            ArrayList<Poisson> list_poisson_ok=new ArrayList<>();


            // mettre liseret rouge si lignee en p??ril

            for (int i =0;i<age_sup_24.size();i++){
                int ok=0;
                for (int j=0;j<age_inf_24.size();j++){
                    if (age_sup_24.get(i).getLignee().equals(age_inf_24.get(j).getLignee())){
                        ok=1;
                    }
                }
                if (ok==0){

                    for (int k=0;k<data.size();k++){
                        if (data.get(k).getKey()==age_sup_24.get(i).getKey()){
                            data.get(k).setLiseret(R.drawable.liseret_rouge);
                            list_poisson_peril.add(data.get(k));
                        }
                    }
                }
            }

            for (int i=0;i<data.size();i++){
                if (data.get(i).getColor()==0){
                    list_poisson_ok.add(data.get(i));
                }
            }

            if (num_tri==1){
                /*Tri par ordre alphab??tique de lignee*/
                Comparator<Poisson> ligneeComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {

                        String lignee1=o1.getLignee().toUpperCase();
                        String prem_1=lignee1.substring(0, 1);
                        String rest_1=lignee1.substring(1, lignee1.length());

                        String lignee2=o2.getLignee().toUpperCase();
                        String prem_2=lignee2.substring(0, 1);
                        String rest_2=lignee2.substring(1, lignee2.length());


                        if (prem_1.equals(" ")) {
                            if (prem_2.equals(" ")){
                                return (rest_1.compareTo(rest_2));
                            }
                            if (contains(SpecialChars, prem_2)){
                                return (rest_1.compareTo(rest_2));
                            }
                            return (rest_1.compareTo(lignee2));
                        }

                        else if (contains(SpecialChars, prem_1)) {

                            if (contains(SpecialChars, prem_2)){
                                return (rest_1.compareTo(rest_2));
                            }
                            if (prem_2.equals(" ")){
                                return (rest_1.compareTo(rest_2));
                            }

                            return (rest_1.compareTo(lignee2));
                        }


                        /*if (prem_2.equals(" ")) {
                            if (prem_1.equals(" ")){
                                return (rest_2.compareTo(rest_1));
                            }
                            if (contains(SpecialChars, prem_1)){
                                return (rest_2.compareTo(rest_1));
                            }
                            return (rest_2.compareTo(lignee1));
                        }

                        if (contains(SpecialChars, prem_2)) {

                            if (contains(SpecialChars, prem_1)){
                                return (rest_2.compareTo(rest_1));
                            }
                            if (prem_1.equals(" ")){
                                return (rest_2.compareTo(rest_1));
                            }

                            return (rest_2.compareTo(lignee1));
                        }*/



                        else {
                            return (lignee1.compareTo(lignee2));
                        }
                    }
                };

                // And then sort it using collections.sort().
                Collections.sort(data, ligneeComparator);

            }


            if (num_tri==2){
                /*Tri par ordre d??croissant l'Age*/
                Comparator<Poisson> ageComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {

                        /* Tri par odre alphab??tique */

                        String resp1 = o1.getResponsable();
                        String resp2 = o2.getResponsable();

                        if (resp1.equals(resp2)){

                            // Get the Age and compare the Age.
                            Integer age1 = Integer.parseInt(o1.getAge());
                            Integer age2 = Integer.parseInt(o2.getAge());

                            return age1.compareTo(age2);
                        } else {
                            return (resp1.toUpperCase()).compareTo(resp2.toUpperCase());
                        }

                    }
                };

                // And then sort it using collections.sort().
                Collections.sort(data, ageComparator);
                Collections.reverse(data);

            }


            if (num_tri==3){
                /*Tri par ordre alphab??tique bac*/
                Comparator<Poisson> bacComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {
                        return (o1.getBac().toUpperCase()).compareTo((o2.getBac()).toUpperCase());
                    }
                };

                // And then sort it using collections.sort().
                Collections.sort(data, bacComparator);

            }


            if (num_tri==4){
                /*R??cup??rer les lign??es en p??ril*/

                data=list_poisson_peril;

                for (int i=0; i<list_poisson_ok.size();i++){
                    data.add(list_poisson_ok.get(i));
                }

                /*Comparator<HashMap<String, String>> bacComparator = new Comparator<HashMap<String,String>>() {

                    @Override
                    public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                        return (o1.get("Bac").toUpperCase()).compareTo((o2.get("Bac")).toUpperCase());
                    }
                };

                // And then sort it using collections.sort().
                Collections.sort(list, bacComparator);*/

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter=new PoissonAdapter2(this, data);



        //assign adapter to list view
        listView.setAdapter(adapter);

        //listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter.toggle(pos));


        loading.dismiss();

        //Toast


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheRegistreSouffrance.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

/*        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Bouton cliqu??___________________");
                System.out.println(adapter.getSelected());
                list_select = adapter.getSelected();
                System.out.println(list_select);
                intent_2.putExtra("main_user", main_user);
                Bundle extra = new Bundle();
                extra.putSerializable("list_select", (Serializable) list_select);
                intent_2.putExtra("extra", extra);

                //intent_2.putExtra("list_select", (Serializable) list_select);

                startActivity(intent_2);

                // Toast.makeText(this, "Vous avez selectionn??  "+list_select.size()+" ??l??ment(s)", Toast.LENGTH_LONG).show();

            }
        });*/

    }



    static boolean contains(String T[], String val) {
        for (int i = 0; i < T.length; i++) {
            if (val.equals(T[i]))
                //retourner la position courante
                return true;
        }return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                getItems_tri(3);
                Toast.makeText(parent.getContext(), "Tri?? par bacs", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                getItems_tri(1);
                Toast.makeText(parent.getContext(), "Tri?? par lign??e", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                getItems_tri(2);
                Toast.makeText(parent.getContext(), "Trier par ??ge", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                getItems_tri(4);
                Toast.makeText(parent.getContext(), "Lign??e en p??ril", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

