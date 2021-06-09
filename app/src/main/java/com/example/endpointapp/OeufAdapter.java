package com.example.endpointapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OeufAdapter extends ArrayAdapter<Oeuf> implements Serializable {

    private final List<Oeuf> liste;
    private final Context context;
    Intent intent;
    String main_user;

    public OeufAdapter(Context context, List<Oeuf> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
        intent = new Intent(getContext(), ActivityHistoriqueAccouplements.class);

    }

    private class IncidentViewHolder{
        TextView Date;
        TextView operateur;
        TextView LigneeM;
        TextView LigneeF;
        TextView BacM;
        TextView BacF;
        TextView NbBac;
        TextView NbMale;
        TextView NbFemelle;
        TextView AgeM;
        TextView AgeF;
        TextView quantite;
        TextView qualite;
        TextView LotM;
        TextView LotF;
        TextView NbMalesFeconde;
        TextView NbfemellesFeconde;

        ImageButton button;


    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OeufAdapter.IncidentViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_oeufs, null);

            // view holder et ses vues
            holder = new OeufAdapter.IncidentViewHolder();
            holder.Date = (TextView) convertView.findViewById(R.id.datee);
            holder.operateur = (TextView) convertView.findViewById(R.id.operateur);

            holder.NbBac = (TextView) convertView.findViewById(R.id.nbbac);

            holder.NbMalesFeconde = (TextView) convertView.findViewById(R.id.nbmalesfeconde);
            holder.NbMale = (TextView) convertView.findViewById(R.id.nbmale);
            holder.LigneeM = (TextView) convertView.findViewById(R.id.ligneemale);
            holder.AgeM = (TextView) convertView.findViewById(R.id.agemale);
            holder.LotM = (TextView) convertView.findViewById(R.id.tv_item_lotM);

            holder.BacM = (TextView) convertView.findViewById(R.id.bacmale);
            holder.NbfemellesFeconde = (TextView) convertView.findViewById(R.id.nbfemellesfeconde);
            holder.NbFemelle = (TextView) convertView.findViewById(R.id.nbfemelle);
            holder.LigneeF = (TextView) convertView.findViewById(R.id.ligneefemelle);
            holder.AgeF = (TextView) convertView.findViewById(R.id.agefemelle);
            holder.BacF = (TextView) convertView.findViewById(R.id.bacfemelle);
            holder.LotF = (TextView) convertView.findViewById(R.id.tv_item_LotF);

            holder.qualite = (TextView) convertView.findViewById(R.id.qualite);
            holder.quantite = (TextView) convertView.findViewById(R.id.quantite);


            holder.button =(ImageButton) convertView.findViewById(R.id.btn_delete);

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String key = liste.get(position).getId();
                    System.out.println("KEY____"+key);

                    WriteOnSheetOeuf.deleteData(getContext(), key);
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
            holder = (OeufAdapter.IncidentViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Oeuf oeuf = liste.get(position);

        holder.Date.setText(oeuf.getDate());
        holder.operateur.setText(oeuf.getOperateur());

        holder.NbBac.setText(oeuf.getNbBac());


        holder.LigneeM.setText(oeuf.getLigneeM());
        holder.AgeM.setText(oeuf.getAgeM());
        holder.BacM.setText(oeuf.getBacM());
        holder.LotM.setText(oeuf.getLotM());
        holder.NbMale.setText(oeuf.getNbMale());
        holder.NbMalesFeconde.setText(oeuf.getNbMalesFeconde());

        holder.LigneeF.setText(oeuf.getLigneeF());
        holder.AgeF.setText(oeuf.getAgeF());
        holder.BacF.setText(oeuf.getBacF());
        holder.LotF.setText(oeuf.getLotF());
        holder.NbFemelle.setText(oeuf.getNbFemelle());
        holder.NbfemellesFeconde.setText(oeuf.getNbfemellesFeconde());

        holder.quantite.setText(oeuf.getQuantite());
        holder.qualite.setText(oeuf.getQualite());


        return convertView;
    }



}
