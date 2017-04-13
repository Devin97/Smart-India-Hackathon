package com.sih.pmkvy.student_dashboard;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by ASUS on 3/31/2017.
 */

public class dashboardadapter extends RecyclerView.Adapter<dashboardadapter.dashboard_viewholder> {


    public static class dashboard_viewholder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView job_role, sector_skill;

        public dashboard_viewholder(View itemView) {
            super(itemView);
            job_role = (TextView) itemView.findViewById(R.id.dashboard_job_role);
            sector_skill = (TextView) itemView.findViewById(R.id.secotr_skill_dashboard);
        }
    }

    List<dashboarddata> dashboards;

    public dashboardadapter(List<dashboarddata> dashboards) {
        this.dashboards = dashboards;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public dashboard_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_cardview, parent, false);
        dashboard_viewholder dashboardViewholder = new dashboard_viewholder(v);
        return dashboardViewholder;
    }

    @Override
    public void onBindViewHolder(dashboard_viewholder holder, int position) {
        holder.job_role.setText(dashboards.get(position).job_role);
        holder.sector_skill.setText(dashboards.get(position).sector_skill);
    }

    @Override
    public int getItemCount() {
        return dashboards.size();
    }
}
