package com.example.java_logbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ConfirmForm extends AppCompatActivity {
    public static final String NAME_KEY = "name";
    public static final String PROPERTY_KEY = "property";
    public static final String ROOM_KEY = "room";
    public static final String FURNITURE_KEY = "furniture";
    public static final String PRICE_KEY = "price";
    public static final String NOTE_KEY = "note";
    public static final String DATE_KEY = "date";

    private DocumentReference documentReference = FirebaseFirestore.getInstance().document("blogs/data");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent i = getIntent();
        ModelData modelData = (ModelData) i.getSerializableExtra("data");
        TextView textView = (TextView) findViewById(R.id.Confirm);
        textView.setTextSize(15);
        textView.setText(Html.fromHtml(
                "Your Name: " + "<b>" + modelData.getName() + "</b>" + "<br>" +
                        "Property Type: " + "<b>" + modelData.getPropertyType() + "</b>" + "<br>" +
                        "Room: " + "<b>" + modelData.getRoom() + "</b>" + "<br>" +
                        "Furniture: " + "<b>" + modelData.getFurnitureType() + "</b>" + "<br>" +
                        "Price: " + "<b>" + modelData.getPrice() + "</b>" + "<br>" +
                        "Notes: " + "<b>" + modelData.getNotes() + "</b>" + "<br>" +
                        "Select Date: " + "<b>" + modelData.getDate() + "</b>" + "<br>"
        ));


        Button back = findViewById(R.id.BackToList);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(ConfirmForm.this, MainActivity.class);
            intent.putExtra("data", modelData);
            startActivity(intent);
        });
    }
    public void saveBlog(View view)
    {
        EditText nameView = (EditText) findViewById(R.id.Name);
        Spinner propertyView = (Spinner) findViewById(R.id.Property);
        Spinner roomView = (Spinner) findViewById(R.id.Room);
        Spinner furnitureView = (Spinner) findViewById(R.id.Furniture);
        EditText priceView = (EditText) findViewById(R.id.Price);
        EditText noteView = (EditText) findViewById(R.id.Notes);
        TextView dateView = (TextView) findViewById(R.id.Date);

        String nameText = nameView.getText().toString();
        String propertyText = propertyView.getSelectedItem().toString();
        String roomText = roomView.getSelectedItem().toString();
        String furnitureText = furnitureView.getSelectedItem().toString();
        String priceText = priceView.getText().toString();
        String noteText = noteView.getText().toString();
        String dateText = dateView.getText().toString();

        if (nameText.isEmpty() || propertyText.isEmpty() ||
                roomText.isEmpty() || furnitureText.isEmpty() ||
                priceText.isEmpty() || noteText.isEmpty() || dateText.isEmpty()) {
            return;
        }
        Map<String, Object> dataSave = new HashMap<String, Object>();
        dataSave.put(NAME_KEY, nameText);
        dataSave.put(PROPERTY_KEY, propertyText);
        dataSave.put(ROOM_KEY, roomText);
        dataSave.put(FURNITURE_KEY, furnitureText);
        dataSave.put(PRICE_KEY, priceText);
        dataSave.put(NOTE_KEY, noteText);
        dataSave.put(DATE_KEY, dateText);
        documentReference.set(dataSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("blogs" ,"Saved!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("blogs", "Not Saved!", e);
            }
        });


    }
}
