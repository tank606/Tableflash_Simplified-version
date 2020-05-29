package com.example.tableflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class table_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_details);

        Intent get = getIntent();
        final HashMap<String, table> tableIDtoTable;
        tableIDtoTable = (HashMap<String, table>) get.getSerializableExtra("data");
        final String id = (String) get.getSerializableExtra("id");
        TextView details_id=findViewById(R.id.details_id);
        details_id.setText(id);

        Button cancel = findViewById(R.id.details_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("data", tableIDtoTable);
                intent.putExtra("from","table_details");
                startActivity(intent);
            }
        });
        Button ok = findViewById(R.id.details_ok);
        //It should have been used to update changes to the menu. Now, this app has not yet put in the menu data.
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("data", tableIDtoTable);
                intent.putExtra("from","table_details");
                startActivity(intent);
            }
        });

        Button table_setting = findViewById(R.id.details_setting);
        table_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), table_setting.class);
                intent.putExtra("data", tableIDtoTable);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}
