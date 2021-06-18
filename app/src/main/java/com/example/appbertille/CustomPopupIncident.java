package com.example.appbertille;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class CustomPopupIncident extends AppCompatActivity  implements Serializable {


    ArrayList<Incident> list_incident;


    String main_user="";

    private String title;
    private String sub_title;
    private Button yesButton, noButton;
    private TextView titleView, subTitleView;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_popop_template);
        this.title = "Incident traité";
        this.sub_title="Souhaitez-vous classé cet élément comme traité ?";
        this.yesButton=findViewById(R.id.yes);
        this.noButton=findViewById(R.id.no);
        this.titleView=findViewById(R.id.title);
        this.subTitleView=findViewById(R.id.subTitle);

        titleView.setText(title);
        subTitleView.setText(sub_title);



        Bundle extra = getIntent().getBundleExtra("extra");
        main_user = getIntent().getStringExtra("main_user");

        list_incident = (ArrayList<Incident>) extra.getSerializable("list_select");



/*            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwyusP2gz3dbU8nZPA67isdZAxP4Qpnr-2z9a1OieDXPZCP32sHV7UPvWn2OSMTCkfd-w/exec?action=updateItem&key="+ key, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            );



        }*/



    }


    public void lancersauvegarde_delete(View view) {


        for (int i=0; i<list_incident.size(); i++) {

            System.out.println("COUCOU valider:"+list_incident.get(i));

            String key = list_incident.get(i).getKey();
            WriteOnSheetIncident.updateData(this, key);


            //Temps d'attente !!! IMPORTANT
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            Intent intent = new Intent(this, ActivityHistoriqueIncidents.class);
            startActivity(intent);


    }


    public void annule_delete(View view) {
        Intent intent=new Intent(this, ActivityHistoriqueIncidents.class);
        startActivity(intent);
    }



}
