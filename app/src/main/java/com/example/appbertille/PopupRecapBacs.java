package com.example.appbertille;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class PopupRecapBacs extends Dialog {

    private ListView listView;
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String,String>> list;
    private Activity activity;

    public PopupRecapBacs(Activity activity, ArrayList list){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        this.activity=activity;
        this.list=list;
        setContentView(R.layout.template__popup_recap_bac);
        this.listView=findViewById(R.id.listView);
        listView.setSelector(android.R.color.transparent);

    }


    public void build(){
        show();
        adapter = new SimpleAdapter(activity, list, R.layout.list_item_recap_bac, new String[]{"Operateur", "Lavage"}, new int[]{R.id.tv_operateur, R.id.tv_nb_lavage});
        listView.setAdapter(adapter);
    }


}
