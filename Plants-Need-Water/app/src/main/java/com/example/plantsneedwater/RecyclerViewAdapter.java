package com.example.plantsneedwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private List<Plant> mData = PlantDataHolder.plantList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    //Data is passed into the constructor
    RecyclerViewAdapter(Context context, List<Plant> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    //Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    //Binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap pimg = mData.get(position).getPlantImage();
        String pName = mData.get(position).getPlantName();
        String pLastWatered = mData.get(position).getPlantLastWatered().toString();
        String pNextWater = mData.get(position).getPlantNextWater().toString();

        holder.plantImage.setImageBitmap(pimg);
        holder.plantName.setText(pName);
        holder.plantLastWatered.setText(pLastWatered);
        holder.plantNextWater.setText(pNextWater);
    }

    //Total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    //Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView plantImage;
        TextView plantName;
        TextView plantLastWatered;
        TextView plantNextWater;

        ViewHolder(View itemView) {
            super(itemView);
            plantImage = itemView.findViewById(R.id.plantImage);
            plantName = itemView.findViewById(R.id.plantName);
            plantLastWatered = itemView.findViewById(R.id.plantLastWatered);
            plantNextWater = itemView.findViewById(R.id.plantNextWater);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    //Convenience method for getting data at click position
    Plant getItem(int id) {
        return mData.get(id);
    }

    //Allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}