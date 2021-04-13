package com.example.endpointapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;


public class ActivityEcrirRecapMort extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    
    Spinner PoissonMortSpinner;
    
    String Bac = "";
    String operateur = "";
    String Lignee="";
    String Lot="";
    String Age="";
    String Responsable="";
    String Key="";
    
    //String PointLimite;
    String Accouplement="";
    String ControleSanitaire="";
    
    
    TextView textViewLot, textViewBac, textViewLignee, textViewAge, textViewResponsable;
    
    
    Spinner OperateurSpinner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrir_recap_mort);
        
        // operateur
        OperateurSpinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.operateur, android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OperateurSpinner.setAdapter(adapter17);
        
        
        PoissonMortSpinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.chiffre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoissonMortSpinner.setAdapter(adapter);
        PoissonMortSpinner.setOnItemSelectedListener(this);
        
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        Bac = intent.getStringExtra("Bac");
        operateur = intent.getStringExtra("operateur");
        Lot=intent.getStringExtra("Lot");
        Lignee=intent.getStringExtra("Lignee");
        Age=intent.getStringExtra("Age");
        Responsable=intent.getStringExtra("Responsable");
        Key=intent.getStringExtra("Key");

//ajout
        
        
        textViewLot=findViewById(R.id.tv_lot);
        textViewBac=findViewById(R.id.tv_Bac);
        textViewLignee=findViewById(R.id.tv_lignee);
        textViewAge=findViewById(R.id.tv_age);
        textViewResponsable=findViewById(R.id.tv_responsable);
        
        
        textViewLot.setText(Lot);
        textViewBac.setText(Bac);
        textViewLignee.setText(Lignee);
        textViewAge.setText(Age);
        textViewResponsable.setText(Responsable);
        
        
    }
    
    
    public void lancermenu(View view) {
        
        Intent intent=new Intent(this, ActivityMenu.class);
        //   intent.putExtra("operateur", operateur);
        startActivity(intent);
    }
    
    /**
     * @param view
     */
    public void lancersauvegarde(View view) {
        
        final String operateur=OperateurSpinner.getSelectedItem().toString();
        final String PoissonMort=PoissonMortSpinner.getSelectedItem().toString();
        WriteOnSheetDeclarerMort.writeData(this, operateur, Bac, Lignee, Lot, Age, Responsable, PoissonMort, Accouplement, ControleSanitaire, Key);
        Intent intent=new Intent(this, ActivityRechercheRegistreMorts.class);
        // intent.putExtra("operateur", operateur);
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


