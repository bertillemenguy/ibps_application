package com.example.appbertille;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

public class ActivityHistoriqueAccouplementsPourOeufs extends AppCompatActivity implements AdapterView.OnItemClickListener {
    

    ListView listView;
    AccouplementAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    String main_user = "";
    Date date=null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
    
        listView=findViewById(R.id.lv_items);
        listView.setOnItemClickListener(this);
        editTextSearchItem=findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
    
        getItems();
    
    }
    
    
    private void getItems() {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw6mmZdn43pcoDmmmqS471JzTwea9jJ_PnSrMVxudNE3XHWbYr0nYfqsT6FneLlvLdQ/exec?action=getItems", new Response.Listener<String>() {
            @TargetApi(Build.VERSION_CODES.O)
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
        ArrayList<Accouplement> list_accouplement = new ArrayList<>();

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


                //   String Date = outputFormat.format(date);
                String operateur=jo.getString("operateur");
                String LigneeM=jo.getString("LigneeM");
                String LigneeF=jo.getString("LigneeF");
                String Couleur1=jo.getString("Couleur1");
                String Couleur2=jo.getString("Couleur2");
                String Bac=jo.getString("Bac");
                String Bac2=jo.getString("Bac2");
                String NbBac=jo.getString("NbBac");
                String NbMale=jo.getString("NbMale");
                String NbFemelle=jo.getString("NbFemelle");
                String Age = jo.getString("Age");
                String Age2 = jo.getString("Age2");
                String Lot = jo.getString("Lot");
                String Lot2 = jo.getString("Lot2");
                String Id = jo.getString("Id");

                list_accouplement.add(new Accouplement(Date, operateur, NbBac, Couleur1, Couleur2, NbMale, Lot, Bac, LigneeM, Age,  NbFemelle,  Lot2,  Bac2,  LigneeF, Age2, Id));

                HashMap<String, String> item = new HashMap<>();

                item.put("Date", Date);
                item.put("operateur", operateur);
                item.put("LigneeM", LigneeM);
                item.put("LigneeF", LigneeF);
                item.put("Couleur1", Couleur1);
                item.put("Couleur2", Couleur2);
                item.put("Bac", Bac);
                item.put("Bac2", Bac2);
                item.put("NbBac", NbBac);
                item.put("NbMale", NbMale);
                item.put("NbFemelle", NbFemelle);
                item.put("Age", Age);
                item.put("Age2", Age2);
                item.put("Lot", Lot);
                item.put("Lot2", Lot2);


                list.add(item);


            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }


        adapter=new AccouplementAdapter(this, list_accouplement, main_user);


        listView.setAdapter(adapter);
        loading.dismiss();
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueAccouplementsPourOeufs.this.adapter.getFilter().filter(charSequence);
            }
    
            @Override
            public void afterTextChanged(Editable editable) {
        
        
            }
        });
    }
    
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Coucou tu à clicker");
        Intent intent=new Intent(this, ActivityEcrirRecapOeuf.class);
        HashMap map=(HashMap) parent.getItemAtPosition(position);
        
        String Date=map.get("Date").toString();
        String operateur=map.get("operateur").toString();
        String LigneeM=map.get("LigneeM").toString();
        String LigneeF=map.get("LigneeF").toString();
        String Couleur1=map.get("Couleur1").toString();
        String Couleur2=map.get("Couleur2").toString();
        String Bac=map.get("Bac").toString();
        String Bac2=map.get("Bac2").toString();
        String NbMale=map.get("NbMale").toString();
        String NbFemelle=map.get("NbFemelle").toString();
        String NbBac=map.get("NbBac").toString();
        String Age=map.get("Age").toString();
        String Age2=map.get("Age2").toString();
        String Lot=map.get("Lot").toString();
        String Lot2=map.get("Lot2").toString();
        String Key=map.get("Key").toString();
        String Key2=map.get("Key2").toString();
        

        intent.putExtra("main_user", main_user);
        intent.putExtra("Date", Date);
        intent.putExtra("operateur", operateur);
        intent.putExtra("LigneeM", LigneeM);
        intent.putExtra("LigneeF", LigneeF);
        intent.putExtra("Couleur1", Couleur1);
        intent.putExtra("Couleur2", Couleur2);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Bac2", Bac2);
        intent.putExtra("NbBac", NbBac);
        intent.putExtra("NbMale", NbMale);
        intent.putExtra("NbFemelle", NbFemelle);
        intent.putExtra("Age", Age);
        intent.putExtra("Age2", Age2);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Lot2", Lot2);
        intent.putExtra("Key", Key);
        intent.putExtra("Key2", Key2);
        
        startActivity(intent);
    }
    
    
    public void fermeractivite(View view) {
        this.finish();
    }
    
    public void lancermenu(View view) {
        
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    
    public void onPointerCaptureChanged(boolean hasCapture) {
    
    }
}

