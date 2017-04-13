package com.sih.pmkvy.browse_course;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by ASUS on 4/1/2017.
 */

public class browse_adapter extends RecyclerView.Adapter<browse_adapter.browse_course_view_holder> {

    public static class browse_course_view_holder extends RecyclerView.ViewHolder{
        TextView job_role,job_sector,course_name;
        public browse_course_view_holder(View itemView) {
            super(itemView);
            job_role=(TextView)itemView.findViewById(R.id.job_role_browse_course);
            job_sector=(TextView)itemView.findViewById(R.id.job_sector_browse_course);
            course_name=(TextView)itemView.findViewById(R.id.course_name_browse_course);

        }
    }
    List<browse_data> courses;

    public browse_adapter(List<browse_data> courses) {
        this.courses = courses;
    }

    @Override
    public browse_course_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_course_cardview,parent,false);
        browse_course_view_holder bcvh=new browse_course_view_holder(v);
        return bcvh;
    }

    @Override
    public void onBindViewHolder(browse_course_view_holder holder, int position) {
        holder.job_sector.setText(courses.get(position).job_secotr);
        holder.job_role.setText(courses.get(position).job_role);
        holder.course_name.setText(courses.get(position).course_name);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


}
