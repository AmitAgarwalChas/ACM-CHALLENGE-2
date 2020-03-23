package com.dev5151.acmchallenge2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev5151.acmchallenge2.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AmitActivity extends AppCompatActivity {

    Button submit;
    TextInputEditText name, age, email, contact;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amit);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        initView();

        name.addTextChangedListener(mTextWatcher);
        age.addTextChangedListener(mTextWatcher);
        email.addTextChangedListener(mTextWatcher);
        contact.addTextChangedListener(mTextWatcher);
        if(submit.isEnabled()){
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mail = email.getText().toString().trim();
                    String Age = age.getText().toString();
                    String Name = name.getText().toString();
                    String phone = contact.getText().toString();
                    String id = databaseReference.push().getKey();
                    User user = new User(id, Name, Age, phone, mail);
                    databaseReference.child(id).setValue(user);
                    startActivity(new Intent(getApplicationContext(), ViewingActivity.class));
                    finish();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "One or more fields empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        submit = findViewById(R.id.btn_submit);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.phone);

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkEmpty();
        }
    };

    public void checkEmpty(){
        String mail = email.getText().toString().trim();
        String Age = age.getText().toString();
        String Name = name.getText().toString();
        String phone = contact.getText().toString();
        if(mail.isEmpty() || Name.isEmpty() || Age.isEmpty() || phone.isEmpty()){
            submit.setEnabled(false);
        }
        else{
            submit.setEnabled(true);
        }
    }

}
