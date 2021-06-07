package com.example.endpointapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityEcrirRecapMort extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner PoissonMortSpinner;
    
    String Bac = "";
    String main_user="";
    String Lignee="";
    String Lot="";
    String Age="";
    String Responsable="";
    String Key="";
    Integer Image;
    Integer Liseret;


    ProgressDialog loading;
    EditText editTextSearchItem;


    //recupérer list poisson selctionné
    ArrayList<Poisson> list_poisson;


    //String PointLimite;
    String Accouplement="";
    String ControleSanitaire="";




    ListView listView;


    TextView textViewLot, textViewBac, textViewLignee, textViewAge, textViewResponsable;
    
    
    //Spinner OperateurSpinner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_mort);

        listView = findViewById(R.id.list_item_select);


        main_user = getIntent().getStringExtra("main_user");


        // récupération des poissons séléctionnés
        Bundle extra = getIntent().getBundleExtra("extra");

        list_poisson = (ArrayList<Poisson>) extra.getSerializable("list_select");

        // operateur
        //OperateurSpinner=findViewById(R.id.spinner1);
        //ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        //adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //OperateurSpinner.setAdapter(adapter17);



        editTextSearchItem = findViewById(R.id.et_search);

        PoissonMortSpinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner.setAdapter(adapter);
        PoissonMortSpinner.setOnItemSelectedListener(this);
        
        // Get the transferred data from source activity.
        //Intent intent = getIntent();

        //list_select = intent.getStringArrayListExtra("list_select");

        List<HashMap<String, String>> list = new ArrayList<>();


        for (int i =0; i<list_poisson.size(); i++){

            Poisson poisson= list_poisson.get(i);
            //String s = String.valueOf(list_select.get(i));
            //s=s.substring(1, s.length()-1);
            //String[] pairs = s.split(", ");

            HashMap<String, String> map = new HashMap<>();


                Bac = poisson.getBac();
                Lot = poisson.getLot();
                Lignee = poisson.getLignee();
                Age = poisson.getAge();
                Responsable=poisson.getResponsable();
                Key=poisson.getKey();
                Image=poisson.getImage();
                Liseret=poisson.getColor();


            map.put("Bac", Bac);
                map.put("Lot", Lot);
                map.put("Lignee", Lignee);
                map.put("Age", Age);
                map.put("Responsable", Responsable);
                map.put("Key", Key);
                map.put("Image", Image+"");
                map.put("Liseret", Liseret +"");


           // for (String pair:pairs){

                //String[] entry = pair.split("=");
                //map.put(entry[0].trim(), entry[1].trim());
                //System.out.println(entry[0]+"="+entry[1]);

            //}

            list.add(map);

        }

        SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image", "Liseret"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort, R.id.color_poisson_peril});

        listView.setAdapter(sadapter);

    }



        /*Bac = intent.getStringExtra("Bac");
        main_user = intent.getStringExtra("main_user");
        Lot=intent.getStringExtra("Lot");
        Lignee=intent.getStringExtra("Lignee");
        Age=intent.getStringExtra("Age");
        Responsable=intent.getStringExtra("Responsable");
        Key=intent.getStringExtra("Key");*/

//ajout
        
        
        /*textViewLot=findViewById(R.id.tv_lot);
        textViewBac=findViewById(R.id.tv_Bac);
        textViewLignee=findViewById(R.id.tv_lignee);
        textViewAge=findViewById(R.id.tv_age);
        textViewResponsable=findViewById(R.id.tv_responsable);
        
        
        textViewLot.setText(Lot);
        textViewBac.setText(Bac);
        textViewLignee.setText(Lignee);
        textViewAge.setText(Age);
        textViewResponsable.setText(Responsable);*/
        

    public void lancermenu(View view) {
        
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    /**
     * @param view
     */
    public void lancersauvegarde(View view) {

        loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);

        final String PoissonMort=PoissonMortSpinner.getSelectedItem().toString();

        System.out.println("Sauvegarde lancé __________="+list_poisson);

        for (int i=0; i<list_poisson.size();i++){

           // Map<String, String> map = list_poissons_select.get(i);

           // for (Map.Entry entry : map.entrySet()){
             //   if (entry.getKey().equals("Bac")){ this.Bac= (String) entry.getValue(); }
               // if (entry.getKey().equals("Lignee")){ this.Lignee= (String) entry.getValue(); }
               // if (entry.getKey().equals("Responsable")){ this.Responsable= (String) entry.getValue(); }
               // if (entry.getKey().equals("Lot")){ this.Lot= (String) entry.getValue(); }
               // if (entry.getKey().equals("Age")){ this.Age= (String) entry.getValue(); }
               // if (entry.getKey().equals("Key")){ this.Key= (String) entry.getValue(); }
            //}

            WriteOnSheetDeclarerMort.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, PoissonMort, Accouplement, ControleSanitaire, Key);


            //Temps d'attente !!! IMPORTANT
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loading.dismiss();

        }

        Intent intent=new Intent(this, ActivityRechercheRegistreMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    
    public void fermeractivite(View view) {
        this.finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    
    }
    

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cbAccouplement:
                if (checked)
                    Accouplement = "1";
                else
                    Accouplement = "";
                break;
            case R.id.cbControleSanitaire:
                if (checked)
                    ControleSanitaire = "1";
                else
                    ControleSanitaire = "";
                break;
        }
    
}

}
