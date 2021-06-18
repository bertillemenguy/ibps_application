package com.example.appbertille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityDupliquerBacs extends AppCompatActivity {


    // élément checkbox
    List<Poisson> list_select;
    PoissonAdapter adapter_poisson;

    EditText remarquea;
    EditText remarqueb;
    EditText remarquec;
    EditText remarqued;
    
    String main_user="";
    String Date="";
    String Bac="";
    String Lot="";
    String Lignee="";
    String Age="";
    String Action="";
    String Bac2="";
    String Lignee2="";
    String Lot2="";
    String NouveauBac="";
    String operateur;

    TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
    
    Spinner SpinnerAlphabet;
    Spinner SpinnerAlphabet2;
    Spinner SpinnerAlphabet3;
    Spinner SpinnerAlphabet4;
    
    Spinner SpinnerNombre;
    Spinner SpinnerNombre2;
    Spinner SpinnerNombre3;
    Spinner SpinnerNombre4;

    ImageView icon_mort;

    TextView tv_Lignee, tv_Lot, tv_Bac, tv_Responsable, tv_Age;
    
    //  Spinner SpinnerAlphabet;
    //   Spinner SpinnerNombre;
    
    
    /**
     * // String remarqueaa="";
     * String NouveauBac1="";
     * String NouveauBac2="";
     * String NouveauBac3="";
     * String NouveauBac4="";
     * <p>
     * String operateur=" ";
     * String Date=" ";
     * String Bac=" ";
     * String Lot=" ";
     * String Lignee=" ";
     * String Age=" ";
     * String Elimine="";
     * String Bac2="";
     * String Lignee2="";
     * String Lot2="";
     * String NouveauBac="";
     * TextView textViewitemName, textViewBac, textViewprice, textViewAge, textViewResponsable;
     */

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dupliquer);



        Intent intent=getIntent();
        main_user=intent.getStringExtra("main_user");

        
        //   String remarquea= remarquea.getText().toString();
        
        //    remarquea = (EditText) findViewById(R.id.remarquea);
        //remarquea=findViewById(R.id.remarquea);
        
        remarquea=(EditText) findViewById(R.id.remarquea);
        remarqueb=(EditText) findViewById(R.id.remarque2);
        remarquec=(EditText) findViewById(R.id.remarquec);
        remarqued=(EditText) findViewById(R.id.remarqued);


        // récupération des poissons séléctionnés
        Bundle extra = getIntent().getBundleExtra("extra");

        list_select = (ArrayList<Poisson>) extra.getSerializable("list_select");

        Bac=list_select.get(0).getBac();
        Age=list_select.get(0).getAge();
        Lignee=list_select.get(0).getLignee();
        Lot=list_select.get(0).getLot();
        Date=list_select.get(0).getAge();
        operateur=list_select.get(0).getResponsable();


        icon_mort=findViewById(R.id.icon_mort);
        tv_Lignee = findViewById(R.id.tv_lignee);
        tv_Lot= findViewById(R.id.tv_lot);
        tv_Bac= findViewById(R.id.tv_bac);
        tv_Responsable=findViewById(R.id.tv_responsable);
        tv_Age= findViewById(R.id.tv_age);

        tv_Age.setText(Age);
        tv_Lot.setText(Lot);
        tv_Bac.setText(Bac);
        tv_Responsable.setText(operateur);
        tv_Lignee.setText(Lignee);

        if (Integer.parseInt(Age)==22){
            icon_mort.setImageResource(icon[0]);
        } else {
            icon_mort.setImageResource(icon[1]);
        }



        SpinnerAlphabet=findViewById(R.id.spinnera);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet.setAdapter(adapter);
        SpinnerNombre=findViewById(R.id.spinnerb);
        ArrayAdapter<CharSequence> adapter21=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre.setAdapter(adapter21);
        
        
        SpinnerAlphabet2=findViewById(R.id.spinnerc);
        ArrayAdapter<CharSequence> adapter20=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet2.setAdapter(adapter20);
        SpinnerNombre2=findViewById(R.id.spinnerd);
        ArrayAdapter<CharSequence> adapter23=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre2.setAdapter(adapter23);
        
        
        SpinnerAlphabet3=findViewById(R.id.spinnere);
        ArrayAdapter<CharSequence> adapter30=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet3.setAdapter(adapter30);
        SpinnerNombre3=findViewById(R.id.spinnerf);
        ArrayAdapter<CharSequence> adapter40=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre3.setAdapter(adapter40);
        
        
        SpinnerAlphabet4=findViewById(R.id.spinnerg);
        ArrayAdapter<CharSequence> adapter50=ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerAlphabet4.setAdapter(adapter50);
        SpinnerNombre4=findViewById(R.id.spinnerh);
        ArrayAdapter<CharSequence> adapter60=ArrayAdapter.createFromResource(this, R.array.nombre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNombre4.setAdapter(adapter60);
        
    }
    
    public void lancermenu(View view) {
        Intent intent=new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    public void enregistrerdupliquer(View view) {
        
        
        final String Lettre=SpinnerAlphabet.getSelectedItem().toString();
        final String Nombre=SpinnerNombre.getSelectedItem().toString();
        
        final String Lettre2=SpinnerAlphabet2.getSelectedItem().toString();
        final String Nombre2=SpinnerNombre2.getSelectedItem().toString();
        
        final String Lettre3=SpinnerAlphabet3.getSelectedItem().toString();
        final String Nombre3=SpinnerNombre3.getSelectedItem().toString();
        
        final String Lettre4=SpinnerAlphabet4.getSelectedItem().toString();
        final String Nombre4=SpinnerNombre4.getSelectedItem().toString();
        
        
        final String NouveauBac=Lettre + Nombre;
        final String NouveauBac2=Lettre2 + Nombre2;
        final String NouveauBac3=Lettre3 + Nombre3;
        final String NouveauBac4=Lettre4 + Nombre4;
        
        
        final String RemarqueA=remarquea.getText().toString();
        final String RemarqueB=remarqueb.getText().toString();
        final String RemarqueC=remarquec.getText().toString();
        final String RemarqueD=remarqued.getText().toString();
        //final String remarqued=remarqued.getText().toString();
    
        Action="Dupliquer";
    
        WriteOnSheetDupliquer.writeData(this, main_user, NouveauBac, Action, list_select.get(0).getBac(), list_select.get(0).getLignee(), list_select.get(0).getLot(), Bac2, Lignee2, Lot2, RemarqueA, RemarqueB, RemarqueC, RemarqueD, NouveauBac2, NouveauBac3, NouveauBac4);
        
        Intent intent=new Intent(this, ActivityEcrirRecapBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }
    
    //private String getRemarquea(String remarquea) {
    // return remarquea;

    public void fermeractivite(View view) {
        this.finish();
    }


}


