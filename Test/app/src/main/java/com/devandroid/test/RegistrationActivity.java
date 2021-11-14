package com.devandroid.test;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistrationActivity extends AppCompatActivity {
    EditText ed_id,ed_pass,check_pass,ed_email,ed_day;
    Button check_id,register,back;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initui();
        ed_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(RegistrationActivity.this, 0, ss, mYear, mMonth, mDay);
                d.show();
            }
        });
        initListener();
      /*  check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idd=ed_id.getText().toString().trim();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference my=database.getReference("User");
                my.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {

                        String passdata = snapshot.child("id").getValue(String.class);
                      if(idd==passdata){
                          ed_id.setError("Lỗi");
                          ed_id.requestFocus();
                      }
                      else {
                          ed_id.setError(null);
                          ed_id.setEnabled(false);
                      }

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
            }
        });
       */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initListener() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
    }

    private void onClickSignUp() {
        String idd=ed_id.getText().toString().trim();
        String pass=ed_pass.getText().toString().trim();
        String email=ed_email.getText().toString().trim();
        String day=ed_day.getText().toString().trim();
        String check=check_pass.getText().toString().trim();
        if(idd.isEmpty()){
            ed_id.setError("Id is required!");
            ed_id.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            ed_pass.setError("password is required!");
            ed_pass.requestFocus();
            return;
        }
        if (pass.length()<6){
            ed_pass.setError("Min password lenght should be 6 characters!");
            ed_pass.requestFocus();
            return;
        }
        if (check.isEmpty()){
            check_pass.setError("Re-enter Password is requierd!");
            check_pass.requestFocus();
            return;
        }
        if (!check.equals(pass)){
            check_pass.setError("password does not match!");
            check_pass.requestFocus();
            return;
        }
        if (email.isEmpty()){
            ed_email.setText("Email is required! ");
            ed_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ed_email.setError("Please provide vaild email!");
            ed_email.requestFocus();
            return;
        }
        if(day.isEmpty()){
            ed_day.setError("Brithday is required!");
        }
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference my=database.getReference("User");
        my.child(idd).setValue(new User(idd,email,day,pass), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull  DatabaseReference ref) {
                Toast.makeText(RegistrationActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
      //
    }

    private void initui() {
        ed_id=findViewById(R.id.etID1);
        ed_pass=findViewById(R.id.etPassword);
        ed_email=findViewById(R.id.etEmail);
        ed_day=findViewById(R.id.etday);
        check_pass=findViewById(R.id.etReenterPassword);
        check_id=findViewById(R.id.checkId);
        register=findViewById(R.id.btnRegister);
        back=findViewById(R.id.btnBack);
        progressBar=findViewById(R.id.progresss);
    }
    DatePickerDialog.OnDateSetListener ss = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            ed_day.setText(sdf.format(c.getTime()));
        }
    };

}