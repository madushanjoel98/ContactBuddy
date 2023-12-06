package com.example.contactview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactview.dto.Contact;
import com.example.contactview.storage.ContactDatabaseHelper;

public class ContactDetailsActivity extends Activity {
ContactDatabaseHelper contactDatabaseHelper;
    long id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        contactDatabaseHelper=new ContactDatabaseHelper(this);
        Intent intent = getIntent();
         id = intent.getLongExtra("id",-1);
        Contact contact=contactDatabaseHelper.getContactById(id);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        nameTextView.setText(contact.getName());
        emailTextView.setText(contact.getEmail());
        phoneTextView.setText(contact.getPhoneNumber());
    }

    public void edit(View view){
        Intent intent = new Intent(this, EditContactActivity.class);

        intent.putExtra("id", id);
        //Toast.makeText(this, "ID:"+String.valueOf(contact.getId()), Toast.LENGTH_SHORT).show();

        startActivityForResult(intent, 2);
    }
}
