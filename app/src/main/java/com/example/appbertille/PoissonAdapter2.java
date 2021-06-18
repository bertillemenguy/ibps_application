package com.example.appbertille;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

class PoissonAdapter2 extends ArrayAdapter<Poisson> implements Serializable {
    private final List<Poisson> liste;
    private final Context context;


    public PoissonAdapter2(Context context, List<Poisson> data) {
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
        ImageView Color;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PoissonViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_registre, null);

            // view holder et ses vues
            holder = new PoissonViewHolder();
            holder.Lignee = (TextView) convertView.findViewById(R.id.tv_lignee);
            holder.Lot = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.Age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.Responsable = (TextView) convertView.findViewById(R.id.tv_responsable);
            holder.Bac = (TextView) convertView.findViewById(R.id.tv_bac);
            holder.Image =(ImageView) convertView.findViewById(R.id.icon_mort);
            holder.Color =(ImageView) convertView.findViewById(R.id.color_poisson_peril);

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
        holder.Color.setImageResource(poisson.getColor());

        return convertView;
    }


}


