package com.example.java_logbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button submitData, viewData;
    EditText name, notes, price;
    TextView dateTime;
    Spinner propertyType, furnitureType, room;
    boolean validation = false;
    final Calendar calendar = Calendar.getInstance();
    ModelData model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }
        // Set property list
        final ArrayList<String> listProperty = new ArrayList<>();
        listProperty.add("Select one Property");
        listProperty.add("Flat House");
        listProperty.add("Apartment");
        listProperty.add("Farm");
        listProperty.add("Building");
        //set room list
        final ArrayList<String> listRoom = new ArrayList<>();
        listRoom.add("Select one Room");
        listRoom.add("Single Room");
        listRoom.add("Double Room");
        listRoom.add("Studio Room");
        //set furniture list
        final ArrayList<String> listFurniture = new ArrayList<>();
        listFurniture.add("Select one Furniture");
        listFurniture.add("Furnished");
        listFurniture.add("Unfurnished");
        listFurniture.add("Part Furnished");

        model = intent.getSerializableExtra("data") == null ? new ModelData() :
                (ModelData) intent.getSerializableExtra("data");
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat time = new SimpleDateFormat(formatDate, Locale.UK);
        model.setDate(time.format(new Date()));
        //Name
        name = findViewById(R.id.Name);
        name.setText(model.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int ii, int iii) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int ii, int iii) {
                if(charSequence.length() == 0){
                    name.setError("Name is required!");
                }
                else {
                    model.setName(name.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
        //Property
        propertyType = findViewById(R.id.Property);
        ArrayAdapter<String> properAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, listProperty);
        properAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        propertyType.setAdapter(properAdapter);
        propertyType.setSelection(model.getPropertyType().equals(" ") ? 0 :
                listProperty.indexOf(model.getPropertyType()));
        propertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String propertySelected = listProperty.get(i);
                model.setPropertyType(propertySelected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
        //Room
        room = findViewById(R.id.Room);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, listRoom);
        roomAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        room.setAdapter(roomAdapter);
        room.setSelection(model.getRoom().equals(" ") ? 0 : listRoom.indexOf(model.getRoom()));
        room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String roomSelected = listRoom.get(i);
                model.setRoom(roomSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Furniture
        furnitureType = findViewById(R.id.Furniture);
        ArrayAdapter<String> furAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, listFurniture);
        furAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        furnitureType.setAdapter(furAdapter);
        furnitureType.setSelection(model.getFurnitureType().equals(" ") ? 0 : listFurniture.indexOf(model.getFurnitureType()));
        furnitureType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String furSelected = listFurniture.get(i);
                if(furSelected.equals("Select One!")){
                    model.setFurnitureType(" ");
                }
                else {
                    model.setFurnitureType(furSelected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Price
        price = findViewById(R.id.Price);
        price.setText(String.valueOf(model.getPrice()));
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int ii, int iii) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int ii, int iii) {
                if(charSequence.length()==0){
                    price.setError("Price is required!");
                }
                else if(charSequence.toString().contains(".") && charSequence.toString().substring(
                        charSequence.toString().indexOf(".")
                ).length() > 3) {
                    price.setError("Only accept 2 digits after period!");
                }
                else if(Double.parseDouble(charSequence.toString()) < 0){
                    price.setError("Doume nhap ngu vl");
                }
                else {
                    model.setPrice(price.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Notes
        notes = findViewById(R.id.Notes);
        notes.setText(model.getNotes());
        notes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int ii, int iii) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int ii, int iii) {
                if(charSequence.length() > 500){
                    notes.setError("The maximum length are 500!");
                }
                else if(charSequence.length() == 0){
                    notes.setError("Please input your notes!");
                }
                else {
                    model.setNotes(notes.getText().toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Date
        dateTime = findViewById(R.id.Date);
        dateTime.setText(model.getDate());
        Button pickDate = findViewById(R.id.pickDate);
        DatePickerDialog.OnDateSetListener dateTime = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONDAY, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        pickDate.setOnClickListener(view -> {
            DatePickerDialog dateDialog = new DatePickerDialog(MainActivity.this, dateTime, calendar.get
                    (Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
            );
            dateDialog.getDatePicker().setMaxDate(new Date().getTime());
            dateDialog.show();
        });
        //
        submitData = findViewById(R.id.submit);
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmScreen(view);
            }
        });

        viewData = findViewById(R.id.viewPost);
        viewData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ViewPost.class);
                startActivity(i);
            }
        });


    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateTime.setText(sdf.format(calendar.getTime()));
        model.setDate(sdf.format(calendar.getTime()));
    }

    private boolean validationSubmit()
    {
        if (name.length() <= 0)
        {
            name.setError("Name is Required!");
            return false;
        }
        if(propertyType.getSelectedItem().toString().equals("Select one Property"))
        {
            TextView errorMessage = (TextView) propertyType.getSelectedView();
            errorMessage.setError("Property is required");
            return false;
        }
        if(room.getSelectedItem().toString().equals("Select one Room"))
        {
            TextView errorMessage = (TextView) room.getSelectedView();
            errorMessage.setError("Room is required");
            return false;
        }
        if(furnitureType.getSelectedItem().toString().equals("Select one Furniture"))
        {
            TextView errorMessage = (TextView) furnitureType.getSelectedView();
            errorMessage.setError("Furniture is required");
            return false;
        }
        if (price.length() <= 0)
        {
            price.setError("Please input Price");
            return false;
        }
        if (notes.length() > 500)
        {
            notes.setError("The maximum length are 500!");
            return false;
        }
        return true;
    }

    public void confirmScreen(View view)
    {
        validation = validationSubmit();
        if(validation)
        {
            Intent i = new Intent(MainActivity.this, ConfirmForm.class);
            i.putExtra("data", model);
            startActivity(i);
        }
    }
}