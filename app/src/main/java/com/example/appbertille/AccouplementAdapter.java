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
import java.util.List;

public class AccouplementAdapter extends ArrayAdapter<Accouplement> implements Serializable {


    private final List<Accouplement> liste;
    private final Context context;
    Intent intent;
    String main_user;

    public AccouplementAdapter(Context context, List<Accouplement> data, String main_user) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
        this.main_user=main_user;
        intent = new Intent(getContext(), ActivityHistoriqueAccouplements.class);
    }

    private class BacViewHolder{
        TextView Date;
        TextView Operateur;
        TextView NombreAccouplements;
        TextView Couleur1;
        TextView Couleur2;
        TextView NbMal;
        TextView LotMal;
        TextView BacMal;
        TextView LigneeMal;
        TextView AgeMal;
        TextView NbFemelle;
        TextView LotFemelle;
        TextView BacFemelle;
        TextView LigneeFemelle;
        TextView AgeFemelle;

        ImageButton button;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AccouplementAdapter.BacViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_accouplement, null);

            // view holder et ses vues
            holder = new AccouplementAdapter.BacViewHolder();


            holder.Date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.Operateur = (TextView) convertView.findViewById(R.id.tv_operateur);
            holder.Couleur1 = (TextView) convertView.findViewById(R.id.tv_couleur1);
            holder.Couleur2 = (TextView) convertView.findViewById(R.id.tv_couleur2);

            holder.NombreAccouplements = (TextView) convertView.findViewById(R.id.tv_nbbac);


            holder.NbMal = (TextView) convertView.findViewById(R.id.tv_nbmale);
            holder.LotMal = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.LigneeMal = (TextView) convertView.findViewById(R.id.tv_ligneemale);
            holder.AgeMal = (TextView) convertView.findViewById(R.id.tv_age);
            holder.BacMal = (TextView) convertView.findViewById(R.id.tv_bac);


            holder.LotFemelle = (TextView) convertView.findViewById(R.id.tv_lot2);
            holder.NbFemelle = (TextView) convertView.findViewById(R.id.tv_nbfemelle);
            holder.LigneeFemelle = (TextView) convertView.findViewById(R.id.tv_ligneefemelle);
            holder.BacFemelle = (TextView) convertView.findViewById(R.id.tv_bac2);
            holder.AgeFemelle = (TextView) convertView.findViewById(R.id.tv_age2);


            holder.button =(ImageButton) convertView.findViewById(R.id.btn_delete);

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String key = liste.get(position).getId();
                    System.out.println("KEY____"+key);

                    WriteOnSheetAccouplement.deleteData(getContext(), key);
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
            holder = (AccouplementAdapter.BacViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Accouplement accouplement = liste.get(position);

        holder.Date.setText(accouplement.getDate());
        // holder.Id.setText(bac.getId());
        holder.Operateur.setText(accouplement.getOperateur());
        holder.Couleur1.setText(accouplement.getCouleur1());
        holder.Couleur2.setText(accouplement.getCouleur2());
        holder.NbFemelle.setText(accouplement.getNbFemelle());
        holder.NbMal.setText(accouplement.getNbMal());
        holder.LigneeFemelle.setText(accouplement.getLigneeFemelle());
        holder.LigneeMal.setText(accouplement.getLigneeMal());
        holder.LotFemelle.setText(accouplement.getLotFemelle());
        holder.LotMal.setText(accouplement.getLotMal());
        holder.AgeFemelle.setText(accouplement.getAgeFemelle());
        holder.AgeMal.setText(accouplement.getAgeMal());
        holder.BacFemelle.setText(accouplement.getBacFemelle());
        holder.BacMal.setText(accouplement.getBacMal());
        holder.NombreAccouplements.setText(accouplement.getNombreAccouplements());


        return convertView;
    }

}
