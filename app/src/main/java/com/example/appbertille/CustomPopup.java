package com.example.appbertille;

import com.android.volley.Request;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;


public class CustomPopup extends AppCompatActivity  {

    String main_user;
    String Key="";


    private String title;
    private String sub_title;
    private Button yesButton, noButton;
    private TextView titleView, subTitleView;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_popop_template);
        this.title = "Supprimer";
        this.sub_title="Souhaitez-vous supprimer cet élément ?";
        this.yesButton=findViewById(R.id.yes);
        this.noButton=findViewById(R.id.no);
        this.titleView=findViewById(R.id.title);
        this.subTitleView=findViewById(R.id.subTitle);

        titleView.setText(title);
        subTitleView.setText(sub_title);


        // Get the transferred data from source activity.
        Intent intent = getIntent();

        main_user=intent.getStringExtra("main_user");
        Key=intent.getStringExtra("Key");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbz0bG0rRxAk4FQXSG1dF0Acex0wO5YojMaa72QIBFnTO8BVq_J8yt0VY0jPD4fE6__D/exec?action=delItem&Key="+ Key, new Response.Listener<String>() {
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
        WriteOnSheetDeclarerMort.deleteData(this, Key);
        Intent intent=new Intent(this, ActivityHistoriqueMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void annule_delete(View view) {
        Intent intent=new Intent(this, ActivityHistoriqueMorts.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    public void setTitle(String title){this.title=title;}

    public void setSub_title(String sub_title){
        this.sub_title=sub_title;
    }

    public Button getYesButton(){
        return yesButton;
    }

    public Button getNoButton(){
        return noButton;
    }

}
