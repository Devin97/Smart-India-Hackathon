package com.sih.pmkvy.course_provided_center;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by ASUS on 3/25/2017.
 */

public class course_provide_center_adapter extends RecyclerView.Adapter<course_provide_center_adapter.course_provided_view_holder> {

    public static class course_provided_view_holder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView job_sector, job_role, course_name,course_id;

        public course_provided_view_holder(View itemView) {
            super(itemView);
            job_sector = (TextView) itemView.findViewById(R.id.job_sector_cardview_course);
            job_role = (TextView) itemView.findViewById(R.id.job_role_cardview_course);
            course_name = (TextView) itemView.findViewById(R.id.course_name_course);
            course_id=(TextView)itemView.findViewById(R.id.course_id_course);
        }
    }

    List<center_provide_center_data> courses;

    public course_provide_center_adapter(List<center_provide_center_data> courses) {
        this.courses = courses;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public course_provided_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_provided_cardview, parent, false);
        course_provided_view_holder cpvh = new course_provided_view_holder(v);
        return cpvh;
    }

    @Override
    public void onBindViewHolder(course_provided_view_holder holder, int position) {
        holder.job_sector.setText(courses.get(position).job_sector);
        holder.job_role.setText(courses.get(position).job_role);
        holder.course_name.setText(courses.get(position).course_name);
        holder.course_id.setText(courses.get(position).course_id);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

}
