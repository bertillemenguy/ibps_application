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

public class WriteOnSheetAccouplement {
    
    public static void writeData(final Context context, final String operateur, final String NbBac, final String Couleur1, final String Couleur2, final String NbMale, final String NbFemelle, final String Lot, final String Lot2, final String Bac, final String Bac2, final String Lignee, final String Lignee2, final String Age, final String Age2, final String Responsable, final String Responsable2, final String Key, final String Key2) {
        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
    
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbz0u3IpxdQ5SoARPB8XWhNIDjUi5cxNtooDNNzI_vaYIqFHsd819aXFfjaTOfA0TXL1/exec?action=addItem",
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
                parmas.put("NbBac", NbBac);
                parmas.put("Couleur1", Couleur1);
                parmas.put("Couleur2", Couleur2);
                parmas.put("NbMale", NbMale);
                parmas.put("NbFemelle", NbFemelle);
                parmas.put("Lot", Lot);
                parmas.put("Lot2", Lot2);
                parmas.put("Bac", Bac);
                parmas.put("Bac2", Bac2);
                parmas.put("Lignee", Lignee);
                parmas.put("Lignee2", Lignee2);
                parmas.put("Age", Age);
                parmas.put("Age2", Age2);
                parmas.put("Responsable", Responsable);
                parmas.put("Responsable2", Responsable2);
    
                parmas.put("Key", Key);
                parmas.put("Key2", Key2);
                parmas.put("operateur", operateur);
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

    public static void deleteData(final Context context, String Key) {
        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");

        System.out.println(Key);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbypv9XsMlpxp9hQyHbLGP2VR-_b1udIv_Z5MWS72_mSes_t3Y591T7hkRDRIGUPKwUq/exec?action=delItem&key"+Key,
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
                parmas.put("key", Key);

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
