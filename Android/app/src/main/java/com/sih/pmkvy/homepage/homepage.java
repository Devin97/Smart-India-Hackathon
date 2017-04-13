package com.sih.pmkvy.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.find_centre.find_centre;
import com.sih.pmkvy.gps_location.*;
import com.sih.pmkvy.course_info.course_info_activity;
import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.signup.signup_activity_student;
import com.sih.pmkvy.login.*;
import com.sih.pmkvy.homepage.*;
import com.sih.pmkvy.settings.*;
import com.sih.pmkvy.course_provided_center.*;
import com.sih.pmkvy.registration_form.*;
import com.sih.pmkvy.feedback.*;
import com.sih.pmkvy.about_pmkvy.*;
import com.sih.pmkvy.browse_course.*;

/**
 * Created by hp on 21-03-2017.
 */

public class homepage extends AppCompatActivity implements View.OnClickListener {
    Button about_pmkvy, find_training_center, browse_course, feedback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        about_pmkvy = (Button) findViewById(R.id.about_pmkvy_homepage);
        about_pmkvy.setOnClickListener(this);
        find_training_center = (Button) findViewById(R.id.find_training_center_homepage);
        find_training_center.setOnClickListener(this);
        browse_course = (Button) findViewById(R.id.browse_course_homepage);
        browse_course.setOnClickListener(this);
        feedback = (Button) findViewById(R.id.feedback_homepage);
        feedback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent feedback = new Intent(this, feedback.class);
        Intent about = new Intent(this, about_pmkvy.class);
        Intent find_training = new Intent(this, traning_centre.class);
        Intent browse_courses = new Intent(this, browse_course.class);
        switch (v.getId()) {
            case R.id.about_pmkvy_homepage:
                startActivity(about);
                break;
            case R.id.feedback_homepage:
                startActivity(feedback);
                break;
            case R.id.find_training_center_homepage:
                startActivity(find_training);
                break;
            case R.id.browse_course_homepage:
                startActivity(browse_courses);
                break;

        }
    }
}
