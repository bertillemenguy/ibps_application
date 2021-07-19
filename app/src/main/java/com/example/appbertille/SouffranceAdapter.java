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

public class SouffranceAdapter  extends ArrayAdapter<Souffrance> implements Serializable {

    private final List<Souffrance> liste;
    private final Context context;
    Intent intent;
    String main_user;

    public SouffranceAdapter(Context context, List<Souffrance> data, String main_user) {
        super(context, 0, data);
        this.context=context;
        this.main_user=main_user;
        this.liste = data;
        intent = new Intent(getContext(), ActivityHistoriqueSouffrance.class);
    }

    private class BacViewHolder{
        TextView Date ;
        TextView Age ;
        TextView Bac ;
        TextView Lignee ;
        TextView Lot ;
        TextView Responsable ;
        TextView Euthanasie ;
        TextView Isolement ;
        TextView Surveillance ;
        TextView Aucune_action_a_mener ;
        TextView PoissonSouffrance ;
        TextView Id ;

        ImageButton button;
    }



    // méthode principale des adapters : elle installe une vue pour l'un des items de la liste
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SouffranceAdapter.BacViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_historique_souffrance, null);

            // view holder et ses vues
            holder = new SouffranceAdapter.BacViewHolder();


            holder.Date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.Lignee = (TextView) convertView.findViewById(R.id.tv_lignee);
            holder.Responsable = (TextView) convertView.findViewById(R.id.tv_responsable);

            holder.Age = (TextView) convertView.findViewById(R.id.tv_age);

            holder.Aucune_action_a_mener = (TextView) convertView.findViewById(R.id.tv_SiAucune_action_a_mener);
            holder.Isolement = (TextView) convertView.findViewById(R.id.tv_SiIsolement);
            holder.Surveillance = (TextView) convertView.findViewById(R.id.tv_SiSurveillance);
            holder.Euthanasie = (TextView) convertView.findViewById(R.id.tv_SiEuthanasie);

            holder.Lot = (TextView) convertView.findViewById(R.id.tv_lot);
            holder.Bac = (TextView) convertView.findViewById(R.id.tv_bac);
            holder.Euthanasie = (TextView) convertView.findViewById(R.id.tv_SiEuthanasie);
            holder.PoissonSouffrance = (TextView) convertView.findViewById(R.id.tv_SiPoissonsouffrance);


            holder.button =(ImageButton) convertView.findViewById(R.id.btn_delete);

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String key = liste.get(position).getId();
                    System.out.println("KEY____"+key);

                    WriteOnSheetSouffrance.deleteData(getContext(), key);
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
            holder = (SouffranceAdapter.BacViewHolder) convertView.getTag();
        }

        // remplir le ViewHolder avec les infos en cet emplacement de la liste
        Souffrance souffrance = liste.get(position);

        holder.Date.setText(souffrance.getDate());
        // holder.Id.setText(bac.getId());
        holder.Lignee.setText(souffrance.getLignee());
        holder.Responsable.setText(souffrance.getResponsable());

        holder.Age.setText(souffrance.getAge());
        holder.Aucune_action_a_mener.setText(souffrance.getAucune_action_a_mener());
        holder.Isolement.setText(souffrance.getIsolement());
        holder.Surveillance.setText(souffrance.getSurveillance());
        holder.Euthanasie.setText(souffrance.getEuthanasie());
        holder.Lot.setText(souffrance.getLot());

        holder.Bac.setText(souffrance.getBac());
        holder.Euthanasie.setText(souffrance.getEuthanasie());
        holder.PoissonSouffrance.setText(souffrance.getPoissonSouffrance());

        return convertView;
    }

}
