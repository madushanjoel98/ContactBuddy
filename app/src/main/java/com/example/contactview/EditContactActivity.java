package com.example.contactview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactview.dto.Contact;
import com.example.contactview.storage.ContactDatabaseHelper;

public class EditContactActivity extends Activity {
ContactDatabaseHelper dbhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        dbhelp=new ContactDatabaseHelper(this);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
         long id = intent.getLongExtra("id",-1);
      Contact contact=  dbhelp.getContactById(id);


        final EditText nameEditText = findViewById(R.id.nameEditText);
        final EditText emailEditText = findViewById(R.id.emailEditText);
        final EditText phoneEditText = findViewById(R.id.phoneEditText);
        final EditText phone2EditText = findViewById(R.id.phone2EditText);
        final EditText lastnameEditText = findViewById(R.id.lastnameEditText);

        nameEditText.setText(contact.getName());
        lastnameEditText.setText(contact.getLastname());
        emailEditText.setText(contact.getEmail());
        phoneEditText.setText(contact.getPhoneNumber());
        phone2EditText.setText(contact.getPhoneNumber2());
        Intent intents = new Intent(this, MainActivity.class);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = nameEditText.getText().toString();
                String newlastName = lastnameEditText.getText().toString();
                String newEmail = emailEditText.getText().toString();
                String newPhoneNumber = phoneEditText.getText().toString();
                String newPhoneNumber2=phone2EditText.getText().toString();

                Contact contact=new Contact(id, newName,newEmail,newPhoneNumber,newlastName,newPhoneNumber2);
                contact.setId(id);
                dbhelp.updateContact(contact);
                startActivityForResult(intents,0);
                finish();
            }
        });
    }
}
