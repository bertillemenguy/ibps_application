package com.example.endpointapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    HashMap<String, String> hashMap = new HashMap<>();
    List<HashMap<String, String>> data;

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data = (List<HashMap<String, String>>) data;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = super.getView(position, convertView, parent);

       /*if (Couleur.equalsIgnoreCase("Passage au Vert")){
            vert.add(i);
        } else if (Couleur.equalsIgnoreCase("Passage au Violet")){
            violet.add(i);
        } else if(Couleur.equalsIgnoreCase("Passage au Jaune")){
            jaune.add(i);
        }*/

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
    }

       /* if (from[][1] == 0) {
        } else {
            v.setBackgroundColor(Color.WHITE);
        }
        */return v;
    }
}

