package com.example.appbertille;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.Volley;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ActivityRechercheRegistre extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    String main_user;
    ListView listView;
    PoissonAdapter2 adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;


    String[] SpecialChars = { "<", "(" ,"[" ,"{" ,"\\" , "^" , "-","=","$","!" ,"|" ,"]" ,"}" ,")","?","*","+",".",">",";"};
    // Convertir le tableau en liste
    List<String> list = Arrays.asList(SpecialChars);



    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};
    //int[] color ={R.drawable.liseret_rouge};


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_item);

        Spinner spinner = findViewById(R.id.spinner_tris);
        ArrayAdapter<CharSequence> adapter_tris= ArrayAdapter.createFromResource(this, R.array.tris, android.R.layout.simple_spinner_item);

        adapter_tris.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter_tris);
        spinner.setOnItemSelectedListener(this);
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
        main_user = intent.getStringExtra("main_user");

        //getItems();


    }


    static boolean contains(String T[], String val) {
        for (int i = 0; i < T.length; i++) {
            if (val.equals(T[i]))
                //retourner la position courante
                return true;
        }return false;
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
        intent.putExtra("main_user", main_user);
        startActivity(intent);
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
                getItems_tri(0);
                Toast.makeText(parent.getContext(), "Trié par bac", Toast.LENGTH_SHORT).show();
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
                getItems_tri(3);
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

                // enlever les bugs quand les poissons sont trop agé
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


            // mettre liseret rouge si lignee en péril

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
                /*Tri par ordre alphabétique de lignee*/
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
                Comparator<Poisson> ageComparator = new Comparator<Poisson>() {

                    @Override
                    public int compare(Poisson o1, Poisson o2) {

                        /* Tri par ordre alphabétique l'équipe responsable*/
                        String resp1 = o1.getResponsable();
                        String resp2 = o2.getResponsable();

                        if (resp1.equals(resp2)){

                            /*Tri par ordre décroissant l'Age*/
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


            if (num_tri==0){
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


            if (num_tri==3){
                /*Récupérer les lignées en péril*/

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




}

