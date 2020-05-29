package com.example.tableflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class table_setting extends AppCompatActivity {
    int capacity =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_setting);


        Intent get = getIntent();
        final HashMap<String, table> tableIDtoTable;
        tableIDtoTable = (HashMap<String, table>) get.getSerializableExtra("data");
        final String id = (String) get.getSerializableExtra("id");
        table cur_table = tableIDtoTable.get(id);
        EditText id_view = findViewById(R.id.table_setting_editText);
        id_view.setText(cur_table.getId());
        final TextView capacity_num = findViewById(R.id.table_setting_capacity_num);
        capacity_num.setText(String.valueOf(cur_table.getCapacity()));
        capacity =cur_table.getCapacity();

       final TextView length_num = findViewById(R.id.table_setting_length);

        final TextView width_num = findViewById(R.id.table_setting_width);

        ImageButton minus = findViewById(R.id.table_setting_minusButton);
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(capacity >1){
                    capacity--;
                    capacity_num.setText(String.valueOf(capacity));
                }

            }
        });
        ImageButton add = findViewById(R.id.table_setting_addButton);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                capacity++;
                capacity_num.setText(String.valueOf(capacity));
            }
        });



        List<String> sizeSelection = new ArrayList<String>(Arrays.asList("0","1","2","3","4"));
        ArrayAdapter<String> selectLength = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sizeSelection);
        selectLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = findViewById(R.id.table_setting_spinner_length);
        spinner.setAdapter(selectLength);
        spinner.setSelection(cur_table.getLength());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object length = adapterView.getItemAtPosition(position);
                if (length != null) {
                    String selectedItemText = (String) spinner.getItemAtPosition(position);
                    length_num.setText(selectedItemText);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        List<String> sizeSelection_width = new ArrayList<String>(Arrays.asList("0","1","2","3","4"));

        ArrayAdapter<String> selectWidth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, sizeSelection_width);
        selectWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        final Spinner spinner_width = findViewById(R.id.table_setting_spinner_width);
        spinner_width.setAdapter(selectWidth);
        spinner_width.setSelection(cur_table.getWidth());
        spinner_width.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    String selectedItemText = (String) spinner_width.getItemAtPosition(position);
                    width_num.setText(selectedItemText);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });


        Button cancel = findViewById(R.id.table_setting_Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button ok = findViewById(R.id.table_setting_save);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), table_details.class);
                Intent get = getIntent();
                HashMap<String, table> map;
                map = (HashMap<String, table>) get.getSerializableExtra("data");
                if (map != null) {
                    map.remove(id);
                }

                TextView id_input = findViewById(R.id.table_setting_editText);
                String id = id_input.getText().toString();
                TextView capacity_num = findViewById(R.id.table_setting_capacity_num);
                String capacity = capacity_num.getText().toString();
                TextView length_input = findViewById(R.id.table_setting_length);
                String length = length_input.getText().toString();
                TextView width_input = findViewById(R.id.table_setting_width);
                String width = width_input.getText().toString();

                if(id.matches("") || length.equals("0") || width.equals("0")){
                    Toast.makeText
                            (getApplicationContext(), "Please check Table ID, length, width", Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    map.put(id,new table(id,Integer.parseInt(capacity),Integer.parseInt(length),Integer.parseInt(width)));
                    intent.putExtra("data", map);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });

        Button remove = findViewById(R.id.table_setting_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                Intent get = getIntent();
                HashMap<String, table> map;
                map = (HashMap<String, table>) get.getSerializableExtra("data");
                if (map != null) {
                    map.remove(id);
                }
                intent.putExtra("data", map);
                intent.putExtra("from","table_setting");
                startActivity(intent);
            }
        });




    }
}
