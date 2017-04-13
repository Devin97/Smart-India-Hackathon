package com.sih.pmkvy.signup;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sih.pmkvy.login.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.sih.pmkvy.login.*;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by test on 2/26/2017.
 */

public class signup_activity_student extends AppCompatActivity implements View.OnClickListener {

    private EditText student_name, student_email, student_password;
    private TextView already_member;
    private Button signup_button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_student);
        student_name = (EditText) findViewById(R.id.student_name_signup);
        student_email = (EditText) findViewById(R.id.student_email_signup);
        student_password = (EditText) findViewById(R.id.studnet_password_signup);
        signup_button = (Button) findViewById(R.id.btn_signup);
        already_member = (TextView) findViewById(R.id.already_member);
        already_member.setOnClickListener(this);
        signup_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.already_member) {
            Intent login=new Intent(this,login_activity_student.class);
        } else {
            boolean checkValidData = checkData();
            if (checkValidData) {
                Toast.makeText(v.getContext(),"LLLOL",Toast.LENGTH_SHORT).show();
                get_request req = new get_request(student_name.getText().toString(), student_email.getText().toString(), student_password.getText().toString(), v.getContext());
                req.execute();


                //(req.getStatus()!=AsyncTask.Status.FINISHED)
                //Toast.makeText(v.getContext(),"After Execution",Toast.LENGTH_SHORT).show();
                //TODO: send data to server to create account
            }
        }
    }

    private boolean checkData() {
        boolean valid = true;
        if (student_name == null || student_name.getText().equals("") || student_name.length() < 3) {
            student_name.setError("Name Should be grater than 3 Digits");
            valid = false;
        } else {
            student_name.setError(null);
        }
        if (student_email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(student_email.getText()).matches()) {
            valid = false;
            student_email.setError("Enter Valid Email Address");
        } else {
            student_email.setError(null);
        }

        if (student_password == null || student_password.length() < 4) {
            valid = false;
            student_password.setError("Password Should be Greater Than 4 AlpaNumeric ");
        } else {
            student_password.setError(null);
        }
        return valid;
    }
}


class get_request extends AsyncTask<String, Void, String> {
    String name, email, pass;
    JSONObject json1;
    public String result;
    boolean flag;
    Context context;

    public get_request(String name, String email, String pass, Context context) {

        this.name = name;
        this.email = email;
        this.pass = pass;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        if (flag)
            Toast.makeText(context.getApplicationContext(), "Account Created Successful", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context.getApplicationContext(), "Account Creation Failed" + s + json1.toString(), Toast.LENGTH_LONG).show();

    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/users/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json1 = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("user_name", name);
            add.put("user_password", pass);
            add.put("user_email", email);
            add.put("user_last_login", "2017-03-20");
            add.put("user_date_joined", "2017-03-20");
            add.put("user_registration_status", false);

            json1.put("data", add);
            json1 = add;


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





















/*import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.sih.pmkvy.R;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

public class signup_activity_student extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_student);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(signup_activity_student.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
*/
