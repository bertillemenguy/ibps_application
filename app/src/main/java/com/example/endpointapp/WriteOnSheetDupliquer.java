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

public class WriteOnSheetDupliquer {
    
    
    public static void writeData(final Context context, final String operateur, final String nouveaubac, final String elimine, final String bac, final String lignee, final String lot, final String bac2, final String lignee2, final String lot2, final String remarqueaa, final String remarquea, final String remarqueb, final String remarquec, final String nouveaubac2, final String nouveaubac3, final String nouveaubac4) {
        
        
        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
        
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzF4sjRlHvt-kN5dswL_kVhUrKY2RXTGDyYkupX_UuYL3AQNEY/exec?action=addItem",
        
        
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
                
                
                parmas.put("operateur", operateur);
                parmas.put("nouveaubac", nouveaubac);
                parmas.put("elimine", elimine);
                parmas.put("bac", bac);
                parmas.put("lignee", lignee);
                parmas.put("lot", lot);
                
                parmas.put("bac2", bac2);
                parmas.put("lignee2", lignee2);
                parmas.put("lot2", lot2);
                
                parmas.put("remarqueaa", remarqueaa);
                parmas.put("remarquea", remarquea);
                parmas.put("remarqueb", remarqueb);
                parmas.put("remarquec", remarquec);
                parmas.put("nouveaubac2", nouveaubac2);
                parmas.put("nouveaubac3", nouveaubac3);
                parmas.put("nouveaubac4", nouveaubac4);
                
                // parmas.put("remarqued", remarqued);
                /**
                 
                 parmas.put("NouveauBac2", nouveauBac2);
                 parmas.put("NouveauBac3", nouveauBac3);
                 */
                
                
                //
                
                
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

