package com.desirecode.rms;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.List;

/*
 * actually we need we create 2 class
 * one is Adapter class which create for buys the data to the view  &
 * other one is ViewHolder class to holds the view*/

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private Context context;
    private List<ResultGetter>productList;

    public ResultAdapter(Context context, List<ResultGetter> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /*
       *
       *
       * my bigger stupid work & I wast about 8 hours for that because my coolness/carelessness
       *
       *
        LayoutInflater inflater = new LayoutInflater.from(context);
        View view=new inflater.inflate(R.layout.list_layout, null);*/

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_row, null);
        ResultViewHolder holder=new ResultViewHolder(view);
        return holder;
        //return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        //bind the data with our UI element (UI elements are create inside DataViewHolder class)
        ResultGetter product=productList.get(position);

        holder.textViewict1301.setText(product.getIct1301());
        holder.textViewict1302.setText(product.getIct1302());
        holder.textViewict1303.setText(product.getIct1303());
        holder.textViewcmt1301.setText(product.getCmt1301());
        holder.textViewcmt1303.setText(product.getCmt1303());
        holder.textViewcml1201.setText(product.getCml1201());
        holder.textViewcmt1005.setText(product.getCmt1005());


//        holder.imageView.setImageDrawable(context.getResources().getDrawable(product.));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        Image imageView;
        TextView textViewict1301,textViewict1302,textViewict1303,textViewcmt1301,textViewcmt1303,textViewcml1201,textViewcmt1005;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            // imageView=itemView.findViewById(R.id.imageView);
            textViewict1301=itemView.findViewById(R.id.textViewict1301);
            textViewict1302=itemView.findViewById(R.id.textViewict1302);
            textViewict1303=itemView.findViewById(R.id.textViewict1303);
            textViewcmt1301=itemView.findViewById(R.id.textViewcmt1301);
            textViewcmt1303=itemView.findViewById(R.id.textViewcmt1303);
            textViewcml1201=itemView.findViewById(R.id.textViewcml1201);
            textViewcmt1005=itemView.findViewById(R.id.textViewcmt1005);

        }
    }
}
