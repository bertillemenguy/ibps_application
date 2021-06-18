package com.example.appbertille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityEcrirRecapSouffrancePoisson extends AppCompatActivity {

    String main_user="";
    Button Valider;
    TextView textView;
    String num, nb_poisson_mort;


    String Bac="";
    String Lignee="";
    String Lot="";
    String Age="";
    String Responsable="";
    String Key="";

    String Id;

    Integer Icon , Liseret;

    String Euthanasie="0";
    String Surveillance="0";
    String Isolement="0";
    String Ras="0";
    int PointLimite=3;

    String Position="0";
    String Nage="0";
    String Malnutrition="0";
    String Prostration="0";

    String Nageoire = "0";
    String Maigreur = "0";
    String Obesite = "0";
    String Blessure = "0";
    String Ulcere="0";
    String Scoliose="0";
    String Exophtalmie="0";
    String Opercules="0";
    String Couleur="0";

    Poisson poisson;

    ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_poisson);
        Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");
        num = intent.getStringExtra("num");
        nb_poisson_mort = intent.getStringExtra("nb_poisson_mort");
        Id = intent.getStringExtra("Id");

        Valider = findViewById(R.id.btn_valider);
        textView = findViewById(R.id.numero_poisson);

        Intent intent_2=getIntent();
        // récupération des poissons séléctionnés
        Bundle extra = getIntent().getBundleExtra("extra");

        poisson = (Poisson) extra.getSerializable("poisson");

        listView = findViewById(R.id.list_item_select);

        List<HashMap<String, String>> list = new ArrayList<>();

        Bac = poisson.getBac();
        Lot = poisson.getLot();
        Lignee = poisson.getLignee();
        Age = poisson.getAge();
        Responsable=poisson.getResponsable();
        Key=poisson.getKey();
        Icon=poisson.getImage();
        Liseret=poisson.getColor();

        HashMap<String,String> map= new HashMap<>();

        map.put("Bac", Bac);
        map.put("Lot", Lot);
        map.put("Lignee", Lignee);
        map.put("Age", Age);
        map.put("Responsable", Responsable);
        map.put("Key", Key);
        map.put("Image", Icon+"");
        map.put("Liseret", Liseret +"");

        list.add(map);

        SimpleAdapter sadapter =new SimpleAdapter(this, list, R.layout.list_item_registre, new String[]{"Bac", "Lot", "Lignee", "Age", "Responsable", "Image", "Liseret"}, new int[]{R.id.tv_bac, R.id.tv_lot, R.id.tv_lignee, R.id.tv_age, R.id.tv_responsable, R.id.icon_mort, R.id.color_poisson_peril});

        listView.setAdapter(sadapter);


        textView.setText("Entrer données poisson n°"+num);

        this.Valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Coucou bouton fonctionne");
                lancerresultat(v);
                fermeractivite(v);
            }
        });

    }

    public void fermeractivite(View view) {
        this.finish();
    }


    public void lancerresultat(View view) {
        //Intent intent=new Intent();
        int score=Integer.parseInt(Position) + Integer.parseInt(Nage) + Integer.parseInt(Malnutrition) + Integer.parseInt(Prostration) + Integer.parseInt(Nageoire) + Integer.parseInt(Maigreur) + Integer.parseInt(Obesite) + Integer.parseInt(Blessure) + Integer.parseInt(Ulcere) + Integer.parseInt(Scoliose) + Integer.parseInt(Exophtalmie) + Integer.parseInt(Opercules) + Integer.parseInt(Couleur);
        if (score >= PointLimite) {
            //intent=new Intent(this, ActivityEuthanasie.class);
            Toast.makeText(getApplicationContext(), "Euthanasie", Toast.LENGTH_LONG).show();
            Euthanasie="1";
        } else if ((score < PointLimite) && (Ulcere.equals("3"))) {
            //intent=new Intent(this, ActivityIsolement.class);
            Toast.makeText(getApplicationContext(), "Isolement", Toast.LENGTH_LONG).show();
            Isolement="1";
        } else if ((score + 1) < PointLimite) {
            //intent = new Intent(this, ActivityRas.class);
            Toast.makeText(getApplicationContext(), "Ras", Toast.LENGTH_LONG).show();
            Ras = "1";
        } else if (score == (PointLimite - 1)) {
            //intent=new Intent(this, ActivitySurveillance.class);
            Toast.makeText(getApplicationContext(), "Surveillance", Toast.LENGTH_LONG).show();
            Surveillance="1";
        }


        /*intent.putExtra("main_user", main_user);
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        intent.putExtra("Key", Key);
        // intent.putExtra("PointLimite", PointLimite);
        intent.putExtra("Euthanasie", Euthanasie);
        intent.putExtra("Isolement", Isolement);
        intent.putExtra("Surveillance", Surveillance);
        intent.putExtra("Ras", Ras);
        //ajout
        intent.putExtra("Position", Position);
        intent.putExtra("Nage", Nage);
        intent.putExtra("Malnutrition", Malnutrition);
        intent.putExtra("Prostration", Prostration);*/

        //startActivity(intent);
       WriteOnSheetSouffrance.writeData(this, main_user, Bac, Lignee, Lot, Age, Responsable, Position, Nage, Malnutrition, Prostration, Nageoire, Maigreur, Obesite, Blessure, Ulcere, Scoliose, Exophtalmie, Opercules, Couleur, Euthanasie, Isolement, Surveillance, Ras, nb_poisson_mort, Key, Id);

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkboxalterationnageoire:
                if (checked)
                    Nageoire = "1";
                else
                    Nageoire = "";
                break;
            case R.id.checkboxmaigreur:
                if (checked)
                    Maigreur = "1";
                else
                    Maigreur = "";
                break;
            case R.id.checkboxobesite:
                if (checked)
                    Obesite = "1";
                else
                    Obesite = "";
                break;
            case R.id.checkboxblessurelesion:
                if (checked)
                    Blessure = "1";
                else
                    Blessure = "";
                break;
            case R.id.checkboxulceremycosesaignement:
                if (checked)
                    Ulcere = "3";
                else
                    Ulcere = "";
                break;
            case R.id.checkboxscoliose:
                if (checked)
                    Scoliose = "1";
                else
                    Scoliose = "";
                break;
            case R.id.checkboxexophtalmiedeformation:
                if (checked)
                    Exophtalmie = "1";
                else
                    Exophtalmie = "";
                break;
            case R.id.checkboxoperculeouverte:
                if (checked)
                    Opercules = "1";
                else
                    Opercules = "";
                break;
            case R.id.checkboxchangementdecouleur:
                if (checked)
                    Couleur = "1";
                else
                    Couleur = "";

                //ajout
            case R.id.checkboxpositioninhabituelle:
                if (checked)
                    Position = "1";
                else
                    Position = "";
                break;
            case R.id.checkboxmalnutrition:
                if (checked)
                    Malnutrition = "1";
                else
                    Malnutrition = "";
                break;
            case R.id.checkboxnageanormale:
                if (checked)
                    Nage = "1";
                else
                    Nage = "";
                break;
            case R.id.checkboxprostration:
                if (checked)
                    Prostration = "1";
                else
                    Prostration = "";
                break;
        }
    }


}