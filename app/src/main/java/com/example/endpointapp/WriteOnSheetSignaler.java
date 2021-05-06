package com.example.endpointapp;

import android.app.ProgressDialog;
import android.content.Context;
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

public class WriteOnSheetSignaler {
    
    public static void writeData(final Context context, final String operateur, final String eaudeville, final String electricite, final String aircomprime, final String climatisation, final String eaudusysteme, final String systemeaquatique, final String travaux, final String nourrissage, final String etat) {
        
        
        //final String CoupureEau, final String Panne, final String CoupureProg, final String CoupureInop, final String ArretAir, final String ArretCTA, final String PH, final String Conductivite, final String NO2, final String NO3, final String NH4, final String Temperature, final String ArretSysteme, final String Fuite, final String UV, final String Autre, final String Vibrations, final String Divers) {
        final ProgressDialog loading = ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
        
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxMbO3DtbldX1Z4wb2rHpH0ISAMlm0HaPkdXTh0T-sGbodva_TupBC6tf_2gWOdbjEnmw/exec?action=addItem",
        
        
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
                Map<String, String> parmas = new HashMap<>();
                parmas.put("eaudeville", eaudeville);
                parmas.put("operateur", operateur);
                parmas.put("electricite", electricite);
                parmas.put("aircomprime", aircomprime);
                parmas.put("climatisation", climatisation);
                parmas.put("eaudusysteme", eaudusysteme);
                parmas.put("systemeaquatique", systemeaquatique);
                parmas.put("travaux", travaux);
                parmas.put("nourrissage", nourrissage);
                parmas.put("etat", etat);


                /**
     
                 parmas.put("CoupureEau", CoupureEau);
                 parmas.put("Panne", Panne);
                 parmas.put("CoupureProg", CoupureProg);
                 parmas.put("CoupureInop", CoupureInop);
                 parmas.put("ArretAir", ArretAir);
                 parmas.put("ArretCTA", ArretCTA);
                 parmas.put("PH", PH);
                 parmas.put("Conductivite", Conductivite);
                 parmas.put("NO2", NO2);
                 parmas.put("NO3", NO3);
                 parmas.put("NH4", NH4);
                 parmas.put("Temperature", Temperature);
                 parmas.put("ArretSysteme", ArretSysteme);
                 parmas.put("Fuite", Fuite);
                 parmas.put("UV", UV);
                 parmas.put("Autre", Autre);
                 parmas.put("Vibrations", Vibrations);
                 parmas.put("Divers", Divers);
     
                 //....
                 */
                return parmas;
            }
        };
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
    
}
