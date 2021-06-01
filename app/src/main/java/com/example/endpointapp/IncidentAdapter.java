package com.example.endpointapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IncidentAdapter extends ArrayAdapter<Incident> implements Serializable {

    private final List<Incident> liste;
    private final List<Incident> selected = new ArrayList<Incident>();
    private final Context context;

    public IncidentAdapter(Context context, List<Incident> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
    }

    private class IncidentViewHolder{
        TextView Date;
        TextView Operateur;
        TextView Etat;

        TextView eaudeville;
        TextView electricite;
        TextView aircomprime;
        TextView climatisation;
        TextView eaudusysteme;
        TextView systemeaquatique;
        TextView travaux;
        TextView nourrissage;
        TextView key;

        CheckBox checkbox;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        IncidentAdapter.IncidentViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_incidents, null);

            // view holder et ses vues
            holder = new IncidentAdapter.IncidentViewHolder();
            holder.Date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.Operateur = (TextView) convertView.findViewById(R.id.tv_operateur);
            holder.Etat = (TextView) convertView.findViewById(R.id.tv_etat);
            holder.eaudeville = (TextView) convertView.findViewById(R.id.tv_eaudeville);
            holder.electricite = (TextView) convertView.findViewById(R.id.tv_electricite);
            holder.aircomprime = (TextView) convertView.findViewById(R.id.tv_aircomprime);
            holder.climatisation = (TextView) convertView.findViewById(R.id.tv_climatisation);
            holder.eaudusysteme = (TextView) convertView.findViewById(R.id.tv_eaudusysteme);
            holder.systemeaquatique = (TextView) convertView.findViewById(R.id.tv_systemeAquatique);
            holder.travaux = (TextView) convertView.findViewById(R.id.tv_travaux);
            holder.nourrissage = (TextView) convertView.findViewById(R.id.tv_nourrissage);

            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);



            convertView.setTag(holder);
        } else {
            holder = (IncidentAdapter.IncidentViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Incident incident = liste.get(position);
        holder.Date.setText(incident.getDate());
        holder.Operateur.setText(incident.getOperateur());
        holder.Etat.setText(incident.getEtat());

        holder.eaudeville.setText(incident.getEaudeville());
        holder.electricite.setText(incident.getElectricite());
        holder.aircomprime.setText(incident.getAircomprime());
        holder.climatisation.setText(incident.getClimatisation());
        holder.eaudusysteme.setText(incident.getEaudusysteme());
        holder.systemeaquatique.setText(incident.getSystemeaquatique());
        holder.travaux.setText(incident.getTravaux());
        holder.nourrissage.setText(incident.getNourrissage());


        holder.checkbox.setChecked(selected.contains(incident));

        return convertView;
    }


    // inverse la sélection sur l'élément n°pos
    public void toggle(int pos) {

        // donnée sélectionnée
        Incident incident = liste.get(pos);
        // enlever cette donnée si elle est dans la sélection, sinon la rajouter
        if (! selected.remove(incident)) {
            selected.add(incident);
        }
        // signaler un changement dans la liste
        notifyDataSetChanged();
    }

    // retourne la liste des éléments sélectionnés
    public List<Incident> getSelected() {
        return selected;
    }

}
