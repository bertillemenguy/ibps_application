package com.example.appbertille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ActivityReunificationBacs extends AppCompatActivity {

    String main_user=" ";
    String Date=" ";
    String Bac=" ";
    String Lot=" ";
    String Lignee=" ";
    
    String Action="";
    String Bac2="";
    String Lot2="";
    String Lignee2="";
    String Key2="";
    String Key="";
    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
    Spinner SpinnerAlphabet;
    Spinner SpinnerNombre;
    ListView listView;


    //recupérer list poisson selctionné
    ArrayList<Poisson> list_poisson;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deplacer_reunir);

        listView = findViewById(R.id.list_item_select);

        Intent intent=getIntent();
        //Bac=intent.getStringExtra("Bac");
        main_user=intent.getStringExtra("main_user");
        
        //Lignee=intent.getStringExtra("Lignee");
        //Lot=intent.getStringExtra("Lot");
        // Date = intent.getStringExtra("Date");
        //Bac2=intent.getStringExtra("Bac2");
        //Lignee2=intent.getStringExtra("Lignee2");
        //Lot2=intent.getStringExtra("Lot2");
        //Key2=intent.getStringExtra("Key2");
        //Key=intent.getStringExtra("Key");


        // récupération des poissons séléctionnés
        Bundle extra = getIntent().getBundleExtra("extra");
        list_poisson = (ArrayList<Poisson>) extra.getSerializable("list_select");


        SpinnerAlphabet=findViewById(R.id.spinnerb);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet.setAdapter(adapter);
        
        
        SpinnerNombre=findViewById(R.id.spinnerh);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre.setAdapter(adapter2);


        System.out.println("LISTE DANS REUNIFICATION"+ list_poisson);

        List<HashMap<String, String>> list = new ArrayList<>();


        for (int i =0; i<list_poisson.size(); i++){

            Poisson poisson= list_poisson.get(i);
            //String s = String.valueOf(list_select.get(i));
            //s=s.substring(1, s.length()-1);
            //String[] pairs = s.split(", ");

            HashMap<String, String> map = new HashMap<>();


            String Bac = poisson.getBac();
            String Lot = poisson.getLot();
            String Lignee = poisson.getLignee();
            String Age = poisson.getAge();
            String Responsable=poisson.getResponsable();
            String Key=poisson.getKey();
            String Image= String.valueOf(poisson.getImage());

            map.put("Bac", Bac);
            map.put("Lot", Lot);
            map.put("Lignee", Lignee);
            map.put("Age", Age);
            map.put("Responsable", Responsable);
            map.put("Key", Key);
            map.put("Image", Image+"");


            // for (String pair:pairs){

            //String[] entry = pair.split("=");
            //map.put(entry[0].trim(), entry[1].trim());
            //System.out.println(entry[0]+"="+entry[1]);

            //}

            list.add(map);

        }

        SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort});

        listView.setAdapter(sadapter);


    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void enregistrerdeplacement(View view) {
        final String Nombre=SpinnerNombre.getSelectedItem().toString();
        final String Lettre=SpinnerAlphabet.getSelectedItem().toString();
        final String NouveauBac=Lettre + Nombre;
        Action="Réunis";

        WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, main_user, NouveauBac, Action, list_poisson.get(0).getBac(), list_poisson.get(0).getLignee(), list_poisson.get(0).getKey(), list_poisson.get(1).getBac(), list_poisson.get(1).getLignee(), list_poisson.get(1).getLot(), list_poisson.get(0).getKey(), list_poisson.get(1).getKey());

        //Temps d'attente !!! IMPORTANT
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent=new Intent(this, ActivityEcrirRecapBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    public void fermeractivite(View view) {
        this.finish();
    }

}
