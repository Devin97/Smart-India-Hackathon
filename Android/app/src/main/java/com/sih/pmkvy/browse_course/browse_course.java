package com.sih.pmkvy.browse_course;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.sih.pmkvy.R;
import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.ui.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/27/2017.
 */

public class browse_course extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView rv;
    List<browse_data> courses;
    Spinner job_role, job_sector;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_course_activity);
        job_role = (Spinner) findViewById(R.id.role_course_browse);

        job_sector = (Spinner) findViewById(R.id.sector_course_browse);
        job_role = (Spinner) findViewById(R.id.role_course_browse);
        job_role.setOnItemSelectedListener(this);
        job_sector.setOnItemSelectedListener(this);

        List<String> job_sector_l = new ArrayList<>();

        job_sector_l.add("Agriculture");
        job_sector_l.add("ITsec");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, job_sector_l);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        job_sector.setAdapter(arrayAdapter);


        rv = (RecyclerView) findViewById(R.id.recycler_browse_course);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        /*rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
        courses = new ArrayList<>();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.sector_course_browse:
                //Toast.makeText(view.getContext(),"HAHAHA",Toast.LENGTH_SHORT).show();
                String job_sector = parent.getItemAtPosition(position).toString();
                new get_request_role(view.getContext(), job_role, job_sector).execute();
                break;
            case R.id.role_course_browse:
                String job=parent.getItemAtPosition(position).toString();
                //Toast.makeText(view.getContext(),"TRY",Toast.LENGTH_SHORT).show();
                new get_request(view.getContext(),courses,rv,job).execute();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

class get_request extends AsyncTask<String, Void, String> {
    Context context;
    List<browse_data> courses;
    RecyclerView rv;
    String center_id;
    String job_role_n;
    JSONObject json;
    Boolean flag;

    public get_request(Context context, List<browse_data> courses, RecyclerView rv,String job_role_n) {
        this.job_role_n=job_role_n;
        this.context = context;
        this.courses = courses;
        this.rv = rv;
    }

    @Override
    protected void onPostExecute(String s) {

        //Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        String job_sector, job_role, course_name, course_id;
        //TODO: Add validate check so that result is true or not

        try {
            JSONObject obj1 = new JSONObject(s);
            JSONArray obj2 = obj1.getJSONArray("data");


            for (int i = 0; i < obj2.length(); i++) {

                JSONObject obj3 = obj2.getJSONObject(i);

                job_sector = obj3.getJSONObject("course_sector").getString("sector_skill_council_name");
                //Toast.makeText(context.getApplicationContext(),job_sector,Toast.LENGTH_LONG).show();
                job_role = obj3.getJSONObject("course_job_role").getString("job_role_name");
                course_name = obj3.getString("course_name");


                courses.add(new browse_data(job_sector, job_role, course_name));

            }

        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }
        browse_adapter ba = new browse_adapter(courses);
        rv.setAdapter(ba);
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/coursedata/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("job_role_name",job_role_n );

            wr.write(add.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                Log.d("LINE : ", line);
                if (line.equals("true")) {
                    //TODO: 3/20/2017 add response checking from server format is in jason
                    flag = true;
                } else
                    flag = false;

                sb.append(line);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}


class get_request_role extends AsyncTask<String, Void, String> {
    Context context;
    List<browse_data> courses;
    RecyclerView rv;
    String center_id;

    JSONObject json;
    Boolean flag;
    Spinner job_role;
    String job_sector;

    public get_request_role(Context context, Spinner job_role, String job_sector) {
        this.context = context;
        //Toast.makeText(context.getApplicationContext(), "INSIDE", Toast.LENGTH_SHORT).show();
        this.job_role = job_role;
        this.job_sector = job_sector;


    }

    @Override
    protected void onPostExecute(String s) {

        //Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        String job_sector, job_role, course_name, course_id;
        //TODO: Add validate check so that result is true or not
        List<String> roles=new ArrayList<>();
        try {
            JSONObject obj1 = new JSONObject(s);

            JSONArray obj2 = obj1.getJSONArray("data");



            for (int i = 0; i < obj2.length(); i++) {

                JSONObject obj3 = obj2.getJSONObject(i);

                job_role = obj3.getString("job_role_name");

                roles.add(job_role);


            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context.getApplicationContext(),android.R.layout.simple_spinner_item,roles);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.job_role.setAdapter(arrayAdapter);
        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/jobrolesector/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("sector_skill_council_name", job_sector);

            wr.write(add.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                Log.d("LINE : ", line);
                if (line.equals("true")) {
                    //TODO: 3/20/2017 add response checking from server format is in jason
                    flag = true;
                } else
                    flag = false;

                sb.append(line);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}

