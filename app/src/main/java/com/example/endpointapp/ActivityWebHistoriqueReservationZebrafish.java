package com.example.endpointapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

public class ActivityWebHistoriqueReservationZebrafish extends AppCompatActivity {
    String html = "<iframe width=\"1000\" height=\"1000\" style=\"border: 1px solid #cccccc;\"  <iframe src=\"https://calendar.google.com/calendar/embed?src=fg2lqq6titggiuqqodhpg0sm30%40group.calendar.google.com&ctz=Europe%2FParis\" style=\"border: 0\" width=\"400\" height=\"300\" frameborder=\"0\" scrolling=\"no\"></iframe>";
    // "https://docs.google.com/spreadsheets/d/e/2PACX-1vR2oO52Ut5QANevgkiopmOW1P79yZ8Eq4AwdLEd-32NzVaFRgL0zDvIRifVFWhzOpGeE0WvLlpJUuCE/pubhtml?gid=370636495&single=true&amp;headers=false\"></iframe>";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_historique);
        WebView webview;
        webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        
        webview.loadData(html, "text/html", null);
        
    }
    
    public void fermeractivite(View view) {
        this.finish();
    }
}


/**
 * public class ActivityHistoriqueIncidents extends AppCompatActivity {
 * <p>
 * <p>
 * <p>
 * <p>
 * String html = "<iframe width=\"2000\" height=\"2000\" style=\"border: 1px solid #cccccc;\"  src=\"https://docs.google.com/spreadsheets/d/e/2PACX-1vR2oO52Ut5QANevgkiopmOW1P79yZ8Eq4AwdLEd-32NzVaFRgL0zDvIRifVFWhzOpGeE0WvLlpJUuCE/pubhtml?gid=370636495&single=true&amp;headers=false\"></iframe>";
 * <p>
 * <p>
 * <p>
 * ListView listView;
 * SimpleAdapter adapter;
 * ProgressDialog loading;
 * EditText editTextSearchItem;
 *
 * @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_recherche_registre_item);
 * <p>
 * //Get List View objet from xml
 * listView = findViewById(R.id.lv_items);
 * /** listView.setOnItemClickListener(this);
 * <p>
 * <p>
 * editTextSearchItem = findViewById(R.id.et_search);
 * <p>
 * // Get the transferred data from source activity.
 * //    Intent intent = getIntent();
 * //  operateur = intent.getStringExtra("operateur");
 * <p>
 * getItems();
 * <p>
 * }
 * <p>
 * private void getItems() {
 * <p>
 * <p>
 * StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbyJe-JjVBjWE41T5jXrxv_sM7NavQPWxchPAp8SPmPzxbjDo4P2/exec/exec?action=getItems", new Response.Listener<String>() {
 * @Override public void onResponse(String response) {
 * parseItems(response);
 * }
 * },
 * <p>
 * new Response.ErrorListener() {
 * @Override public void onErrorResponse(VolleyError error) {
 * <p>
 * }
 * }
 * );
 * loading = ProgressDialog.show(this, "Chargement...", " Veuillez patienter", false, true);
 * <p>
 * int socketTimeOut = 50000;
 * RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
 * <p>
 * stringRequest.setRetryPolicy(policy);
 * <p>
 * RequestQueue queue = Volley.newRequestQueue(this);
 * queue.add(stringRequest);
 * <p>
 * }
 * <p>
 * /**
 * formater les donnees dans un nouveau layout (list_item_row)
 * @param jsonResponce
 * <p>
 * private void parseItems(String jsonResponce) {
 * <p>
 * //
 * ArrayList<HashMap<String, String>> list = new ArrayList<>();
 * <p>
 * try {
 * JSONObject jobj = new JSONObject(jsonResponce);
 * JSONArray jarray = jobj.getJSONArray("items");
 * <p>
 * <p>
 * for (int i = 0; i < jarray.length(); i++) {
 * <p>
 * JSONObject jo = jarray.getJSONObject(i);
 * <p>
 * String Date = jo.getString("Date");
 * String operateur = jo.getString("operateur");
 * String eaudeville = jo.getString("eaudeville");
 * String electricite = jo.getString("electricite");
 * String aircomprime = jo.getString("aircomprime");
 * String climatisation = jo.getString("climatisation");
 * String eaudusysteme = jo.getString("eaudusysteme");
 * String systemeaquatique = jo.getString("systemeaquatique");
 * String travaux = jo.getString("travaux");
 * <p>
 * <p>
 * <p>
 * HashMap<String, String> item = new HashMap<>();
 * <p>
 * item.put("Date", Date);
 * item.put("operateur", operateur);
 * item.put("eaudeville", eaudeville);
 * item.put("electricite", electricite);
 * item.put("aircomprime", aircomprime);
 * <p>
 * item.put("eaudusysteme", eaudusysteme);
 * item.put("systemeaquatique", systemeaquatique);
 * item.put("travaux", travaux);
 * <p>
 * <p>
 * <p>
 * list.add(item);
 * <p>
 * }
 * } catch (JSONException e) {
 * e.printStackTrace();
 * <p>
 * }
 * <p>
 * <p>
 * adapter = new SimpleAdapter(this, list, R.layout.list_item_historique_incidentsb, new String[]{"Bac", "operateur","eaudeville","electricite","aircomprime","eaudusysteme","systemeaquatique","travaux"}, new int[]{R.id.datee, R.id.operateur, R.id.ligneemale, R.id.ligneefemelle, R.id.tv_responsable});
 * <p>
 * <p>
 * //assign adapter to list view
 * listView.setAdapter(adapter);
 * loading.dismiss();
 * <p>
 * //Toast
 * <p>
 * <p>
 * editTextSearchItem.addTextChangedListener(new TextWatcher() {
 * @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
 * <p>
 * }
 * @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
 * ActivityRechercheRegistre.this.adapter.getFilter().filter(charSequence);
 * }
 * @Override public void afterTextChanged(Editable editable) {
 * <p>
 * <p>
 * }
 * });
 * }
 * <p>
 * /**
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.list_item_historique_incidents);
 * WebView webview;
 * webview = findViewById(R.id.webview);
 * webview.getSettings().setJavaScriptEnabled(true);
 * <p>
 * webview.loadData(html, "text/html", null);
 * <p>
 * }
 * <p>
 * public void lancermenu(View view) {
 * <p>
 * Intent intent = new Intent(this, ActivityMenu.class);
 * //  intent.putExtra("operateur", operateur);
 * startActivity(intent);
 * }
 * /**
 * public void fermeractivite(View view) {
 * this.finish();
 * }
 * <p>
 * }
 */
/**
 private void parseItems(String jsonResponce) {
 
 //
 ArrayList<HashMap<String, String>> list = new ArrayList<>();
 
 try {
 JSONObject jobj = new JSONObject(jsonResponce);
 JSONArray jarray = jobj.getJSONArray("items");
 
 
 for (int i = 0; i < jarray.length(); i++) {
 
 JSONObject jo = jarray.getJSONObject(i);
 
 String Date = jo.getString("Date");
 String operateur = jo.getString("operateur");
 String eaudeville = jo.getString("eaudeville");
 String electricite = jo.getString("electricite");
 String aircomprime = jo.getString("aircomprime");
 String climatisation = jo.getString("climatisation");
 String eaudusysteme = jo.getString("eaudusysteme");
 String systemeaquatique = jo.getString("systemeaquatique");
 String travaux = jo.getString("travaux");
 
 
 
 HashMap<String, String> item = new HashMap<>();
 
 item.put("Date", Date);
 item.put("operateur", operateur);
 item.put("eaudeville", eaudeville);
 item.put("electricite", electricite);
 item.put("aircomprime", aircomprime);
 
 item.put("eaudusysteme", eaudusysteme);
 item.put("systemeaquatique", systemeaquatique);
 item.put("travaux", travaux);
 
 
 
 list.add(item);
 
 }
 } catch (JSONException e) {
 e.printStackTrace();
 
 }
 
 
 adapter = new SimpleAdapter(this, list, R.layout.list_item_historique_incidentsb, new String[]{"Bac", "operateur","eaudeville","electricite","aircomprime","eaudusysteme","systemeaquatique","travaux"}, new int[]{R.id.datee, R.id.operateur, R.id.ligneemale, R.id.ligneefemelle, R.id.tv_responsable});
 
 
 //assign adapter to list view
 listView.setAdapter(adapter);
 loading.dismiss();
 
 //Toast
 
 
 editTextSearchItem.addTextChangedListener(new TextWatcher() {
@Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

}

@Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
ActivityRechercheRegistre.this.adapter.getFilter().filter(charSequence);
}

@Override public void afterTextChanged(Editable editable) {


}
});
 }
 
 /**
 @Override protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.list_item_historique_incidents);
 WebView webview;
 webview = findViewById(R.id.webview);
 webview.getSettings().setJavaScriptEnabled(true);
 
 webview.loadData(html, "text/html", null);
 
 }
 */
/**
 public void lancermenu(View view) {
 
 Intent intent = new Intent(this, ActivityMenu.class);
 //  intent.putExtra("operateur", operateur);
 startActivity(intent);
 }
 /**
 public void fermeractivite(View view) {
 this.finish();
 }
 
 }
 */