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

class PoissonAdapter extends ArrayAdapter<Poisson> implements Serializable {
    private final List<Poisson> liste;
    private final List<Poisson> selected = new ArrayList<Poisson>();
    private final Context context;


    public PoissonAdapter(Context context, List<Poisson> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
    }


    private class PoissonViewHolder{
        TextView Lignee;
        TextView Lot;
        TextView Age;
        TextView Responsable;
        TextView Bac;
        ImageView Image;
        CheckBox checkbox;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PoissonViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_registre_checked, null);

            // view holder et ses vues
            holder = new PoissonViewHolder();
            holder.Lignee = (TextView) convertView.findViewById(R.id.tv_lignee);
            holder.Lot = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.Age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.Responsable = (TextView) convertView.findViewById(R.id.tv_responsable);
            holder.Bac = (TextView) convertView.findViewById(R.id.tv_bac);

            holder.Image =(ImageView) convertView.findViewById(R.id.icon_mort);

            holder.checkbox = (CheckBox) convertView.findViewById(R.id.tv_checkbox);

            convertView.setTag(holder);
        } else {
            holder = (PoissonViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Poisson poisson = liste.get(position);
        holder.Lignee.setText(poisson.getLignee());
        holder.Lot.setText(poisson.getLot());
        holder.Age.setText(poisson.getAge());
        holder.Responsable.setText(poisson.getResponsable());
        holder.Bac.setText(poisson.getBac());
        holder.Image.setImageResource(poisson.getImage());
        holder.checkbox.setChecked(selected.contains(poisson));

        return convertView;
    }


    // inverse la sélection sur l'élément n°pos
    public void toggle(int pos) {
        //Log.e("APPLI", "selected="+selected.toString());
        Log.e("APPLI", "je suis dans le toggle() et je fais ça");

        // donnée sélectionnée
        Poisson poisson = liste.get(pos);
        // enlever cette donnée si elle est dans la sélection, sinon la rajouter
        if (! selected.remove(poisson)) {
            selected.add(poisson);
        }
        // signaler un changement dans la liste
        notifyDataSetChanged();
    }

    // retourne la liste des éléments sélectionnés
    public List<Poisson> getSelected() {
        Log.e("APPLI", "je suis dans le getSelect() et je fais ça");
        return selected;
    }

}


