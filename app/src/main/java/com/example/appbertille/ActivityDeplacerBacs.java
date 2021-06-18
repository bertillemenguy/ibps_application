

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


public class ActivityDeplacerBacs extends AppCompatActivity {


    String main_user=" ";
    String Date=" ";
    String Bac=" ";
    String Lot=" ";
    String Lignee=" ";
    String Age=" ";
    String Action="";
    String Bac2="";
    String Lignee2="";
    String Lot2="";
    String Key2="";
    String Key="";
    // String NouveauBac = "";
    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;

    ListView listView;


    Spinner SpinnerAlphabet;
    Spinner SpinnerNombre;

    //recupérer list poisson selctionné
    ArrayList<Poisson> list_poisson;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deplacer_reunir);

        listView = findViewById(R.id.list_item_select);


        Intent intent=getIntent();

        main_user=intent.getStringExtra("main_user");

        //Bac=intent.getStringExtra("Bac");
        main_user=intent.getStringExtra("main_user");
        //Age=intent.getStringExtra("Age");
        //Lignee=intent.getStringExtra("Lignee");
        //Lot=intent.getStringExtra("Lot");
        //Date=intent.getStringExtra("Date");
        
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




        ArrayList<HashMap<String, String>> list= new ArrayList<>();

            Poisson poisson= list_poisson.get(0);

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


            list.add(map);

            SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort});

            listView.setAdapter(sadapter);


    }
    
    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void enregistrerdeplacement(View view) {
        final String Nombre = SpinnerNombre.getSelectedItem().toString();
        final String Lettre = SpinnerAlphabet.getSelectedItem().toString();
        final String NouveauBac = Lettre + Nombre;
        Action="Déplacé";
    
    
        WriteOnSheetDeplacerEliminerErreurReunir.writeData(this, main_user, NouveauBac, Action, list_poisson.get(0).getBac(), list_poisson.get(0).getLignee(), list_poisson.get(0).getLot(), Bac2, Lignee2, Lot2, list_poisson.get(0).getKey(), Key2);

                                                //             context,   main_user,  nouveaubac,  elimine,     bac,                            lignee,                            lot,      bac2, lignee2,  lot2,       Key                   Key2

        Intent intent = new Intent(this, ActivityEcrirRecapBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    public void fermeractivite(View view) {
        this.finish();
    }


}
