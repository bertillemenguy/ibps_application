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

public class WriteOnSheetIncident {


    public static void updateData(final Context context, final String key) {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxMbO3DtbldX1Z4wb2rHpH0ISAMlm0HaPkdXTh0T-sGbodva_TupBC6tf_2gWOdbjEnmw/exec?action=updateItem&key"+key,
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
                parmas.put("key", key);
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