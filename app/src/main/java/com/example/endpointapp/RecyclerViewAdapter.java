package com.example.endpointapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<BacRegistre> data;


    public RecyclerViewAdapter(Context context, List<BacRegistre> data){
        this.context=context;
        this.data=data;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.cardview_item_bac, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){

        holder.tv_bac.setText(data.get(position).getNum());

        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);


        Date dateajd= new Date();
        Date date_lavage;

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dateajd);
        gc.add(GregorianCalendar.MONTH, -6);

        dateajd = gc.getTime();


        try {
            date_lavage = dateFormat.parse(data.get(position).getDateLavage());

            System.out.println(dateajd);

            holder.tv_bac.setBackgroundColor(data.get(position).isSelected() ? Color.GREEN : Color.LTGRAY);
            holder.tv_bac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.get(position).setSelected(!data.get(position).isSelected());
                    holder.tv_bac.setBackgroundColor(data.get(position).isSelected() ? Color.GREEN : Color.LTGRAY);
                }
            });

            if (dateajd.compareTo(date_lavage)>0){
                //holder.tv_bac.setTextColor(Color.BLACK);

                holder.tv_bac.setBackgroundColor(data.get(position).isSelected() ? Color.GREEN : Color.WHITE);
                holder.tv_bac.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.get(position).setSelected(!data.get(position).isSelected());
                        holder.tv_bac.setBackgroundColor(data.get(position).isSelected() ? Color.GREEN : Color.WHITE);
                    }
                });
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public int getItemCount(){
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_bac ;

        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setLongClickable(true);

            tv_bac = (TextView) itemView.findViewById(R.id.num_bac);


        }

    }

}
