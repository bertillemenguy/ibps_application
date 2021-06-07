package com.example.endpointapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ActivityEcrirRecapBacs extends AppCompatActivity implements Serializable, View.OnTouchListener, View.OnClickListener, AdapterView.OnItemSelectedListener {


    // élément checkbox
    List<Poisson> list_select;
    PoissonAdapter adapter;

    TextView textViewitemLignee, textViewBac, textViewLot, textViewAge, textViewResponsable;

    ListView listView;
    //SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;

    //String Bac,Lot,Lignee,Age,Responsable,PointLimite,Elimine,Bac2,NouveauBac,Lignee2, Lot2;
    String Key="";
    String Key2="";
    String Bac="";
    String Lot="";
    String Lignee="";
    String Age="";
    String Responsable="";
    String PointLimite="";
    String Action="";
    String Bac2="";
    String NouveauBac="";
    String Lignee2="";
    String Lot2 = "";


    String main_user = "";

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};


    String[] SpecialChars = { "<", "(" ,"[" ,"{" ,"\\" , "^" , "-","=","$","!" ,"|" ,"]" ,"}" ,")","?","*","+",".",">",";"};
    // Convertir le tableau en liste
    List<String> list = Arrays.asList(SpecialChars);




    //Spinner OperateurSpinner;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_ecrir_recap_bacs);

        listView = findViewById(R.id.listView);
        // operateur
        // operateur = intent.getStringExtra("operateur");
        //OperateurSpinner = findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter17);
        // operateur = intent.getStringExtra("operateur");

        Spinner spinner = findViewById(R.id.spinner_tris);
        ArrayAdapter<CharSequence> adapter_tris= ArrayAdapter.createFromResource(this, R.array.tris, android.R.layout.simple_spinner_item);

        adapter_tris.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter_tris);
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();


        //Bac=intent.getStringExtra("Bac");
        //Lot=intent.getStringExtra("Lot");
        //Lignee=intent.getStringExtra("Lignee");
        //Age=intent.getStringExtra("Age");
        //Key=intent.getStringExtra("Key");
    
        //textViewitemLignee=findViewById(R.id.tv_lignee);
        //textViewBac=findViewById(R.id.tv_Bac);
        //textViewAge=findViewById(R.id.tv_age);
        //textViewLot=findViewById(R.id.tv_lot);
        //textViewResponsable=findViewById(R.id.tv_responsable);
    
    
        //textViewLot.setText(Lot);
        //textViewBac.setText(Bac);
        //textViewitemLignee.setText(Lignee);
        //textViewAge.setText(Age);
        //textViewResponsable.setText(Responsable);
    
        // Get the transferred data from source activity.
        main_user = intent.getStringExtra("main_user");

        //élément checkbox

        //getItems();

    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
    

    // Réunir
    public void lancerreunir(View view) {
        //Intent intent = new Intent(this, ActivityReunirBacs.class);

        Intent intent_2 = new Intent(this, ActivityReunificationBacs.class);

        System.out.println("Bouton cliqué___________________");
        System.out.println(adapter.getSelected());

        if ((adapter.getSelected().size()>2)||(adapter.getSelected().size()<=1)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 2 éléments", Toast.LENGTH_SHORT).show();
        } else{
            list_select = adapter.getSelected();
            System.out.println(list_select);

            intent_2.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent_2.putExtra("extra", extra);
            intent_2.putExtra("list_select", (Serializable) list_select);

            startActivity(intent_2);
        }

        //intent.putExtra("main_user", main_user);
        //intent.putExtra("Bac", Bac);
        //intent.putExtra("Lot", Lot);
        //intent.putExtra("Responsable", Responsable);
        //intent.putExtra("Lignee", Lignee);
        //intent.putExtra("Age", Age);
        //intent.putExtra("Key", Key);
        //startActivity(intent);
    }

    // Déplacer bac
    public void lancerdeplacer(View view) {

        Intent intent=new Intent(this, ActivityDeplacerBacs.class);

        list_select = adapter.getSelected();


        if ((list_select.size()>1)||(list_select.size()==0)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 éléments", Toast.LENGTH_SHORT).show();
        } else {

            intent.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent.putExtra("extra", extra);
            intent.putExtra("list_select", (Serializable) list_select);
            startActivity(intent);

        }
    }


    //Dupliquer
    public void lancerdupliquer(View view) {

        Intent intent=new Intent(this, ActivityDupliquerBacs.class);
        list_select = adapter.getSelected();

        if ((list_select.size()>1)||(list_select.size()==0)){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 éléments", Toast.LENGTH_SHORT).show();
        } else {

            intent.putExtra("main_user", main_user);
            Bundle extra = new Bundle();
            extra.putSerializable("list_select", (Serializable) list_select);
            intent.putExtra("extra", extra);
            intent.putExtra("list_select", (Serializable) list_select);
            startActivity(intent);

        }

    }
    
    /*public void lancersignaler(View view) {
        Intent intent=new Intent(this, ActivityErreur.class);
        intent.putExtra("main_user", main_user);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Key", Key);
        startActivity(intent);
    }*/


    // Eliminer bac(s)
    public void lancereliminer(View view) {
        Intent intent=new Intent(this, ActivityEcrirRecapBacs.class);
        list_select = adapter.getSelected();
        System.out.println(list_select);
        Action="Eliminé";

        if(list_select.size()==0){
            Toast.makeText(getApplicationContext(), "Vous devez selectionner 1 ou plusieurs éléments", Toast.LENGTH_SHORT).show();
        } else {
            for(int i=0; i<list_select.size();i++){
                WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, main_user, NouveauBac, Action, list_select.get(i).getBac(), list_select.get(i).getLignee(), list_select.get(i).getLot(), Bac2, Lignee2, Lot2, list_select.get(i).getKey(), Key2);

                //Temps d'attente !!! IMPORTANT
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startActivity(intent);
        }
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

    }*/


    /*private void parseItems(String jsonResponce) {

        List<Poisson> data = new ArrayList<>();
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
                String Key=jo.getString("Key");


                Integer Image;

                if (Integer.parseInt(Age)==22){
                    Image=icon[0];
                } else {
                    Image=icon[1];
                }

                Integer Color;
                if (Integer.parseInt(Age)==22){
                    Color= R.drawable.liseret_rouge;
                } else {
                    Color=0;
                }

              /*  HashMap<String, String> item=new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);
                list.add(item);*/

                /*data.add(new Poisson(Lot,Bac,Responsable,Lignee,Age,Key,Image, Color));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //adapter=new SimpleAdapter(this, list, R.layout.list_item_registre_checked, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable});
        //listView.setAdapter(adapter);


        adapter_poisson=new PoissonAdapter(this, data);
        listView.setAdapter(adapter_poisson);


        // clics sur les éléments, on les envoie à l'adaptateur
        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter_poisson.toggle(pos));

        loading.dismiss();

    }*/




    public void lancermenu(View view) {

        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    static boolean contains(String T[], String val) {
        for (int i = 0; i < T.length; i++) {
            if (val.equals(T[i]))
                //retourner la position courante
                return true;
        }return false;
    }


    @Override
    public void onClick(View v) {

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                getItems_tri(3);
                Toast.makeText(parent.getContext(), "Trié par bacs", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                getItems_tri(1);
                Toast.makeText(parent.getContext(), "Trié par lignée", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                getItems_tri(2);
                Toast.makeText(parent.getContext(), "Trier par âge", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                getItems_tri(4);
                Toast.makeText(parent.getContext(), "Lignée en péril", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // public void onPointerCaptureChanged(boolean hasCapture) {

    // }



    /**
     * charger le registre au niveau du menu. sans issu apres
     */

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

    /**
     * formater les donnees dans un nouveau layout (list_item_row)
     *
     * @param jsonResponce
     */



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
                String Responsable = jo.getString("Responsable");
                String Key = jo.getString("Key");



                /*HashMap<String, String> item = new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);*/


                /*---------------------METTRE IMAGE POISSON MORT---------------------*/
                /*-------------------------------------------------------------------*/

                // enlever les bugs quand les poissons sont trop agé
                Integer Image;
                if (Integer.parseInt(Age) == 22) {
                    Image = icon[0];
                } else {
                    Image = icon[1];
                }

                Integer Color = 0;

                if (24 <= Integer.parseInt(Age)) {
                    age_sup_24.add(new Poisson(Lot, Bac, Responsable, Lignee, Age, Key, Image, Color));
                } else {
                    age_inf_24.add(new Poisson(Lot, Bac, Responsable, Lignee, Age, Key, Image, Color));
                }

                if (Integer.parseInt(Age) < 1000) {
                    data.add(new Poisson(Lot, Bac, Responsable, Lignee, Age, Key, Image, Color));
                }
            }


            ArrayList<Poisson> list_poisson_peril = new ArrayList<>();
            ArrayList<Poisson> list_poisson_ok = new ArrayList<>();


            // mettre liseret rouge si lignee en péril

            for (int i = 0; i < age_sup_24.size(); i++) {
                int ok = 0;
                for (int j = 0; j < age_inf_24.size(); j++) {
                    if (age_sup_24.get(i).getLignee().equals(age_inf_24.get(j).getLignee())) {
                        ok = 1;
                    }
                }
                if (ok == 0) {

                    for (int k = 0; k < data.size(); k++) {
                        if (data.get(k).getKey() == age_sup_24.get(i).getKey()) {
                            data.get(k).setLiseret(R.drawable.liseret_rouge);
                            list_poisson_peril.add(data.get(k));
                        }
                    }
                }
            }

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getColor() == 0) {
                    list_poisson_ok.add(data.get(i));
                }
            }

            if (num_tri == 1) {
                /*Tri par ordre alphabétique de lignee*/
                Comparator<Poisson> ligneeComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {

                        String lignee1 = o1.getLignee().toUpperCase();
                        String prem_1 = lignee1.substring(0, 1);
                        String rest_1 = lignee1.substring(1, lignee1.length());

                        String lignee2 = o2.getLignee().toUpperCase();
                        String prem_2 = lignee2.substring(0, 1);
                        String rest_2 = lignee2.substring(1, lignee2.length());


                        if (prem_1.equals(" ")) {
                            if (prem_2.equals(" ")) {
                                return (rest_1.compareTo(rest_2));
                            }
                            if (contains(SpecialChars, prem_2)) {
                                return (rest_1.compareTo(rest_2));
                            }
                            return (rest_1.compareTo(lignee2));
                        } else if (contains(SpecialChars, prem_1)) {

                            if (contains(SpecialChars, prem_2)) {
                                return (rest_1.compareTo(rest_2));
                            }
                            if (prem_2.equals(" ")) {
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


            if (num_tri == 2) {
                /*Tri par ordre décroissant l'Age*/
                Comparator<Poisson> ageComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {

                        /* Tri par odre alphabétique */

                        String resp1 = o1.getResponsable();
                        String resp2 = o2.getResponsable();

                        if (resp1.equals(resp2)) {

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


            if (num_tri == 3) {
                /*Tri par ordre alphabétique bac*/
                Comparator<Poisson> bacComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {
                        return (o1.getBac().toUpperCase()).compareTo((o2.getBac()).toUpperCase());
                    }
                };

                // And then sort it using collections.sort().
                Collections.sort(data, bacComparator);

            }


            if (num_tri == 4) {
                /*Récupérer les lignées en péril*/

                data = list_poisson_peril;

                for (int i = 0; i < list_poisson_ok.size(); i++) {
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


        adapter = new PoissonAdapter(this, data);


        //assign adapter to list view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter.toggle(pos));

        loading.dismiss();

        //Toast


    }

}