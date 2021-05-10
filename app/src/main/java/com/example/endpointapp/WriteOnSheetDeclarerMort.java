package com.example.endpointapp;

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

public class WriteOnSheetDeclarerMort {
    
    public static void writeData(final Context context, final String operateur, final String Bac, final String Lignee, final String Lot, final String Age, final String Responsable, final String PoissonMort, final String Accouplement, final String ControleSanitaire, final String Key) {
    
    
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzmNfdy1LMRBRmlJ_sG8SsaAqktjs_N1cLYanWcXuhp-x21moAGScVUfvd9qYVchQBN/exec?action=addItem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    
                    
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    
                    
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                
                    }
                }
        ) {
            /**
             * @return /**
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                parmas.put("Operateur", operateur);
                parmas.put("Bac", Bac);
                parmas.put("Lignee", Lignee);
                parmas.put("Lot", Lot);
                parmas.put("Age", Age);
                parmas.put("Responsable", Responsable);
                parmas.put("PoissonMort", PoissonMort);
                parmas.put("Accouplement", Accouplement);
                parmas.put("ControleSanitaire", ControleSanitaire);
                parmas.put("Key", Key);
                //....
        
                return parmas;
            }
        };
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


    public static void deleteData(final Context context, final String Key) {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbziuP47HUKzO8C9SmteIivluWBMmT4xNTGnRRRssjY8g7WhQ4uPTrf4mKnxou1saQI3/exec?action=delItem&Key="+Key,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            /**
             * @return /**
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                parmas.put("Key", Key);
                //....

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