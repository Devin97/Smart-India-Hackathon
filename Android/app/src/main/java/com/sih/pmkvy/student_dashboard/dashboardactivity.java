package com.sih.pmkvy.student_dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sih.pmkvy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/31/2017.
 */

public class dashboardactivity extends AppCompatActivity {
    List<dashboarddata> dashboards;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        recyclerView = (RecyclerView) findViewById(R.id.dashboard_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.setTitle("Dashboard");
        initializedata();
        initializeadapter();
    }

    private void initializeadapter() {
        dashboardadapter adapter=new dashboardadapter(dashboards);
        recyclerView.setAdapter(adapter);
    }

    private void initializedata() {
        dashboards=new ArrayList<>();
        dashboards.add(new dashboarddata("TESTING","TEST"));
        dashboards.add(new dashboarddata("Test","TRY"));
        dashboards.add(new dashboarddata("AGAIN","SURE"));
    }
}
