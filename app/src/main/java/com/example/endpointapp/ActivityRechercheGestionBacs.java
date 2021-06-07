package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ActivityRechercheGestionBacs extends AppCompatActivity  {
    
    //implements AdapterView.OnItemClickListener


    // élément checkbox
    Intent intent_2 ;
    List<Bac> list_select;
    Button button;
    BacAdapter adapter_bac;


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;
    
    String main_user="";
    Date date=null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_item_traite);
    
        listView=findViewById(R.id.lv_items);
        //listView.setOnItemClickListener(this);

        editTextSearchItem=findViewById(R.id.et_search);
    
        // Get the transferred data from source activity.
        Intent intent=getIntent();
        main_user=intent.getStringExtra("main_user");

        button = findViewById(R.id.btn_valider);

        button.setText("Traité");
        intent_2 = new Intent(this, ActivityRechercheGestionBacs.class);


        getItems(1);
    
    }
    
    
    private void getItems(int type_affichage) {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxr8Y48s1vLCcnRigyBOwpzZqYa5t8-E8omRRKXZtwuf6s42zgJFwXSyVcBFcWEAh0v/exec?action=getItems", new Response.Listener<String>() {
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

        List<Bac> data = new ArrayList<>();
        List<Bac> tete = new ArrayList<>();
        List<Bac> queue = new ArrayList<Bac>();


        //ArrayList<HashMap<String, String>> list = new ArrayList<>();
        
        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");
            

            for (int i = 0; i < jarray.length(); i++) {
                
                JSONObject jo = jarray.getJSONObject(i);
    
                SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat=new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                try {
                    date = inputFormat.parse(jo.getString("Date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String Date=dateFormat.format(date);

                String modif_Date=outputFormat.format(date);

                String Id=jo.getString("ID");

                String Actions=jo.getString("Actions");
                String NouveauBac=jo.getString("NouveauBac");
                String Lignee=jo.getString("Lignee");
                String Bac=jo.getString("Bac");
                String Lignee2=jo.getString("Lignee2");
                String Bac2=jo.getString("Bac2");
    
                String remarque=jo.getString("remarque");
                String remarque2=jo.getString("remarque2");
                String Bac2b=jo.getString("Bac2b");
    
                String remarque3=jo.getString("remarque3");
                String Bac3=jo.getString("Bac3");
    
                String remarque4=jo.getString("remarque4");
                String Bac4=jo.getString("Bac4");
    
                String Lot=jo.getString("Lot");
                String Lot2=jo.getString("Lot2");

                String SItraite=jo.getString("SItraite");



                if (SItraite.equals("En cours")){
                    tete.add(new Bac(modif_Date, Id, Actions, NouveauBac, Lignee, Bac, Lignee2, Bac2, remarque, remarque2, Bac2b, remarque3, Bac3, remarque4, Bac4, Lot, Lot2, SItraite));
                } else {
                    queue.add(new Bac(modif_Date, Id, Actions, NouveauBac, Lignee, Bac, Lignee2, Bac2, remarque, remarque2, Bac2b, remarque3, Bac3, remarque4, Bac4, Lot, Lot2, SItraite));
                }


                if (type_affichage==2){
                    data.add(new Bac(modif_Date, Id, Actions, NouveauBac, Lignee, Bac, Lignee2, Bac2, remarque, remarque2, Bac2b, remarque3, Bac3, remarque4, Bac4, Lot, Lot2, SItraite));
                }

                /*HashMap<String, String> item=new HashMap<>();
    
                item.put("Date", Date);
                item.put("Actions", Actions);
                item.put("NouveauBac", NouveauBac);
                item.put("Lignee", Lignee);
                item.put("Bac", Bac);
                item.put("Lignee2", Lignee2);
                item.put("Bac2", Bac2);
    
                item.put("remarque", remarque);
                item.put("remarque2", remarque2);
                item.put("Bac2b", Bac2b);
    
    
                item.put("remarque3", remarque3);
                item.put("Bac3", Bac3);
                item.put("remarque4", remarque4);
                item.put("Bac4", Bac4);
    
                item.put("Lot", Lot);
                item.put("Lot2", Lot2);

                item.put("SItraite", SItraite);

                item.put("modif_Date", modif_Date);
                item.put("ID", Id);

                list.add(item);*/

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }






        //trier incident
        /*Trie par ordre alphabétique bac*/
        Comparator<Bac> incidentComparator = new Comparator<Bac>() {


            @Override
            public int compare(Bac o1, Bac o2) {


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



        adapter_bac=new BacAdapter(this, data);
        

        listView.setAdapter(adapter_bac);

        // clics sur les éléments, on les envoie à l'adaptateur
        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter_bac.toggle(pos));




        loading.dismiss();
        
        
        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheGestionBacs.this.adapter.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            
            }
        });



        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Bouton cliqué___________________");

                System.out.println(adapter_bac.getSelected());

                list_select = adapter_bac.getSelected();

                System.out.println(list_select);

                lancersauvregarde();



                //Bundle extra = new Bundle();

                //extra.putSerializable("list_select", (Serializable) list_select);

                //intent_2.putExtra("extra", extra);

                //intent_2.putExtra("list_select", (Serializable) list_select);

                intent_2.putExtra("main_user", main_user);
                startActivity(intent_2);

                // Toast.makeText(this, "Vous avez selectionné  "+list_select.size()+" élément(s)", Toast.LENGTH_LONG).show();

            }

        });


    }


    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void tout_afficher(View view) {

        getItems(2);
    }
    
    /*public void lancertraiter(View view) {
        //Intent intent = new Intent(this,ActivityMenu.class);
        //intent.putExtra("operateur", operateur);
        main_user="operateur";
        Elimine="<<<<<TRAITE>>>>>";
        
        WriteOnSheetTRAITE.writeData(this, main_user, Elimine);
        // startActivity(intent);
    }*/
    
    public void fermeractivite(View view) {
        this.finish();
    }






    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CustomPopupBacs.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        String Id = map.get("ID").toString();
        intent.putExtra("ID", Id);
        intent.putExtra("main_user", main_user);
        //Toast.makeText(ActivityRechercheGestionBacs.this, Date, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }*/


    public void lancersauvregarde(){
        for (int i=0; i<list_select.size(); i++) {

            System.out.println("COUCOU valider:"+list_select.get(i));

            String key = list_select.get(i).getId();
            WriteOnSheetBacsTraite.updateData(this, key);

            //Temps d'attente !!! IMPORTANT
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
}




//https://script.google.com/macros/s/AKfycbxwQDJZtx2VIcjVdtnTgdWQDWiR9wBrPUSpEknEOurhhIeLP59kf-eWAvKwUUPPhOEX/exec