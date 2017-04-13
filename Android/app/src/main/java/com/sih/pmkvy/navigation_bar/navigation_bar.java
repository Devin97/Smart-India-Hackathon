package com.sih.pmkvy.navigation_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sih.pmkvy.R;

/**
 * Created by ASUS on 3/30/2017.
 */

public class navigation_bar extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar);
        String[] name = {"TEST", "TEST1", "TEST2", "TEST3"};
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.navigation_bar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, name);
        ListView drawList = (ListView) findViewById(R.id.drawer_list);
        drawList.setAdapter(adapter);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.id.);
    }
}
