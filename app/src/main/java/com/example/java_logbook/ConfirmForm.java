package com.example.java_logbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ConfirmForm extends AppCompatActivity {
    private String _name, _property, _room, _furniture, _date, _price, _note;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.firebaseFirestore = FirebaseFirestore.getInstance();
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

        Button submit = findViewById(R.id.SubmitData);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _name = modelData.getName();
                _property = modelData.getPropertyType();
                _room = modelData.getRoom();
                _furniture = modelData.getFurnitureType();
                _price = modelData.getPrice();
                _date = modelData.getDate();
                _note = modelData.getNotes();

                addDataToFirestore(modelData);

            }
        });
    }
    private void addDataToFirestore(ModelData modelData) {


        CollectionReference dbBlogs = firebaseFirestore.collection("feedback");


        dbBlogs.add(modelData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(ConfirmForm.this, "Your Submit has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ConfirmForm.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
