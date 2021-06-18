package com.example.appbertille;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TacheAdapter extends ArrayAdapter<Tache> implements Serializable {
    private final ArrayList<Tache> liste;
    private final ArrayList<Tache> selected = new ArrayList<Tache>();
    private final Context context;


    public TacheAdapter(Context context, ArrayList<Tache> data) {
        super(context, 0, data);
        this.context=context;
        this.liste = data;
    }


    private class TacheViewHolder{
        TextView Tache;
        CheckBox checkbox;
    }


    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TacheAdapter.TacheViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.liste_tache_checked, null);

            // view holder et ses vues
            holder = new TacheAdapter.TacheViewHolder();
            holder.Tache = (TextView) convertView.findViewById(R.id.tache);


            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);

            convertView.setTag(holder);
        } else {
            holder = (TacheAdapter.TacheViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Tache tache = liste.get(position);
        holder.Tache.setText(tache.getTache());

        holder.checkbox.setChecked(selected.contains(tache));

        return convertView;
    }


    // inverse la sélection sur l'élément n°pos
    public void toggle(int pos) {
        //Log.e("APPLI", "selected="+selected.toString());
        Log.e("APPLI", "je suis dans le toggle() et je fais ça");

        // donnée sélectionnée
        Tache tache = liste.get(pos);
        // enlever cette donnée si elle est dans la sélection, sinon la rajouter
        if (! selected.remove(tache)) {
            selected.add(tache);
        }
        // signaler un changement dans la liste
        notifyDataSetChanged();
    }


    // retourne la liste des éléments sélectionnés
    public List<Tache> getSelected() {
        Log.e("APPLI", "je suis dans le getSelect() et je fais ça");
        return selected;
    }

}
