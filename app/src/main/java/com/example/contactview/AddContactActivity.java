
/*
 * madushan joel 2023.
 */

/*
 * madushan joel 2023.
 */

/*
 * madushan joel 2023.
 */

package com.example.contactview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
        EditText phone2EditText = findViewById(R.id.phone2EditText);
        EditText lasnameText = findViewById(R.id.lastnameEditText);

        String name = nameEditText.getText().toString();
        String last_name = lasnameText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneEditText.getText().toString();
        String phoneNumber2 = phone2EditText.getText().toString();

        if(TextUtils.isEmpty(name)) {
            nameEditText.setError("Name is empty");
            return;
        }
//        if(TextUtils.isEmpty(email)) {
//            emailEditText.setError("Email is empty");
//            return;
//        }
        if(TextUtils.isEmpty(phoneNumber)) {
            phoneEditText.setError("Phone number is  empty");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            return;
        }

        Contact contact=new Contact(name,email,phoneNumber,last_name,phoneNumber2);
    int id= (int) contactDatabaseHelper.addContact(contact);
        Toast.makeText(this,"ID:"+ String.valueOf(id), Toast.LENGTH_SHORT).show();
        Intent intents = new Intent(this, MainActivity.class);
        startActivityForResult(intents,0);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intents = new Intent(this, MainActivity.class);
        startActivityForResult(intents,0);
        finish();
        super.onBackPressed();
    }
}
