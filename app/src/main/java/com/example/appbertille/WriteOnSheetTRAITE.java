package com.example.appbertille;

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

public class WriteOnSheetTRAITE {
    
    
    public static void writeData(final Context context, final String operateur, final String elimine) {
        final ProgressDialog loading = ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
        
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzXCR18WhMEzkCHUMwXp9QsV3d40h5RZ3w0w3cEmnVSpnAcE0Ee/exec?action=addItem",
        
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
                
                
                parmas.put("operateur", operateur);
                
                parmas.put("elimine", elimine);
                
                
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
