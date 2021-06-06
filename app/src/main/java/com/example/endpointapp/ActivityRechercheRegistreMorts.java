
package com.example.endpointapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.graphics.pdf.PdfDocument;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.pm.PackageManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ActivityRechercheRegistreMorts extends AppCompatActivity implements Serializable{


    // élément checkbox
    Intent intent_2 ;
    List<Poisson> list_select;
    PoissonAdapter adapter_poisson;
    Button button;

    String main_user;

    ListView listView;

    ProgressDialog loading;
    EditText editTextSearchItem;

    int[] icon ={R.drawable.fish_bones, R.drawable.zebrafish};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_registre_morts_item);

        listView = findViewById(R.id.lv_items);

        //  listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        //  listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        button = findViewById(R.id.btn_valider);
        editTextSearchItem = findViewById(R.id.et_search);

        // Get the transferred data from source activity.
        final Intent intent = getIntent();
        main_user = intent.getStringExtra("main_user");


        /*this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                System.out.println(listView.getItemAtPosition(position));
                //user.setActive(!currentCheck);
            }
        });*/


        //élément checkbox
        intent_2 = new Intent(this, ActivityEcrirRecapMort.class);
        getItems();

    }



    private void getItems() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwqCJJWqCBXyVIatCzuiST50A0_kcxPXL-GH9BEYtBfk6y-aEHX/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    
                    }
                }
        );
        loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    
        stringRequest.setRetryPolicy(policy);
    
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    
    }


    /**
     * @param jsonResponce
     */
    private void parseItems(String jsonResponce) {

        List<Poisson> data = new ArrayList<>();

        final ArrayList<HashMap<String, String>> list = new ArrayList<>();


        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");

            for (int i = 0; i < jarray.length(); i++) {
                
                JSONObject jo = jarray.getJSONObject(i);
                
                String Bac = jo.getString("Bac");
                String Lot = jo.getString("Lot");
                String Lignee = jo.getString("Lignee");
                String Age = jo.getString("Age");
                String Responsable=jo.getString("Responsable");
                String Key=jo.getString("Key");

                Integer Image;

                if (Integer.parseInt(Age)==22){
                    Image=icon[0];
                } else {
                    Image=icon[1];
                }

                Integer Color;
                if (Integer.parseInt(Age)==22){
                    Color= R.drawable.liseret_rouge;
                } else {
                    Color=0;
                }


                data.add(new Poisson(Lot,Bac,Responsable,Lignee,Age,Key,Image,Color));


                /*
                HashMap<String, String> item = new HashMap<>();

                item.put("Bac", Bac);
                item.put("Lot", Lot);
                item.put("Lignee", Lignee);
                item.put("Age", Age);
                item.put("Responsable", Responsable);
                item.put("Key", Key);

                list.add(item);
                */
            }

            System.out.println(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

      /*  adapter=new ArrayAdapter(this, R.layout.simple_list_item_checked, list);

        listView.setAdapter(adapter);
*/

        adapter_poisson=new PoissonAdapter(this, data);
        listView.setAdapter(adapter_poisson);

        Log.e("APPLI", "je suis ici et je fais ça");
        // clics sur les éléments, on les envoie à l'adaptateur
        listView.setOnItemClickListener((adapterView, view, pos, l) -> adapter_poisson.toggle(pos));

        Log.e("APPLI", "Apres");

        loading.dismiss();

        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActivityRechercheRegistreMorts.this.adapter_poisson.getFilter().filter(charSequence);
            }
            
            @Override
            public void afterTextChanged(Editable editable) { }
        });


        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Bouton cliqué___________________");
                System.out.println(adapter_poisson.getSelected());
                list_select = adapter_poisson.getSelected();
                System.out.println(list_select);
                intent_2.putExtra("main_user", main_user);
                Bundle extra = new Bundle();
                extra.putSerializable("list_select", (Serializable) list_select);
                intent_2.putExtra("extra", extra);

                //intent_2.putExtra("list_select", (Serializable) list_select);

                startActivity(intent_2);

               // Toast.makeText(this, "Vous avez selectionné  "+list_select.size()+" élément(s)", Toast.LENGTH_LONG).show();

            }
        });
    }


    /**
     //* @param parent
     //* @param view
     //* @param position
     //* @param id
     */

    /*-----------------------------SELECTIONNER POISSON(S)-------------------------------*/
    /*-----------------------------------------------------------------------------------*/


    /*public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Intent intent = new Intent(this, ActivityItemdetails.class);
        Intent intent = new Intent(this, ActivityEcrirRecapMort.class);
        HashMap map = (HashMap) parent.getItemAtPosition(position);
        
        String Bac = map.get("Bac").toString();
        String Lot = map.get("Lot").toString();
        String Lignee = map.get("Lignee").toString();
        String Age = map.get("Age").toString();
        String Responsable = map.get("Responsable").toString();
        String Key = map.get("Key").toString();
        
        
        intent.putExtra("Bac", Bac);
        intent.putExtra("Lot", Lot);
        intent.putExtra("Lignee", Lignee);
        intent.putExtra("Age", Age);
        intent.putExtra("Responsable", Responsable);
        intent.putExtra("Key", Key);

        intent.putExtra("main_user", main_user);


        startActivity(intent);
    }*/
    
    
    public void lancermenu(View view) {
        
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void lancertrie(View view) {
        Intent intent = new Intent(this, ActivityRechercheRegistreMortsTrier.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }


    public void lancerPDF(View view){

        Toast.makeText(getApplicationContext(), "Chargement ... ", Toast.LENGTH_SHORT).show();

        SimpleDateFormat formater = null;

        Date aujourdhui = new Date();

        formater = new SimpleDateFormat("dd-MM-yy_hh-mm-ss");
        String date = formater.format(aujourdhui);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Canvas canvas = myPage.getCanvas();

        canvas.drawText("Coucou", 40, 50, myPaint);
        myPdfDocument.finishPage(myPage);

        File file = new File(Environment.getExternalStorageDirectory(), "/Registre_morts"+date+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e){
            e.printStackTrace();
        }

        myPdfDocument.close();
        Toast.makeText(getApplicationContext(), "Pdf enregistré dans Téléchargements", Toast.LENGTH_SHORT).show();

    }


    /*public ArrayList getSelectedItems()  {

        ArrayList<String> list = new ArrayList<>();
        int nb = 0;

        SparseBooleanArray sp = listView.getCheckedItemPositions();
        StringBuilder sb= new StringBuilder();

        int i;

        for(i=0;i<sp.size();i++){
            if(sp.valueAt(i)){
                String s = ((CheckedTextView) listView.getChildAt(sp.keyAt(i))).getText().toString();
                list.add(s);
                sb = sb.append(" ");
                sb = sb.append(s);
                nb++;
            }
        }

        Toast.makeText(this, "Vous avez selectionné  "+nb+" élément(s)", Toast.LENGTH_LONG).show();

        return list;
    }*/

    public void onPointerCaptureChanged(boolean hasCapture) {
    
    }



}
