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

public class WriteOnSheetOeuf {
    
    public static void writeData(final Context context, final String main_user, final String Qualite, final String Quantite, final String NbBac, final String NbMale, final String NbFemelle, final String Bac, final String Bac2, final String LigneeM, final String LigneeF, final String Age, final String Age2, final String Lot, final String Lot2, final String Date, final String NbMalesFeconde, final String NbfemellesFeconde, final String Key, final String Key2) {
        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");
    
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwCSW2TM1e-Lj3yOj9D4iho1EwAVmMduVArtoUixBlaX-CHy0ktbQT3PC2vZFGhfksy/exec?action=addItem",
            
            
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
                parmas.put("NbMale", NbMale);
                parmas.put("NbFemelle", NbFemelle);
    
                parmas.put("Bac", Bac);
                parmas.put("Bac2", Bac2);
                parmas.put("LigneeM", LigneeM);
                parmas.put("LigneeF", LigneeF);
                parmas.put("Age", Age);
                parmas.put("Age2", Age2);
                parmas.put("Qualite", Qualite);
                parmas.put("Quantite", Quantite);
                parmas.put("Lot", Lot);
                parmas.put("Lot2", Lot2);
                parmas.put("Key", Key);
                parmas.put("Key2", Key2);
//ajout
    
                parmas.put("main_user", main_user);
                parmas.put("Date", Date);
    
                parmas.put("NbMalesFeconde", NbMalesFeconde);
                parmas.put("NbfemellesFeconde", NbfemellesFeconde);
    
    
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
        final ProgressDialog loading=ProgressDialog.show(context, "Chargement...", "Veuillez patienter");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyhN7038mZ0vJbb8fIjRpQwFGF9o5dPo9lY15AoExiQUv0V484pDen8Qbb9VQ--Va5_/exec?action=delItem&Id="+Id,


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
    
}
