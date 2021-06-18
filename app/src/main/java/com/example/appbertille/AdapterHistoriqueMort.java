package com.example.appbertille;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdapterHistoriqueMort extends ArrayAdapter<PoissonMort> implements Serializable {


    private final List<PoissonMort> liste;
    private final List<PoissonMort> selected = new ArrayList<PoissonMort>();
    private final Context context;

    Intent intent;
    String main_user;


    public AdapterHistoriqueMort(Context context, List<PoissonMort> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
        intent = new Intent(getContext(), ActivityHistoriqueMorts.class);
    }

    private class BacViewHolder{
        TextView Date;
        TextView Age;
        TextView Bac;
        TextView Lignee;
        TextView Lot;
        TextView Responsable;
        TextView SiAccouplement;
        TextView SumMorts;
        TextView Key;
        ImageButton button;

    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AdapterHistoriqueMort.BacViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_morts, null);

            // view holder et ses vues
            holder = new AdapterHistoriqueMort.BacViewHolder();


            //{"modif_Date", "Actions", "NouveauBac",                            "Lignee",      "Bac",              "Lignee2",              "Bac2",     "remarque",             "remarque2",            "Bac2b",            "remarque3",             "Bac3",            "remarque4",            "Bac4",         "Lot",      "Lot2",         "SItraite"},
            //{R.id.tv_date, R.id.tv_actions, R.id.tv_nouveaubacEtDupliBac1, R.id.tv_lignee, R.id.tv_bac, R.id.tv_ReuniLignee2, R.id.tv_RuniBac2, R.id.tv_DupliRemarque1, R.id.tv_DupliRemarque2, R.id.tv_DupliBac2, R.id.tv_DupliRemarque3, R.id.tv_DupliBac3, R.id.tv_DupliRemarque4, R.id.tv_DupliBac4, R.id.tv_lot, R.id.tv_ReuniLot2, R.id.tv_SITraite}


            holder.Date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.Lignee = (TextView) convertView.findViewById(R.id.tv_lignee);
            holder.Age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.Responsable = (TextView) convertView.findViewById(R.id.tv_responsable);
            holder.SumMorts = (TextView) convertView.findViewById(R.id.tv_summorts);
            holder.Bac = (TextView) convertView.findViewById(R.id.tv_bac);
            holder.Lot = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.button = (ImageButton) convertView.findViewById(R.id.btn_delete);


            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        String key = liste.get(position).getId();
                        System.out.println("KEY____"+key);

                        WriteOnSheetDeclarerMort.deleteData(getContext(), key);
                        //Temps d'attente !!! IMPORTANT
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("main_user", main_user);
                        context.startActivity(intent);
                    }

            });

            convertView.setTag(holder);

        } else {
            holder = (AdapterHistoriqueMort.BacViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        PoissonMort poisson = liste.get(position);

        holder.Date.setText(poisson.getDate());
        holder.Lignee.setText(poisson.getLignee());
        holder.Bac.setText(poisson.getBac());
        holder.Lot.setText(poisson.getLot());
        holder.Age.setText(poisson.getAge());
        holder.Responsable.setText(poisson.getResponsable());
        holder.SumMorts.setText(poisson.getSumMorts());


        return convertView;



    }



}
