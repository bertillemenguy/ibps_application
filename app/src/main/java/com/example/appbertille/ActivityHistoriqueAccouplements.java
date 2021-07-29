package com.example.appbertille;

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
import android.widget.EditText;
import android.widget.ListView;

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

public class ActivityHistoriqueAccouplements extends AppCompatActivity {
    
    String main_user;

    ListView listView;
    AccouplementAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    //  String operateur = "";
    Date date=null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
    
        listView=findViewById(R.id.lv_items);
    
        editTextSearchItem=findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
    
        getItems();
    
    }
    
    
    private void getItems() {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbz_LO8Ir5g-uXo5NtVzsb7mMUGJbqaAplo6NwyKNfMFXl6-5eOX37HfuHrI5JkbqotY/exec?action=getItems", new Response.Listener<String>() {
            @RequiresApi(api=Build.VERSION_CODES.N)
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
                String Key = jo.getString("Key");
                String Key2 = jo.getString("Key2");

                list_accouplement.add(new Accouplement(Date, operateur, NbBac, Couleur1, Couleur2, NbMale, Lot, Bac, LigneeM, Age,  NbFemelle,  Lot2,  Bac2,  LigneeF, Age2, Id, Key, Key2));

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
                ActivityHistoriqueAccouplements.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            
            }
        });
    }
    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    
    public void onPointerCaptureChanged(boolean hasCapture) {
    
    }
}

