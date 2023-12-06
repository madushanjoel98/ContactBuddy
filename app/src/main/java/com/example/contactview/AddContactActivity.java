package com.example.contactview;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactview.dto.Contact;
import com.example.contactview.storage.ContactDatabaseHelper;

public class AddContactActivity extends Activity {
ContactDatabaseHelper contactDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        contactDatabaseHelper=new ContactDatabaseHelper(this);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneEditText.getText().toString();

        if(TextUtils.isEmpty(name)) {
            nameEditText.setError("Name is empty");
            return;
        }
        if(TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is empty");
            return;
        }
        if(TextUtils.isEmpty(phoneNumber)) {
            phoneEditText.setError("Phone number is  empty");
            return;
        }

        Contact contact=new Contact( name,email,phoneNumber);
    int id= (int) contactDatabaseHelper.addContact(contact);
        Toast.makeText(this,"ID:"+ String.valueOf(id), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.putExtra("name", name);
//        intent.putExtra("email", email);
//        intent.putExtra("phoneNumber", phoneNumber);
//        setResult(RESULT_OK, intent);
        finish();
    }
}
