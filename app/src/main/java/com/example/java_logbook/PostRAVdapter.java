package com.example.java_logbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PostRAVdapter extends RecyclerView.Adapter<PostRAVdapter.ViewHolder> {
    private ArrayList<ModelData> coursesArrayList;
    private Context context;

    public PostRAVdapter(ArrayList<ModelData> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostRAVdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.activity_view_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostRAVdapter.ViewHolder holder, int position) {
        ModelData courses = coursesArrayList.get(position);
        holder.name.setText("Your Name: " + courses.getName());
        holder.property.setText("Property Type: " + courses.getPropertyType());
        holder.room.setText("Room Type: " + courses.getRoom());
        holder.furniture.setText("Furniture Type: " + courses.getFurnitureType());
        holder.price.setText("Price: $" + courses.getPrice());
        holder.note.setText("Notes: " + courses.getNotes());
        holder.date.setText("Date: " + courses.getDate());
    }

    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView property;
        private final TextView room;
        private final TextView furniture;
        private final TextView price;
        private final TextView note;
        private final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            property = itemView.findViewById(R.id.Property);
            room = itemView.findViewById(R.id.Room);
            furniture = itemView.findViewById(R.id.Furniture);
            price = itemView.findViewById(R.id.Price);
            note = itemView.findViewById(R.id.Notes);
            date = itemView.findViewById(R.id.Date);
        }
    }
}
