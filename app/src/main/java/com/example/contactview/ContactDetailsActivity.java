package com.example.contactview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.contactview.dto.Contact;
import com.example.contactview.storage.ContactDatabaseHelper;

public class ContactDetailsActivity extends Activity {
ContactDatabaseHelper contactDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        contactDatabaseHelper=new ContactDatabaseHelper(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id",-1);
        Contact contact=contactDatabaseHelper.getContactById(id);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        nameTextView.setText(contact.getName());
        emailTextView.setText(contact.getEmail());
        phoneTextView.setText(contact.getPhoneNumber());
    }
}
