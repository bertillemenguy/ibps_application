package com.example.appbertille;

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

public class WriteOnSheetSouffrance {
    
    public static void writeData(final Context context, final String operateur, final String Bac, final String Lignee, final String Lot, final String Age, final String Responsable, final String Position, final String Nage, final String Malnutrition, final String Prostration, final String Nageoire, final String Maigreur, final String Obesite, final String Blessure, final String Ulcere, final String Scoliose, final String Exophtalmie, final String Opercules, final String Couleur, final String Euthanasie, final String Isolement, final String Surveillance, final String Ras, final String PoissonSouffrance, final String Key, final String Id) {
        //final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
    
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxV6lWAgamdYr8xAuZklYhFqbU7_gVQxqk4I8zlQ14ZTorBBUGwYgQwkgkFcxdpJzjA/exec?action=addItem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        //loading.dismiss();
                    
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.dismiss();
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                        
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas=new HashMap<>();
                parmas.put("Operateur", operateur);
                parmas.put("Bac", Bac);
                parmas.put("Lignee", Lignee);
                parmas.put("Lot", Lot);
                parmas.put("Age", Age);
                parmas.put("Responsable", Responsable);
                parmas.put("Id", Id);
                parmas.put("Key", Key);
    
    
                parmas.put("Position", Position);
                parmas.put("Nage", Nage);
                parmas.put("Malnutrition", Malnutrition);
                parmas.put("Prostration", Prostration);
                parmas.put("Nageoire", Nageoire);
                parmas.put("Maigreur", Maigreur);
                parmas.put("Obesite", Obesite);
                parmas.put("Blessure", Blessure);
                parmas.put("Ulcere", Ulcere);
                parmas.put("Scoliose", Scoliose);
                parmas.put("Exophtalmie", Exophtalmie);
                parmas.put("Opercules", Opercules);
                parmas.put("Couleur", Couleur);
                parmas.put("Euthanasie", Euthanasie);
                parmas.put("Isolement", Isolement);
                parmas.put("Surveillance", Surveillance);
                parmas.put("Ras", Ras);
                parmas.put("PoissonSouffrance", PoissonSouffrance);
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


    public static void deleteData(final Context context, final String Id) {
        //final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbweF8pUvnlDBMEIP1FGauf2UTa_0z1Lz2NxciascGU5lqQQfsaDT6NFAFJ0-v1ZifHS/exec?action=delItem&Id="+Id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        //loading.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.dismiss();
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas=new HashMap<>();

                parmas.put("Id", Id);

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



    public static void updateData(final Context context, final String Euthanasie, final String Isolement, final String Surveillance, final String Ras,final String Id) {
        //final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwPsX0DXicMd7gtQJOYku7aHnLs-s_lhnJVPF4RFt2Ov4lqEXg-jJY_OKzjiK9m39Ui/exec?action=updateItem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        //loading.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.dismiss();
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas=new HashMap<>();

                parmas.put("Id", Id);
                parmas.put("Euthanasie", Euthanasie);
                parmas.put("Isolement", Isolement);
                parmas.put("Surveillance", Surveillance);
                parmas.put("Ras", Ras);

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