package com.sih.pmkvy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by ASUS on 3/28/2017.
 */

public class getdata extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        SharedPreferences sharedPreferences = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        String data = sharedPreferences.getString("NAME", null);
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
