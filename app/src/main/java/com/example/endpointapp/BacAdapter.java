package com.example.endpointapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BacAdapter extends ArrayAdapter<Bac> implements Serializable {

    private final List<Bac> liste;
    private final List<Bac> selected = new ArrayList<Bac>();
    private final Context context;

    public BacAdapter(Context context, List<Bac> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
    }

    private class BacViewHolder{
        TextView Date;
        TextView Id;
        TextView Actions;
        TextView NouveauBac;
        TextView Lignee;
        TextView Bac;
        TextView Lignee2;
        TextView Bac2;
        TextView remarque;
        TextView remarque2;
        TextView Bac2b;
        TextView remarque3;
        TextView Bac3;
        TextView SiTraite;
        TextView Remarque4;
        TextView Bac4;
        TextView Lot;
        TextView Lot2;

        CheckBox checkbox;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BacAdapter.BacViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_bacs, null);

            // view holder et ses vues
            holder = new BacAdapter.BacViewHolder();


            //{"modif_Date", "Actions", "NouveauBac",                            "Lignee",      "Bac",              "Lignee2",              "Bac2",     "remarque",             "remarque2",            "Bac2b",            "remarque3",             "Bac3",            "remarque4",            "Bac4",         "Lot",      "Lot2",         "SItraite"},
            //{R.id.tv_date, R.id.tv_actions, R.id.tv_nouveaubacEtDupliBac1, R.id.tv_lignee, R.id.tv_bac, R.id.tv_ReuniLignee2, R.id.tv_RuniBac2, R.id.tv_DupliRemarque1, R.id.tv_DupliRemarque2, R.id.tv_DupliBac2, R.id.tv_DupliRemarque3, R.id.tv_DupliBac3, R.id.tv_DupliRemarque4, R.id.tv_DupliBac4, R.id.tv_lot, R.id.tv_ReuniLot2, R.id.tv_SITraite}


            holder.Date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.Actions = (TextView) convertView.findViewById(R.id.tv_actions);
            holder.NouveauBac = (TextView) convertView.findViewById(R.id.tv_nouveaubacEtDupliBac1);
            holder.Lignee = (TextView) convertView.findViewById(R.id.tv_lignee);
            holder.Bac = (TextView) convertView.findViewById(R.id.tv_bac);
            holder.Lignee2 = (TextView) convertView.findViewById(R.id.tv_ReuniLignee2);
            holder.Bac2 = (TextView) convertView.findViewById(R.id.tv_RuniBac2);
            holder.remarque = (TextView) convertView.findViewById(R.id.tv_DupliRemarque1);
            holder.remarque2 = (TextView) convertView.findViewById(R.id.tv_DupliRemarque2);
            holder.Bac2b = (TextView) convertView.findViewById(R.id.tv_DupliBac2);
            holder.remarque3 = (TextView) convertView.findViewById(R.id.tv_DupliRemarque3);
            holder.Bac3 = (TextView) convertView.findViewById(R.id.tv_DupliBac3);
            holder.Remarque4 = (TextView) convertView.findViewById(R.id.tv_DupliRemarque4);
            holder.Bac4 = (TextView) convertView.findViewById(R.id.tv_DupliBac4);
            holder.Lot = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.Lot2 = (TextView) convertView.findViewById(R.id.tv_ReuniLot2);
            holder.SiTraite = (TextView) convertView.findViewById(R.id.tv_SITraite);

            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);

            convertView.setTag(holder);

        } else {
            holder = (BacAdapter.BacViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Bac bac = liste.get(position);

        holder.Date.setText(bac.getDate());
       // holder.Id.setText(bac.getId());
        holder.Actions.setText(bac.getActions());
        holder.NouveauBac.setText(bac.getNouveauBac());
        holder.Lignee.setText(bac.getLignee());
        holder.Bac.setText(bac.getBac());
        holder.Lignee2.setText(bac.getLignee2());
        holder.Bac2.setText(bac.getBac2());
        holder.remarque.setText(bac.getRemarque());
        holder.remarque2.setText(bac.getRemarque2());
        holder.Bac2b.setText(bac.getBac2b());
        holder.remarque3.setText(bac.getRemarque3());
        holder.Remarque4.setText(bac.getRemarque4());
        holder.Bac3.setText(bac.getBac3());
        holder.Bac4.setText(bac.getBac4());
        holder.Lot.setText(bac.getLot());
        holder.Lot2.setText(bac.getLot2());
        holder.SiTraite.setText(bac.getSiTraite());



        if (bac.getSiTraite().equals("En cours")){
            holder.checkbox.setChecked(selected.contains(bac));
        } else {
            holder.checkbox.setVisibility(View.INVISIBLE);
        }


        return convertView;
    }


    // inverse la sélection sur l'élément n°pos
    public void toggle(int pos) {

        // donnée sélectionnée
        Bac bac = liste.get(pos);
        // enlever cette donnée si elle est dans la sélection, sinon la rajouter
        if (! selected.remove(bac)) {
            selected.add(bac);
        }
        // signaler un changement dans la liste
        notifyDataSetChanged();
    }

    // retourne la liste des éléments sélectionnés
    public List<Bac> getSelected() {
        return selected;
    }



}
