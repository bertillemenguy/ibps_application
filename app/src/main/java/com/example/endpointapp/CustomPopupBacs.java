package com.example.endpointapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class CustomPopupBacs extends AppCompatActivity  {

    String ID="";
    String main_user="";


    private String title;
    private String sub_title;
    private Button yesButton, noButton;
    private TextView titleView, subTitleView;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_popop_template);
        this.title = "Bacs traité";
        this.sub_title="Souhaitez-vous classé cet élément comme traité ?";
        this.yesButton=findViewById(R.id.yes);
        this.noButton=findViewById(R.id.no);
        this.titleView=findViewById(R.id.title);
        this.subTitleView=findViewById(R.id.subTitle);

        titleView.setText(title);
        subTitleView.setText(sub_title);



        // Get the transferred data from source activity.
        Intent intent = getIntent();
        main_user=intent.getStringExtra("main_user");

        ID=intent.getStringExtra("ID");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzCek4pEbkou34ITvWk50kh5m7z4Ye0jqtxF-xdVGp5J-0TVReSlSiRoxq3CKeQ-_YP/exec?action=updateItem&ID="+ ID, new Response.Listener<String>() {
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

    }


    public void lancersauvegarde_delete(View view) {
        WriteOnSheetBacsTraite.updateData(this, ID);
        Intent intent=new Intent(this, ActivityRechercheGestionBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void annule_delete(View view) {
        Intent intent=new Intent(this, ActivityRechercheGestionBacs.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }



}
