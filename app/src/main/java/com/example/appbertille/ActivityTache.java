package com.example.appbertille;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityTache extends AppCompatActivity implements View.OnClickListener{

    Button btn_lun, btn_mar, btn_mer, btn_jeu, btn_ven, btn_sam, btn_dim;

    String main_user = "";
    ProgressDialog loading;
    Calendar c;
    ListView lv;
    TextView tv;

    String j="";

    EditText edit_lundi, edit_mardi, edit_mercredi, edit_jeudi, edit_vendredi, edit_samedi, edit_dimanche;

    TextView commentaire_lundi, commentaire_mardi, commentaire_mercredi, commentaire_jeudi, commentaire_vendredi, commentaire_samedi, commentaire_dimanche;
    String comment_lundi, comment_mardi, comment_mercredi, comment_jeudi, comment_vendredi, comment_samedi, comment_dimanche;


    TextView jour_semaine, num_lundi, num_mardi, num_mercredi, num_jeudi, num_vendredi, num_samedi, num_dimanche;
    String n_lundi, n_mardi, n_mercredi, n_jeudi, n_vendredi, n_samedi, n_dimanche;

    TextView mois_lundi, mois_mardi, mois_mercredi, mois_jeudi, mois_vendredi, mois_samedi, mois_dimanche;
    String m_lundi, m_mardi, m_mercredi, m_jeudi, m_vendredi, m_samedi, m_dimanche;

    TextView compteur_lundi, compteur_mardi, compteur_mercredi, compteur_jeudi, compteur_vendredi, compteur_samedi, compteur_dimanche;
    String c_lundi, c_mardi, c_mercredi, c_jeudi, c_vendredi, c_samedi, c_dimanche;

    ArrayList<Tache> list_taches;
    Date date=null;

    TacheAdapter adapter_tache;

    AlertDialog.Builder alertDialog, alert_comment;
    AlertDialog dialog, dialog_comment;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taches);

        Intent intent = getIntent();
        this.main_user = intent.getStringExtra("main_user");

        btn_lun = (Button) findViewById(R.id.tache_lundi) ;
        btn_lun.setOnClickListener(this);

        btn_mar = (Button) findViewById(R.id.tache_mardi) ;
        btn_mar.setOnClickListener(this);

        btn_mer = (Button) findViewById(R.id.tache_mercredi) ;
        btn_mer.setOnClickListener(this);

        btn_jeu = (Button) findViewById(R.id.tache_jeudi) ;
        btn_jeu.setOnClickListener(this);

        btn_ven = (Button) findViewById(R.id.tache_vendredi) ;
        btn_ven.setOnClickListener(this);

        btn_sam = (Button) findViewById(R.id.tache_samedi) ;
        btn_sam.setOnClickListener(this);

        btn_dim = (Button) findViewById(R.id.tache_dimanche) ;
        btn_dim.setOnClickListener(this);

        Date date = new Date();
        SimpleDateFormat jour_format=new SimpleDateFormat("d", Locale.FRANCE);
        SimpleDateFormat mois_format=new SimpleDateFormat("MMM", Locale.FRANCE);
        SimpleDateFormat j_format=new SimpleDateFormat("EEEE", Locale.FRANCE);

        String jour = jour_format.format(date);
        String mois= mois_format.format(date);
        String j = j_format.format(date);

        if (j.equals("lundi")){
            this.n_lundi=jour;
            this.m_lundi=mois;

            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);
        }

        if (j.equals("mardi")){
            this.n_mardi=jour;
            this.m_mardi=mois;
            c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, -1);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 2);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);


            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);

        }

        if (j.equals("mercredi")){

            this.n_mercredi=jour;
            this.m_mercredi=mois;
            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -2);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 2);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);
        }

        if (j.equals("jeudi")){
            this.n_jeudi=jour;
            this.m_jeudi=mois;
            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -3);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);

            c.add(Calendar.DATE, 2);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);
        }


        if (j.equals("vendredi")){
            this.n_vendredi=jour;
            this.m_vendredi=mois;

            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -4);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 2);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);
        }

        if (j.equals("samedi")){
            this.n_samedi=jour;
            this.m_samedi=mois;

            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -5);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 2);
            date = c.getTime();
            this.n_dimanche=jour_format.format(date);
            this.m_dimanche=mois_format.format(date);
        }



        if (j.equals("dimanche")){
            this.n_dimanche=jour;
            this.m_dimanche=mois;

            c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -6);
            date = c.getTime();
            this.n_lundi=jour_format.format(date);
            this.m_lundi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mardi=jour_format.format(date);
            this.m_mardi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_mercredi=jour_format.format(date);
            this.m_mercredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_jeudi=jour_format.format(date);
            this.m_jeudi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_vendredi=jour_format.format(date);
            this.m_vendredi=mois_format.format(date);

            c.add(Calendar.DATE, 1);
            date = c.getTime();
            this.n_samedi=jour_format.format(date);
            this.m_samedi=mois_format.format(date);

        }


        this.commentaire_lundi=findViewById(R.id.commentaire_lundi);
        this.commentaire_mardi=findViewById(R.id.commentaire_mardi);
        this.commentaire_mercredi=findViewById(R.id.commentaire_mercredi);
        this.commentaire_jeudi=findViewById(R.id.commentaire_jeudi);
        this.commentaire_vendredi=findViewById(R.id.commentaire_vendredi);
        this.commentaire_samedi=findViewById(R.id.commentaire_samedi);
        this.commentaire_dimanche=findViewById(R.id.commentaire_dimanche);


        this.num_lundi = findViewById(R.id.num_lundi);
        this.num_mardi = findViewById(R.id.num_mardi);
        this.num_mercredi = findViewById(R.id.num_mercredi);
        this.num_jeudi = findViewById(R.id.num_jeudi);
        this.num_vendredi = findViewById(R.id.num_vendredi);
        this.num_samedi = findViewById(R.id.num_samedi);
        this.num_dimanche = findViewById(R.id.num_dimanche);

        num_lundi.setText(n_lundi);
        num_mardi.setText(n_mardi);
        num_mercredi.setText(n_mercredi);
        num_jeudi.setText(n_jeudi);
        num_vendredi.setText(n_vendredi);
        num_samedi.setText(n_samedi);
        num_dimanche.setText(n_dimanche);

        this.mois_lundi = findViewById(R.id.mois_lundi);
        this.mois_mardi = findViewById(R.id.mois_mardi);
        this.mois_mercredi = findViewById(R.id.mois_mercredi);
        this.mois_jeudi = findViewById(R.id.mois_jeudi);
        this.mois_vendredi = findViewById(R.id.mois_vendredi);
        this.mois_samedi = findViewById(R.id.mois_samedi);
        this.mois_dimanche = findViewById(R.id.mois_dimanche);


        mois_lundi.setText(m_lundi);
        mois_mardi.setText(m_mardi);
        mois_mercredi.setText(m_mercredi);
        mois_jeudi.setText(m_jeudi);
        mois_vendredi.setText(m_vendredi);
        mois_samedi.setText(m_samedi);
        mois_dimanche.setText(m_dimanche);



        this.compteur_lundi = findViewById(R.id.compteur_lundi);
        this.compteur_mardi = findViewById(R.id.compteur_mardi);
        this.compteur_mercredi = findViewById(R.id.compteur_mercredi);
        this.compteur_jeudi = findViewById(R.id.compteur_jeudi);
        this.compteur_vendredi = findViewById(R.id.compteur_vendredi);
        this.compteur_samedi = findViewById(R.id.compteur_samedi);
        this.compteur_dimanche = findViewById(R.id.compteur_dimanche);


        getItemsCompteur();

    }



    private void getItemsCompteur() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzXzAJsDJ9uldFU7iYWnTEDBH4RqhjGKtRUv9QfRl8xWv5mrTep19P5DI3s42AxD8FO/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItemsCompteur(response);
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
    private void parseItemsCompteur(String jsonResponce) {

        int int_cptr_lundi=0;
        int int_cptr_mardi=0;
        int int_cptr_mercredi=0;
        int int_cptr_jeudi=0;
        int int_cptr_vendredi=0;
        int int_cptr_samedi=0;
        int int_cptr_dimanche=0;

        list_taches = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");

            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String Jour = jo.getString("Jour");
                String SiFait=jo.getString("SiFait");
                String Commentaire= jo.getString("Commentaire");

                if ("Lundi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_lundi++;
                    }
                    comment_lundi=Commentaire;
                }

                if ("Mardi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_mardi++;
                    }
                    comment_mardi=Commentaire;
                }

                if ("Mercredi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_mercredi++;
                    }
                    comment_mercredi=Commentaire;
                }

                if ("Jeudi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_jeudi++;
                    }
                    comment_jeudi=Commentaire;
                }

                if ("Vendredi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_vendredi++;
                    }
                    comment_vendredi=Commentaire;
                }

                if ("Samedi".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_samedi++;
                    }
                    comment_samedi=Commentaire;
                }

                if ("Dimanche".equals(Jour)){
                    if (SiFait.equals("En cours")){
                        int_cptr_dimanche++;
                    }
                    comment_dimanche=Commentaire;
                }
            }



            if (int_cptr_lundi==0){
                btn_lun.setEnabled(false);
            }
            if (int_cptr_mardi==0){
                btn_mar.setEnabled(false);
            }
            if (int_cptr_mercredi==0){
                btn_mer.setEnabled(false);
            }
            if (int_cptr_jeudi==0){
                btn_jeu.setEnabled(false);
            }
            if (int_cptr_vendredi==0){
                btn_ven.setEnabled(false);
            }
            if (int_cptr_samedi==0){
                btn_sam.setEnabled(false);
            }
            if (int_cptr_dimanche==0){
                btn_dim.setEnabled(false);
            }


            commentaire_lundi.setText(comment_lundi);
            commentaire_mardi.setText(comment_mardi);
            commentaire_mercredi.setText(comment_mercredi);
            commentaire_jeudi.setText(comment_jeudi);
            commentaire_vendredi.setText(comment_vendredi);
            commentaire_samedi.setText(comment_samedi);
            commentaire_dimanche.setText(comment_dimanche);

            c_lundi=int_cptr_lundi+"";
            compteur_lundi.setText(c_lundi);

            c_mardi=int_cptr_mardi+"";
            compteur_mardi.setText(c_mardi);

            c_mercredi=int_cptr_mercredi+"";
            compteur_mercredi.setText(c_mercredi);

            c_jeudi=int_cptr_jeudi+"";
            compteur_jeudi.setText(c_jeudi);

            c_vendredi=int_cptr_vendredi+"";
            compteur_vendredi.setText(c_vendredi);

            c_samedi=int_cptr_samedi+"";
            compteur_samedi.setText(c_samedi);

            c_dimanche=int_cptr_dimanche+"";
            compteur_dimanche.setText(c_dimanche);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        loading.dismiss();

    }


    private void getItems(final String j) {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycby517YZa68LQIXHkR02otOI9zSJXCHP5qDq11ZwryanmOfKUL0iSRRyaZd-SHjTrZO6/exec?action=getItems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response,j);
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
    private void parseItems(String jsonResponce, String j) {

        list_taches = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResponce);
            JSONArray jarray = jobj.getJSONArray("items");

            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String Id = jo.getString("Id");
                String Jour = jo.getString("Jour");
                String Tache = jo.getString("Tache");
                String SiFait=jo.getString("SiFait");

                if (j.equals(Jour)){
                    if (SiFait.equals("En cours")){
                        list_taches.add(new Tache(Jour, Id, Tache,SiFait));
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        this.adapter_tache = new TacheAdapter(this,  list_taches);
        lv.setAdapter(this.adapter_tache);
        lv.setOnItemClickListener((adapterView, view, pos, l) -> this.adapter_tache.toggle(pos));

    }


    //Click sur un jour de la semaine

    @Override
    public void onClick(View v) {

        alertDialog = new AlertDialog.Builder(ActivityTache.this);

        View row = getLayoutInflater().inflate(R.layout.row_item_taches, null);
        lv = (ListView) row.findViewById(R.id.listView);

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        jour_semaine = findViewById(R.id.jour_semaine);;

        System.out.println(jour_semaine);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("onItemClick: " +position);
                CheckedTextView vi = (CheckedTextView) view;
                boolean currentCheck = vi.isChecked();
                System.out.println(lv.getItemAtPosition(position));
                //user.setActive(!currentCheck);
            }
        });


        if (v==btn_lun){
            j="Lundi";
            getItems("Lundi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
            //adapter_tache.notifyDataSetChanged();
        }
        else if (v==btn_mar){
            j="Mardi";
            getItems("Mardi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }
        else if (v==btn_mer){
            j="Mercredi";
            getItems("Mercredi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }
        else if (v==btn_jeu){
            j="Jeudi";
            getItems("Jeudi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }
        else if (v==btn_ven){
            j="Vendredi";
            getItems("Vendredi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }

        else if (v==btn_sam){
            j="Samedi";
            getItems("Samedi");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }
        else {
            j="Dimanche";
            getItems("Dimanche");
            alertDialog.setView(row);
            dialog = alertDialog.create();
            Toast.makeText(this, "Chargement ...", Toast.LENGTH_LONG).show();
            dialog.show();
            loading.dismiss();
        }
    }


    public ArrayList getSelectedItems()  {

        ArrayList<Tache> list = new ArrayList<>();
        int nb = 0;

        SparseBooleanArray sp = lv.getCheckedItemPositions();
        StringBuilder sb= new StringBuilder();


        for(int i=0;i<sp.size();i++) {
            if (sp.valueAt(i)) {
                Tache tache = (Tache) lv.getItemAtPosition(i);

                list.add(tache);
                sb = sb.append(" ");
                sb = sb.append(tache);
                nb++;
            }
        }

        Toast.makeText(this, "Vous avez selectionné  "+nb+" élément(s)", Toast.LENGTH_LONG).show();

        return list;
    }


    // Enregistrer une tache réalisée

    public void lancer_tache_sauvegarde(View v){

        //final Intent intent_2 = new Intent(this, ActivityEcrirTache.class);
        //Toast.makeText(this, "Coucou", Toast.LENGTH_LONG).show();

        List<Tache> list_select;

        list_select = adapter_tache.getSelected();

        System.out.println(list_select);

        //intent_2.putExtra("main_user", main_user);
        //intent_2.putExtra("list_select", (Serializable) list_select);
        //startActivity(intent_2);

        for (int i=0; i<list_select.size();i++){

            Tache tache = list_select.get(i);

            WriteOnSheetTaches.updateData(this,  tache.getTache(), j);
            WriteOnSheetTaches.writeData(this, main_user, tache.getTache());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(this, ActivityTache.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
        dialog.dismiss();

    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void lancermenu(View view) {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

    public void getCommentLundi(View view){
        j="Lundi";
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_lundi =(EditText) row.findViewById(R.id.comment);
        if (comment_lundi.equals("Ecrire commentaire")){
            comment_lundi="";
        }
        edit_lundi.setText(comment_lundi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();
    }

    public void getCommentMardi(View view){
        j="Mardi";
        System.out.println(comment_mardi);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_mardi =(EditText) row.findViewById(R.id.comment);
        if (comment_mardi.equals("Ecrire commentaire")){
            comment_mardi="";
        }
        edit_mardi.setText(comment_mardi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();
    }

    public void getCommentMercredi(View view){
        j="Mercredi";
        System.out.println(comment_mercredi);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_mercredi =(EditText) row.findViewById(R.id.comment);
        if (comment_mercredi.equals("Ecrire commentaire")){
            comment_mercredi="";
        }
        edit_mercredi.setText(comment_mercredi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();    }

    public void getCommentJeudi(View view){
        j="Jeudi";
        System.out.println(comment_jeudi);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_jeudi =(EditText) row.findViewById(R.id.comment);
        if (comment_jeudi.equals("Ecrire commentaire")){
            comment_jeudi="";
        }
        edit_jeudi.setText(comment_jeudi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();
    }
    public void getCommentVendredi(View view){
        j="Vendredi";
        System.out.println(comment_vendredi);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_vendredi =(EditText) row.findViewById(R.id.comment);
        if (comment_vendredi.equals("Ecrire commentaire")){
            comment_vendredi="";
        }
        edit_vendredi.setText(comment_vendredi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();
    }
    public void getCommentSamedi(View view){
        j="Samedi";
        System.out.println(comment_samedi);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_samedi =(EditText) row.findViewById(R.id.comment);
        if (comment_samedi.equals("Ecrire commentaire")){
            comment_samedi="";
        }
        edit_samedi.setText(comment_samedi);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();    }

    public void getCommentDimanche(View view){
        j="Dimanche";
        System.out.println(comment_dimanche);
        alert_comment = new AlertDialog.Builder(ActivityTache.this);
        View row = getLayoutInflater().inflate(R.layout.popup_comment, null);
        edit_dimanche =(EditText) row.findViewById(R.id.comment);
        if (comment_dimanche.equals("Ecrire commentaire")){
            comment_dimanche="";
        }
        edit_dimanche.setText(comment_dimanche);
        alert_comment.setView(row);
        dialog_comment = alert_comment.create();
        dialog_comment.show();
        loading.dismiss();
    }

    public void close_popup(View view) {
        dialog.dismiss();
    }

    public void close_popup_comment(View view) {
        dialog_comment.dismiss();
    }

    public void lancer_comment_sauvegarde(View view){

        String chaine="";

        if (j.equals("Lundi")){
            chaine = edit_lundi.getText().toString();
        } else if (j.equals("Mardi")){
            chaine = edit_mardi.getText().toString();
        } else if (j.equals("Mercredi")){
            chaine = edit_mercredi.getText().toString();
        } else if (j.equals("Jeudi")){
            chaine = edit_jeudi.getText().toString();
        } else if (j.equals("Vendredi")){
            chaine = edit_vendredi.getText().toString();
        } else if (j.equals("Samedi")){
            chaine = edit_samedi.getText().toString();
        } else if (j.equals("Dimanche")){
            chaine = edit_dimanche.getText().toString();
        }

        WriteOnSheetTaches.updateComment(this, chaine,j);

        Intent intent = new Intent(this, ActivityTache.class);
        intent.putExtra("main_user", main_user);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
        dialog_comment.dismiss();

    }

    public void getNettoyage(View view){
        Intent intent = new Intent(this, NettoyageBac.class);
        intent.putExtra("main_user", main_user);
        startActivity(intent);
    }

}


