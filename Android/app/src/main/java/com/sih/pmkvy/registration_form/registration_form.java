package com.sih.pmkvy.registration_form;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;


/**
 * Created by hp on 25-03-2017.
 */

public class registration_form extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText rname, remail, rcontact, rdob, rpincode, rfather, rincome, rstate, rdistrict, rstreet, rqualif, rworkexpinmonth, rworkindetail, rpreferedsec, rprefcourse, rmaxfees;
    Spinner rcategory;
    Button b2;
    RadioGroup gender, disable, relocate, agree, bpl;
    String name, mail, contact, pincode, fname, dob, income, street,
            district, state, qualification, workindetail, workinmonth, preferedsector, preferedcourse, maxfees;
    RadioButton gender_radio, disabled_radio, bpl_radio, relocate_radio, agree_radio;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        rname = (EditText) findViewById(R.id.candidate_name);
        remail = (EditText) findViewById(R.id.candidate_email);
        rpincode = (EditText) findViewById(R.id.candidate_pincode);
        rcontact = (EditText) findViewById(R.id.candidate_contact_number);
        rdob = (EditText) findViewById(R.id.candidate_dob);
        rfather = (EditText) findViewById(R.id.candidate_f_name);
        rincome = (EditText) findViewById(R.id.student_annual_family_income);
        rstreet = (EditText) findViewById(R.id.candidate_address);
        rstate = (EditText) findViewById(R.id.candidate_state_ut);
        rdistrict = (EditText) findViewById(R.id.candidate_district);
        rqualif = (EditText) findViewById(R.id.candidate_educational_qualification);
        rworkexpinmonth = (EditText) findViewById(R.id.candidate_work_experience_months);
        rworkindetail = (EditText) findViewById(R.id.candidate_work_experience_desc);
        rpreferedsec = (EditText) findViewById(R.id.candidate_preferred_sector);
        rprefcourse = (EditText) findViewById(R.id.student_preferred_course);
        rmaxfees = (EditText) findViewById(R.id.candidate_max_fees);


        b2 = (Button) findViewById(R.id.submit);
        b2.setOnClickListener(this);

        name = rname.getText().toString();
        mail = remail.getText().toString();
        contact = rcontact.getText().toString();
        pincode = rpincode.getText().toString();
        dob = rdob.getText().toString();
        fname = rfather.getText().toString();
        income = rincome.getText().toString();
        street = rstreet.getText().toString();
        district = rdistrict.getText().toString();
        state = rstate.getText().toString();
        qualification = rqualif.getText().toString();
        workinmonth = rworkexpinmonth.getText().toString();
        workindetail = rworkindetail.getText().toString();
        preferedsector = rpreferedsec.getText().toString();
        preferedcourse = rprefcourse.getText().toString();
        maxfees = rmaxfees.getText().toString();

        gender = (RadioGroup) findViewById(R.id.reg_gender);
        disable = (RadioGroup) findViewById(R.id.disable_reg);
        relocate = (RadioGroup) findViewById(R.id.relocate_reg);
        agree = (RadioGroup) findViewById(R.id.agree_reg);
        bpl = (RadioGroup) findViewById(R.id.bpl_reg);


        rcategory = (Spinner) findViewById(R.id.student_category);
        rcategory.setOnItemSelectedListener(this);


        List<String> states = new ArrayList<String>();
        states.add("General");
        states.add("OBC");
        states.add("SC");
        states.add("ST");


        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rcategory.setAdapter(stateAdapter);


    }

    @Override
    public void onClick(View v) {
        if (Check_data()) {
            int gender_id = gender.getCheckedRadioButtonId();
            gender_radio = (RadioButton) v.findViewById(gender_id);

            int disabled_id = disable.getCheckedRadioButtonId();
            boolean disabled=true;
            disabled_radio = (RadioButton) v.findViewById(disabled_id);
            /*if (disabled_radio.getText().toString().equalsIgnoreCase("Yes")) {
                disabled = true;
            } else {
                disabled = false;
            }*/

            int bpl_id = bpl.getCheckedRadioButtonId();
            bpl_radio = (RadioButton) v.findViewById(bpl_id);
            boolean bpl=true;
            /*if (bpl_radio.getText().toString().equalsIgnoreCase("Yes")) {
                bpl = true;
            } else {
                bpl = false;
            }*/

            int relocate_id = relocate.getCheckedRadioButtonId();
            relocate_radio = (RadioButton) v.findViewById(relocate_id);
            boolean relocate=true;
            /*if (relocate_radio.getText().toString().equalsIgnoreCase("Yes")) {
                relocate = true;
            } else {
                relocate = false;
            }*/

            int agree_id = agree.getCheckedRadioButtonId();
            agree_radio = (RadioButton) v.findViewById(agree_id);
            boolean agree=true;
            /*if (agree_radio.getText().toString().equalsIgnoreCase("Yes")) {
                agree = true;
            } else {
                agree = false;
            }*/


            //creating json object here
            JSONObject add = new JSONObject();

            try {
                add.put("c_name", name);
                add.put("c_email_id", "Random@gamail.com");
                add.put("c_alternate_email_id", "aksjhd");
                add.put("c_contact_number", contact);
                add.put("c_alternate_contact_number", "124343");
                add.put("c_address", street);
                add.put("c_state_ut", state);
                add.put("c_district", district);
                add.put("c_pincode",123);//Long.parseLong(pincode));
                add.put("c_alternate_address", "fasasf");
                add.put("c_alternate_state", "sadasf");
                add.put("c_alternate_district", "asfas");
                add.put("c_alternate_pincode", 32423);
                add.put("c_date_of_birth", dob);
                add.put("c_f_name", fname);
                add.put("c_gender", "Male");//gender_radio.getText().toString());
                add.put("c_category", "OBC");//rcategory.getSelectedItem().toString());
                add.put("c_differently_abled", 1);
                add.put("c_is_bpl",1);
                add.put("c_annual_family_income",1234);//Long.parseLong(income));
                add.put("c_educational_qualification",qualification);
                add.put("c_work_experience_months",12);//Integer.parseInt(workinmonth));
                add.put("c_work_experience_desc",workindetail);
                add.put("c_current_location_state_ut",state);
                add.put("c_current_location_district",district);
                add.put("c_preferred_training_state_ut","afs");
                add.put("c_sector",preferedsector);
                add.put("c_course",preferedcourse);
                add.put("c_is_ready_to_relocate",1);
                add.put("c_max_fee",213);//Long.parseLong(maxfees));
                add.put("c_is_agree",1);
                add.put("c_app_user_email","appuser1@gmail.com");



                new register_info_data(add,this).execute();
            } catch (JSONException e) {
                Log.d("Error", e.toString());
                e.printStackTrace();
            }
        }

        /*male=(RadioButton)findViewById(R.id.candidate_male);
        female=(RadioButton)findViewById(R.id.candidate_female);
        disable_true=(RadioButton)findViewById(R.id.candidate_disabled_true);
        disable_false=(RadioButton)findViewById(R.id.candidate_disabled_false);
        bpl_true=(RadioButton)findViewById(R.id.candidate_bpl_true);
        bpl_false=(RadioButton)findViewById(R.id.candidate_bpl_false);
        agree_true=(RadioButton)findViewById(R.id.candidate_agree_true);
        agree_false=(RadioButton)findViewById(R.id.candidate_agree_false);
        relocate_true=(RadioButton)findViewById(R.id.candidate_agree_true);
        relocate_false=(RadioButton)findViewById(R.id.candidate_agree_false);*/


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    boolean Check_data() {
        boolean flag = true;

        if (name == null || name.length() <= 3) {
            rname.setError("Name must be greater than 3 characters");
            flag = false;
        } else {
            rname.setError(null);
        }

        if (mail == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(remail.getText()).matches()) {
            flag = false;
            remail.setError("Invalid Email Addresss");

        } else {
            remail.setError(null);
        }

        if (dob == null) {
            flag = false;
            rdob.setError("Enter Correct dob");

        } else {
            rdob.setError(null);
        }

        if (fname == null || fname.length() <= 3) {
            flag = false;
            rfather.setError("Name must be greter than 3 letters");

        } else {
            rfather.setError(null);
        }
        if (pincode == null ) {
            flag = false;
            rpincode.setError("Enter Correct pincode");

        } else {
            rpincode.setError(null);
        }
        if (contact == null || contact.length() < 10) {
            flag = false;
            rcontact.setError("Enter Correct contact");

        } else {
            rcontact.setError(null);
        }

        if (income == null) {
            flag = false;
            rincome.setError("Enter correct income");

        } else {
            rincome.setError(null);
        }

        if (street == null) {
            flag = false;
            rstreet.setError("Enter correct detail");

        } else {
            rstreet.setError(null);
        }

        if (state == null) {
            flag = false;
            rstate.setError("Enter correct income");

        } else {
            rstate.setError(null);
        }

        if (district == null) {
            flag = false;
            rdistrict.setError("Enter correct income");

        } else {
            rdistrict.setError(null);
        }

        if (qualification == null) {
            flag = false;
            rqualif.setError("Enter correct income");

        } else {
            rqualif.setError(null);
        }

        if (workindetail == null) {
            flag = false;
            rworkindetail.setError("Enter correct income");

        } else {
            rworkindetail.setError(null);
        }

        if (workinmonth == null) {
            flag = false;
            rworkexpinmonth.setError("Enter correct income");

        } else {
            rworkexpinmonth.setError(null);
        }

        if (preferedcourse == null) {
            flag = false;
            rprefcourse.setError("Enter correct income");

        } else {
            rprefcourse.setError(null);
        }

        if (preferedsector == null) {
            flag = false;
            rpreferedsec.setError("Enter correct income");

        } else {
            rpreferedsec.setError(null);
        }

        if (maxfees == null) {
            flag = false;
            rmaxfees.setError("Enter correct income");

        } else {
            rmaxfees.setError(null);
        }

        return flag;
    }
}


class register_info_data extends AsyncTask<String, Void, String> {
    boolean flag;
    registration_form rf;
    Context context;
    JSONObject obj;
    public register_info_data(JSONObject obj, Context context) {
        this.context = context;
        this.obj=obj;

    }

    @Override
    protected void onPostExecute(String s) {


        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String link = "https://9a3a0b42.ngrok.io/api/candidateregister/";


            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());


            wr.write(obj.toString());
            wr.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                Log.d("LINE : ", line);


                sb.append(line);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}
