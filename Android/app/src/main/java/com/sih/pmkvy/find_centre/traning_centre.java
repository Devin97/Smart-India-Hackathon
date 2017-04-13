package com.sih.pmkvy.find_centre;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.sih.pmkvy.R;
import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.adapter.centre_list_adapter;
import com.sih.pmkvy.ui.DividerItemDecoration;
import com.sih.pmkvy.centre_complete_info.*;


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
 * Created by test on 2/26/2017.
 */


public class traning_centre extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<find_centre> centre_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private centre_list_adapter centre_lists_adapter;
    private List<String> district;
    Spinner district_spinner, state_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traning_centre_activity_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        centre_lists_adapter = new centre_list_adapter(centre_list);

        district_spinner = (Spinner) findViewById(R.id.district_training_center);
        state_spinner = (Spinner) findViewById(R.id.state_training_center);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(centre_lists_adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            public void onClick(View view, int position) {


                Intent centre_info_intent = new Intent(view.getContext(), centre_info_display.class);
                TextView phone = (TextView) view.findViewById(R.id.centre_phone_no);
                centre_info_intent.putExtra("Phone", phone.getText());
                startActivity(centre_info_intent);

            }


            public void onLongClick(View view, int position) {

            }
        }));
        //get_request a = new get_request(getApplicationContext(), centre_list, centre_lists_adapter);
        //a.execute();
        district_spinner.setOnItemSelectedListener(this);
        state_spinner.setOnItemSelectedListener(this);

        List<String> state = new ArrayList<String>();
        state.add("Select State");
        state.add("CG");
        state.add("MP");

        ArrayAdapter<String> state_dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        state_spinner.setAdapter(state_dataAdapter);

         district = new ArrayList<>();
         //district.add("Select District");




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item;
        if (position >= 1) {
            if (parent.getId() == R.id.state_training_center) {

                item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                new get_request_state(getApplicationContext(), centre_list, centre_lists_adapter, item, district_spinner,district).execute();
            } else {
                item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                new get_request(getApplicationContext(), centre_list, centre_lists_adapter,item).execute();

            }

            //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


class get_request extends AsyncTask<String, Void, String> {
    String res;
    JSONObject json;
    boolean flag;
    Context context;
    String district;
    List<find_centre> center_list;
    centre_list_adapter centre_lists_adapter;

    public get_request(Context context, List<find_centre> center_list, centre_list_adapter centre_lists_adapter,String district) {
        this.district=district;
        this.context = context;
        this.centre_lists_adapter = centre_lists_adapter;
        this.center_list = center_list;

    }

    @Override
    protected void onPostExecute(String s) {

        //Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        StringBuilder center_name;
        StringBuilder center_address;
        StringBuilder center_phone;
        StringBuilder center_info;
        find_centre centre_add;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                center_name = new StringBuilder(obj.getString("training_center_name"));
                center_address = new StringBuilder(obj.getString("address"));
                center_phone = new StringBuilder(obj.getString("center_id"));
                center_info = new StringBuilder(obj.getString("center_id"));
                centre_add = new find_centre(center_name.toString(), center_address.toString(), center_phone.toString(), center_info.toString());
                center_list.add(centre_add);

            }
            //this.data.setText(ansobj.toString());
            //result=new String(ansobj.toString());
            centre_lists_adapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    @Override

    protected String doInBackground(String... params) {

        try {

            String link = context.getResources().getString(R.string.link) + "/api/trainingcenter/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("training_center_district",district);

            //json.put("data", add);


            wr.write(add.toString());
            wr.flush();
            res = add.toString();
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


class get_request_state extends AsyncTask<String, Void, String> {
    String res;
    JSONObject json;
    boolean flag;
    Context context;
    String state;
    Spinner district_spinner;
    List<find_centre> center_list;
    centre_list_adapter centre_lists_adapter;
    List<String> district;

    public get_request_state(Context context, List<find_centre> center_list, centre_list_adapter centre_lists_adapter, String state, Spinner district_spinner,List<String> district) {
        this.context = context;
        this.district_spinner = district_spinner;
        this.centre_lists_adapter = centre_lists_adapter;
        this.center_list = center_list;
        this.state = state;
        this.district=district;
    }

    @Override
    protected void onPostExecute(String s) {
        //
        StringBuilder district_name;



        try {
            district.add("Select District");
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("district");


            for (int i = 0; i < jsonArray.length(); i++) {

                //Toast.makeText(context.getApplicationContext(), jsonArray.get(i).toString(), Toast.LENGTH_LONG).show();
                district.add(jsonArray.get(i).toString());


            }
            ArrayAdapter<String> district_dataAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item, district);
            district_spinner.setAdapter(district_dataAdapter);

        } catch (Exception e) {

        }
    }

    @Override

    protected String doInBackground(String... params) {

        try {

            String link = context.getResources().getString(R.string.link) + "/api/districtlist/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("state", "chhattisgarh");

            //json.put("data", add);


            wr.write(add.toString());
            wr.flush();
            res = add.toString();
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