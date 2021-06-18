package com.example.appbertille;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityHistoriqueIncidents extends AppCompatActivity implements Serializable{
    
    
    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    Button button_tri;
    EditText editTextSearchItem;


    // élément checkbox
    Intent intent_2 ;
    List<Incident> list_select;
    IncidentAdapter adapter_incident;
    Button button;
    ImageButton button_delete;

    String main_user = "";
    Date date = null;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item_traite);
        //setContentView(R.layout.activity_recherche_morts);
        
        listView = findViewById(R.id.lv_items);

        //listView.setOnItemClickListener(this);

        editTextSearchItem = findViewById(R.id.et_search);

        //élément checkbox
        button = findViewById(R.id.btn_valider);

        button_delete = findViewById(R.id.btn_delete);


        intent_2 = new Intent(this, ActivityHistoriqueIncidents.class);

        button_tri=findViewById(R.id.afficher_tout);

        button.setText("Traiter");

        // Get the transferred data from source activity.
        Intent intent = getIntent();

        intent_2 = getIntent();


        main_user= intent.getStringExtra("main_user");


        getItems(1);
        
    }
    
    
    private void getItems(int type_affichage) {
        
        
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxv_6vHp6__F0NdJ8BWtqWhhC9JgkpVcHpD0tfbT0ETgvQwunmAzXdtPfsVUqTKBYHinw/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response, type_affichage);
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
    
    
    private void parseItems(String jsonResponce, int type_affichage) {
        
        List<Incident> data = new ArrayList<>();
        List<Incident> tete = new ArrayList<>();
        List<Incident> queue = new ArrayList<>();


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
                String Date = outputFormat.format(date);
                
                String Operateur = jo.getString("operateur");
                String Etat = jo.getString("etat");
                String eaudeville = jo.getString("eaudeville");
                String electricite = jo.getString("electricite");
                String aircomprime = jo.getString("aircomprime");
                String climatisation = jo.getString("climatisation");
                String eaudusysteme = jo.getString("eaudusysteme");
                String systemeaquatique = jo.getString("systemeaquatique");
                String travaux = jo.getString("travaux");
                String nourrissage = jo.getString("nourrissage");
                String key = jo.getString("key");


                if (Etat.equals("En cours")){
                    tete.add(new Incident(Date,Operateur,Etat, eaudeville, electricite, aircomprime, climatisation, eaudusysteme, systemeaquatique, travaux, nourrissage, key));
                } else {
                    queue.add(new Incident(Date,Operateur,Etat, eaudeville, electricite, aircomprime, climatisation, eaudusysteme, systemeaquatique, travaux, nourrissage, key));
                }

                if (type_affichage==2){
                    data.add(new Incident(Date,Operateur,Etat, eaudeville, electricite, aircomprime, climatisation, eaudusysteme, systemeaquatique, travaux, nourrissage, key));
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //trier incident
        /*Trie par ordre alphabétique bac*/
        Comparator<Incident> incidentComparator = new Comparator<Incident>() {


            @Override
            public int compare(Incident o1, Incident o2) {


                Date date_1=null;
                Date date_2=null;
                try {
                    date_1 = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE).parse(o1.getDate());
                    date_2 = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE).parse(o2.getDate());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return (date_1.compareTo((date_2)));
            }
        };



        if (type_affichage==1){
            // And then sort it using collections.sort().
            Collections.sort(tete, incidentComparator);
            Collections.sort(queue, incidentComparator);

            Collections.reverse(tete);
            Collections.reverse(queue);


            data=tete;

       /* for (int i =0; i<queue.size(); i++){
            data.add(queue.get(i));
        }*/
        }

        if (type_affichage==2){
            Collections.sort(data, incidentComparator);
            Collections.reverse(data);

        }


        // élément checkbox
        adapter_incident=new IncidentAdapter(this, data);
        listView.setAdapter(adapter_incident);

        // clics sur les éléments, on les envoie à l'adaptateur
        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter_incident.toggle(pos));



        loading.dismiss();
    
    
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityHistoriqueIncidents.this.adapter_incident.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                
                
            }
        });



        this.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_select = adapter_incident.getSelected();

                if (list_select.size()>1){
                    Toast.makeText(ActivityHistoriqueIncidents.this, "Vous devez selectionner 1 élément ", Toast.LENGTH_SHORT).show();
                } else {
                    String key = list_select.get(0).getKey();
                    System.out.println("KEY____"+key);
                    WriteOnSheetIncident.deleteData(ActivityHistoriqueIncidents.this, key);
                    //Temps d'attente !!! IMPORTANT
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    intent_2.putExtra("main_user", main_user);
                    startActivity(intent_2);
                }

            }
        });


        this.button_tri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(button_tri.getText().equals("Tous")){
                   button_tri.setText("En cours");
                   getItems(2);
               } else {
                   getItems(1);
                   button_tri.setText("Tous");
               }
            }
        });


        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(adapter_incident.getSelected());
                list_select = adapter_incident.getSelected();
                System.out.println(list_select);

                lancersauvregard();
                
                //Temps d'attente IMPORTANT
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                intent_2.putExtra("main_user", main_user);
                startActivity(intent_2);
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
    
    
    public void fermeractivite(View view) {
        this.finish();
    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CustomPopupIncident.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        String key = map.get("key").toString();
        intent.putExtra("key", key);
        intent.putExtra("main_user", main_user);
        //Toast.makeText(ActivityHistoriqueIncidents.this, key, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }*/


     public void lancersauvregard(){
         for (int i=0; i<list_select.size(); i++) {

             if(list_select.get(i).getEtat().equals("En cours")) {

                 String key = list_select.get(i).getKey();
                 WriteOnSheetIncident.updateData(this, key);

                 //Temps d'attente !!! IMPORTANT
                 try {
                     Thread.sleep(1500);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }


         }
     }


    public void tout_afficher(View view) {

        getItems(2);
    }
}
    
    

