package com.example.endpointapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyAdapter extends SimpleAdapter {

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     * Maps contain the data for each row, and should include all the entries specified in
     * "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     * item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     * item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     * TextViews. The first N views in this list are given the values of the first N columns
     */


    Context context;
    TextView title, lignee,lot;
    HashMap<String, String> hashMap = new HashMap<>();
    List<HashMap<String, String>> data;
    int resource;
    Date date=null;
    Date today= null;

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data = (List<HashMap<String, String>>) data;
        this.context = context;
        this.resource= resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
            //@NonNull
        View v = super.getView(position, convertView, parent);

        if (v == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(resource, null);
        }



        this.title = (TextView) v.findViewById(R.id.text2);
        this.lignee = (TextView) v.findViewById(R.id.tv_lignee);
        this.lot = (TextView) v.findViewById(R.id.tv_lot);



        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat=new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);


        System.out.println(this.data.get(position));

        hashMap= this.data.get(position);


        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("Couleur")){
                if (value.equalsIgnoreCase("Passage au Jaune")){
                    v.setBackgroundColor(Color.YELLOW);
                } else if (value.equalsIgnoreCase("Passage au Violet")) {
                    v.setBackgroundColor(Color.MAGENTA);
                } else {
                    v.setBackgroundColor(Color.GREEN);
                }
            }


            if (key.equalsIgnoreCase("Date")){

                try {
                    date = outputFormat.parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date dt = null;
                Date dd = null;

                today= new Date();

                String t=dateFormat.format(today);
                String d=dateFormat.format(date);

                try {
                    dt = dateFormat.parse(t);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    dd = dateFormat.parse(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if ((dt.compareTo(dd)==0)||(dt.compareTo(dd)==-1)){
                    this.title.setTextColor(v.getResources().getColor(R.color.cardview_dark_background));
                    this.lignee.setTextColor(v.getResources().getColor(R.color.cardview_dark_background));
                    this.lot.setTextColor(v.getResources().getColor(R.color.cardview_dark_background));
               } else {
                    this.title.setTextColor(v.getResources().getColor(R.color.gris));
                    this.lignee.setTextColor(v.getResources().getColor(R.color.gris));
                    this.lot.setTextColor(v.getResources().getColor(R.color.gris));
                }
            }

    }

        return v;
    }
}

