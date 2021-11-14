package com.devandroid.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.IDN;

public class LoginActivity extends AppCompatActivity {
    EditText Id, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Id = findViewById(R.id.etID);
        pass = findViewById(R.id.etPassword);
    }

    public void Login(View view) {
        String id = Id.getText().toString().trim();
        String pass1 = pass.getText().toString().trim();
        if (id.isEmpty()) {
            Id.setError("Field cannot be empty");
            Id.requestFocus();
            return;
        }
        if (pass1.isEmpty()) {
            pass.setError("Field cannot be empty");
            pass.requestFocus();
            return;
        }
        if (pass1.length() < 6) {
            pass.setError("Min password lenght should be 6 characters!");
            pass.requestFocus();
            return;
        }
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query check = reference.orderByChild("id").equalTo(id);
        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Id.setError(null);
                    Id.setEnabled(false);
                    String passdata = snapshot.child(id).child("pass").getValue(String.class);
                    if (passdata.equals(pass1)) {
                        Id.setError(null);
                        Id.setEnabled(false);
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        pass.setError("Wrong passWord");
                        pass.requestFocus();
                    }
                }
                else {
                    Id.setError("No such user exist");
                    Id.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Join(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}