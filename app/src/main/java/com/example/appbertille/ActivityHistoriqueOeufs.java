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

public class ActivityHistoriqueOeufs extends AppCompatActivity {
    
    String main_user;

    ListView listView;
    OeufAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    //String operateur = "";
    Date date = null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item);
    
        listView = findViewById(R.id.lv_items);
    
        editTextSearchItem = findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
    
        getItems();
    
    }
    
    
    private void getItems() {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbz2DmegfdqprkLJ7lWxX-dwbVRM4LCN7WDdPUa699QHYdzweh0vzSxYs_JEHqQedYtu/exec?action=getItems", new Response.Listener<String>() {
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
    
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void parseItems(String jsonResponce) {


        ArrayList<Oeuf> list_oeufs = new ArrayList<>();

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            
            
            for (int i = 0; i < jarray.length(); i++) {
                
                JSONObject jo = jarray.getJSONObject(i);
    
                SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat=new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);
                
                try {
                    date = inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String Date=outputFormat.format(date);
                
                
                Calendar c = Calendar.getInstance();
                
                c.setTime(outputFormat.parse(Date));
                
                c.add(Calendar.DATE, 1);  // number of days to add
                
                Date = outputFormat.format(c.getTime());  // dt is now the new date
    
    
                /**  Date=new Date();
                 Calendar c = Calendar.getInstance();
                 c.setTime(currentDate);
     
                 */
    
    
                String operateur=jo.getString("operateur");
                String LigneeM=jo.getString("LigneeM");
                String LigneeF=jo.getString("LigneeF");
                String BacM=jo.getString("BacM");
                String BacF=jo.getString("BacF");
                String NbBac=jo.getString("NbBac");
                String NbMale=jo.getString("NbMale");
                String NbFemelle=jo.getString("NbFemelle");
                String AgeM=jo.getString("AgeM");
                String AgeF=jo.getString("AgeF");
                String quantite=jo.getString("quantite");
                String qualite=jo.getString("qualite");
                String LotM=jo.getString("LotM");
                String LotF=jo.getString("LotF");
                String NbMalesFeconde=jo.getString("NbMalesFeconde");
                String NbfemellesFeconde=jo.getString("NbfemellesFeconde");
                String Id=jo.getString("Id");



                list_oeufs.add(new Oeuf(Date,operateur,LigneeM, LigneeF,BacM,BacF,NbBac, NbMale,  NbFemelle, AgeM, AgeF, quantite, qualite, LotM, LotF,  NbMalesFeconde, NbfemellesFeconde, Id));

                HashMap<String, String> item=new HashMap<>();
    
                item.put("Date", Date);
                item.put("operateur", operateur);
                item.put("LigneeM", LigneeM);
                item.put("LigneeF", LigneeF);
                item.put("BacM", BacM);
                item.put("BacF", BacF);
                item.put("NbBac", NbBac);
                item.put("NbMale", NbMale);
                item.put("NbFemelle", NbFemelle);
                item.put("AgeM", AgeM);
                item.put("AgeF", AgeF);
                item.put("quantite", quantite);
                item.put("qualite", qualite);
    
                item.put("LotM", LotM);
                item.put("LotF", LotF);
                item.put("NbMalesFeconde", NbMalesFeconde);
                item.put("NbfemellesFeconde", NbfemellesFeconde);
                list.add(item);
                
                
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    
    
        adapter=new OeufAdapter(this, list_oeufs);
        
        
        listView.setAdapter(adapter);
        loading.dismiss();
        
        
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueOeufs.this.adapter.getFilter().filter(charSequence);
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
    
    
    public void fermeractivite(View view) {
        this.finish();
    }
}

