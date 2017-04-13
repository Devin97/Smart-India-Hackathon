package com.sih.pmkvy.centre_complete_info;

import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.find_centre;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sih.pmkvy.course_provided_center.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ASUS on 3/12/2017.
 */

public class centre_info_display extends AppCompatActivity implements View.OnClickListener {
    String center_id;
    TextView center_name, center_address, center_district, center_state, poc_name, poc_email;
    ScrollView sc;
    Button course_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_centre_info);
        course_btn = (Button) findViewById(R.id.view_course_provided_center_btn);
        course_btn.setOnClickListener(this);
        center_id = getIntent().getStringExtra("Phone");
        Toast.makeText(this,center_id,Toast.LENGTH_SHORT).show();
        center_name = (TextView) findViewById(R.id.centre_name_info);
        center_address = (TextView) findViewById(R.id.center_address_details);
        center_district = (TextView) findViewById(R.id.center_district_details);
        center_state = (TextView) findViewById(R.id.center_state_info);
        poc_name = (TextView) findViewById(R.id.poc_name);
        poc_email = (TextView) findViewById(R.id.poc_email);
        sc = (ScrollView) findViewById(R.id.complete_center_info);
        new get_request(center_name, center_address, center_district, center_state, poc_name, poc_email, this, center_id, sc).execute();


    }

    @Override
    public void onClick(View v) {
        Intent course_provided = new Intent(this,course_provide_center_activity.class);
        course_provided.putExtra("center_id",center_id);
        startActivity(course_provided);

    }
}

class get_request extends AsyncTask<String, Void, String> {
    String name, email, pass;
    JSONObject json;
    public String result;
    String center_id;
    boolean flag;
    TextView center_name, center_address, center_district, center_state, poc_name, poc_email;
    Context context;
    ScrollView sc;

    public get_request(TextView center_name, TextView center_address, TextView center_district, TextView center_state, TextView poc_name, TextView poc_email, Context context, String center_id, ScrollView sc) {
        this.center_name = center_name;
        this.center_address = center_address;
        this.center_district = center_district;
        this.center_state = center_state;
        this.poc_name = poc_name;
        this.poc_email = poc_email;
        this.context = context;
        this.center_id = center_id;
        this.sc = sc;

    }

    @Override
    protected void onPostExecute(String s) {
        //TODO: Add validate check so that result is true or not
        //Toast.makeText(context.getApplicationContext(),s,Toast.LENGTH_LONG).show();
        try {
            JSONObject obj1 = new JSONObject(s);
            JSONObject obj = obj1.getJSONObject("data");
            //Toast.makeText(context.getApplicationContext(),obj.getString("address"),Toast.LENGTH_LONG).show();
            center_name.setText(obj.getString("training_center_name"));
            center_address.setText(obj.getString("address"));
            center_district.setText(obj.getString("training_center_district"));
            center_state.setText(obj.getString("training_center_state"));
            poc_name.setText(obj.getString("center_poc_name"));
            poc_email.setText(obj.getString("center_poc_email"));
            sc.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }

    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/singletrainingcenter/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("center_id", center_id);

            wr.write(add.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

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

