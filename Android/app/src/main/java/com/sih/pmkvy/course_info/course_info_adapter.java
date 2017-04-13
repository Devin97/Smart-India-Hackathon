package com.sih.pmkvy.course_info;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by hp on 24-03-2017.
 */

public class course_info_adapter extends RecyclerView.Adapter<course_info_adapter.Cousre_info_view_holder> {


    public static class Cousre_info_view_holder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView start_info, start_data, end_info, end_data, seats_info, seats_data, status_data, status_info;

        public Cousre_info_view_holder(View itemView) {
            super(itemView);
            start_info = (TextView) itemView.findViewById(R.id.starting_info);
            start_data = (TextView) itemView.findViewById(R.id.starting_data);
            end_data = (TextView) itemView.findViewById(R.id.ending_data);
            end_info = (TextView) itemView.findViewById(R.id.ending_inf0);
            seats_data = (TextView) itemView.findViewById(R.id.seats_data);
            seats_info = (TextView) itemView.findViewById(R.id.seats_info);
            status_data = (TextView) itemView.findViewById(R.id.status_data);
            status_info = (TextView) itemView.findViewById(R.id.status_info);


        }
    }

    List<couse_info_data> courses;

    public course_info_adapter(List<couse_info_data> courses) {
        this.courses = courses;


    }

    @Override
    public Cousre_info_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_info_cardview, parent, false);
        Cousre_info_view_holder civh = new Cousre_info_view_holder(v);
        return civh;
    }

    @Override
    public void onBindViewHolder(Cousre_info_view_holder holder, int position) {

        holder.start_info.setText("Starting Date");
        holder.start_data.setText(courses.get(position).starting_date);
        holder.end_info.setText("Ending Date");
        holder.end_data.setText(courses.get(position).end_date);
        holder.seats_info.setText("Seats");
        holder.seats_data.setText(courses.get(position).seats);
        holder.status_info.setText("Status");
        holder.status_data.setText(courses.get(position).status);


    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
