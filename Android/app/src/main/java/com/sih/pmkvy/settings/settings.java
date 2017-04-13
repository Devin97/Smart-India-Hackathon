package com.sih.pmkvy.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sih.pmkvy.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

/**
 * Created by hp on 23-03-2017.
 */


public class settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        EditText e_name,e_mail,e_contact,e_pincode;
        Button b1;
        Spinner s_state,s_district,s_city,s_lang;
        b1=(Button)findViewById(R.id.submit);
        b1.setOnClickListener(this);

        e_name=(EditText)findViewById(R.id.name);
        e_mail=(EditText)findViewById(R.id.email);
        e_contact=(EditText)findViewById(R.id.contact);
        e_pincode=(EditText)findViewById(R.id.pincode);

        String name = e_name.getText().toString();
        String mail = e_mail.getText().toString();
        String contact= e_contact.getText().toString();
        String pincode= e_pincode.getText().toString();

        s_state = (Spinner) findViewById(R.id.s1);
        s_district = (Spinner) findViewById(R.id.s2);
        s_city = (Spinner) findViewById(R.id.s3);
        s_lang = (Spinner) findViewById(R.id.s4);
        s_state.setOnItemSelectedListener(this);
        s_district.setOnItemSelectedListener(this);
        s_city.setOnItemSelectedListener(this);
        s_lang.setOnItemSelectedListener(this);


        List<String> states = new ArrayList<String>();
        states.add("Cg");
        states.add("Mp");
        states.add("Tm");
        states.add("Ap");
        states.add("Od");
        states.add("Mg");

        List<String> city = new ArrayList<String>();
        city.add("Raipur");
        city.add("Bhilai");

        List<String> district = new ArrayList<String>();
        district.add("Raipur");
        district.add("Durg");


        List<String> lang = new ArrayList<String>();
        lang.add("Hindi");
        lang.add("English");

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_state.setAdapter(stateAdapter);


        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, simple_spinner_item, city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_city.setAdapter(cityAdapter);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, simple_spinner_item, district);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_district.setAdapter(districtAdapter);

        ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this, simple_spinner_item, lang);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_lang.setAdapter(langAdapter);



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
       //Intent home=new Intent(this,homepage.class);
        //startActivity(home);
    }
}
