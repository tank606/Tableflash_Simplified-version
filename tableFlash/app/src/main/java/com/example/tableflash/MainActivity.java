package com.example.tableflash;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,NavigationView.OnNavigationItemSelectedListener{

    HashMap<String,table> tableIDtoTable =new HashMap<>();

    DrawerLayout homePage;
    BottomNavigationView bottomNavigate;
    NavigationView navigation;
    Toolbar bar;
    ActionBarDrawerToggle actionBar;



    @SuppressLint({"RestrictedApi", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add table
        Button addTable = findViewById(R.id.addTable);
        addTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), add_table.class);
                intent.putExtra("data", tableIDtoTable);
                startActivity(intent);
            }
        });


        //show table
        Intent tableShow = getIntent();
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            Intent get = getIntent();
            tableIDtoTable = (HashMap<String, table>) get.getSerializableExtra("data");
            if(tableShow.getSerializableExtra("from").toString().equals("add_table")) {
                String table_id = tableShow.getSerializableExtra("table_id").toString();
                int table_capacity = Integer.parseInt(tableShow.getSerializableExtra("table_capacity").toString());
                int table_length = Integer.parseInt(tableShow.getSerializableExtra("table_length").toString());
                int table_width = Integer.parseInt(tableShow.getSerializableExtra("table_width").toString());
                table temp_table = new table(table_id, table_capacity, table_length, table_width);
                tableIDtoTable.put(table_id, temp_table);
            }
            LinearLayout table_layout = findViewById(R.id.fragment_container);
            for(Map.Entry<String,table> e : tableIDtoTable.entrySet()){

                Button tableList = new Button(this);
                tableList.setBackgroundColor(Color.WHITE);
                tableList.setText("Table#: "+e.getKey()+"        ||    Capacity:"+e.getValue().getCapacity());

                final String table_id = e.getKey();
                tableList.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), table_details.class);
                        intent.putExtra("data", tableIDtoTable);
                        intent.putExtra("id",table_id);
                        startActivity(intent);
                    }
                });

                table_layout.setOrientation(LinearLayout.VERTICAL);
                table_layout.addView(tableList);
                TextView tv =new TextView(this);
                table_layout.addView(tv);
            }

        }


        bottomNavigate = findViewById(R.id.navigation);
        bottomNavigate.setOnNavigationItemSelectedListener(this);
        homePage = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigationView);
        bar = findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar = new ActionBarDrawerToggle(this, homePage, bar,R.string.drawerOpen,R.string.drawerClose);
        homePage.addDrawerListener(actionBar);
        actionBar.syncState();
        navigation.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.action_dinein:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}




































