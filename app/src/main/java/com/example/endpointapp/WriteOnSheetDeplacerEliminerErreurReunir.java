package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class WriteOnSheetDeplacerEliminerErreurReunir {


    
    public static void writeData(final Context context, final String main_user, final String nouveaubac, final String elimine, final String bac, final String lignee, final String lot, final String bac2, final String lignee2, final String lot2, final String Key, final String Key2) {

        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
        
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzaowsqMi3ccMCJHN3hX-4h325-AwW3uVudSFphIkAXBAJTOfZwik3FutbuXB7YgLGN/exec?action=addItem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        
                        
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        loading.dismiss();
                        
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                        
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas=new HashMap<>();
                
                
                parmas.put("operateur", main_user);
                parmas.put("nouveaubac", nouveaubac);
                parmas.put("elimine", elimine);
                parmas.put("bac", bac);
                parmas.put("lignee", lignee);
                parmas.put("lot", lot);
                parmas.put("bac2", bac2);
                parmas.put("lignee2", lignee2);
                parmas.put("lot2", lot2);
                parmas.put("Key", Key);
                parmas.put("Key2", Key2);
                //....
                
                return parmas;
            }
        };
        int socketTimeOut=50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy=new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue=Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


    
}
