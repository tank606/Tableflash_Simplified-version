package com.example.tableflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class add_table extends AppCompatActivity  {
    int capacity =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);
        final TextView showCapacity = findViewById(R.id.capacity_num);


        //add
        ImageButton add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                capacity++;
                showCapacity.setText(String.valueOf(capacity));
            }
        });

        //minus
        ImageButton minus = findViewById(R.id.minusButton);
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(capacity >1){
                    capacity--;
                    showCapacity.setText(String.valueOf(capacity));
                }

            }
        });



        //Drop-down selection menu
        List<String> sizeSelection = new ArrayList<String>(Arrays.asList("0","1","2","3","4"));

        ArrayAdapter<String> selectLength = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, sizeSelection);
        selectLength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final Spinner spinner = findViewById(R.id.spinner_length);
        spinner.setAdapter(selectLength);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object length = adapterView.getItemAtPosition(position);
                if (length != null) {
                    String selectedItemText = (String) spinner.getItemAtPosition(position);
                    TextView lengthShow = findViewById(R.id.length);
                    lengthShow.setText(selectedItemText);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        //width
        List<String> sizeSelection_width = new ArrayList<String>(Arrays.asList("0","1","2","3","4"));

        ArrayAdapter<String> selectWidth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, sizeSelection_width);
        selectWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final Spinner spinner_width = findViewById(R.id.spinner_width);
        spinner_width.setAdapter(selectWidth);
        spinner_width.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object width = adapterView.getItemAtPosition(position);
                if (width != null) {
                    String selectedItemText = (String) spinner_width.getItemAtPosition(position);
                    TextView widthShow = findViewById(R.id.width);
                    widthShow.setText(selectedItemText);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });


        //button cancel , OK

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //ok: store data
        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View e) {

                TextView id_input = findViewById(R.id.editText);
                String id = id_input.getText().toString();

                TextView capacityShow = findViewById(R.id.capacity_num);
                String capacity = capacityShow.getText().toString();

                TextView length_display = findViewById(R.id.length);
                String length = length_display.getText().toString();
                TextView width_display = findViewById(R.id.width);
                String width = width_display.getText().toString();

                 if(id.matches("") || length.equals("0") || width.equals("0")){
                    Toast.makeText(getApplicationContext(), "Please check Table ID, length, width", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    Intent intent = new Intent(e.getContext(), MainActivity.class);

                    intent.putExtra("from","add_table");
                    intent.putExtra("table_id", id);
                    intent.putExtra("table_capacity", capacity);
                    intent.putExtra("table_length", length);
                    intent.putExtra("table_width", width);
                    Intent get = getIntent();
                    HashMap<String, table> tableIDtoTable= (HashMap<String, table>) get.getSerializableExtra("data");
                    intent.putExtra("data", tableIDtoTable);
                    startActivity(intent);
                }
            }
        });




    }
}
