package com.example.contactview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactview.adapters.ContactListAdapter;
import com.example.contactview.dto.Contact;
import com.example.contactview.perfers.SettingPerfernce;
import com.example.contactview.security.BiometricCallback;
import com.example.contactview.storage.ContactDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> originalContacts = new ArrayList<>();
    private ContactListAdapter adapter;
    ContactDatabaseHelper dbhelp;
    private BiometricPrompt biometricPrompt;
    private CancellationSignal cancellationSignal;

    private SettingPerfernce perfernce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelp = new ContactDatabaseHelper(this);
//        perfernce=SettingPerfernce.getInstance(this);
//        String message=perfernce.getAuthSetting()?"PRESBiometric Enabled":"Normal login enabled";
      //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        contacts = dbhelp.getAllContactsAlphabetical();
        originalContacts = dbhelp.getAllContactsAlphabetical();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactListAdapter(contacts);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new ContactListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openContactDetails(position);
            }
        });

//        Button addContactButton = findViewById(R.id.addContactButton);
//        addContactButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddContactActivity();
//            }
//        });

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterContacts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void openContactDetails(int position) {
        Contact contact = contacts.get(position);
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("id", contact.getId());
        startActivity(intent);
    }

    private void openAddContactActivity() {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 1);

    }

    public void openAddContactActivitys(View  view) {

        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 1);
        finish();
    }

    private void filterContacts(String query) {
        contacts.clear();
        if (query.isEmpty()) {
            contacts.addAll(originalContacts);
        } else {
            query = query.toLowerCase().trim();
            for (Contact contact : originalContacts) {
                if (contact.getName().toLowerCase().contains(query) || contact.getLastname().toLowerCase().contains(query)) {
                    contacts.add(contact);
                }

            }
        }
        adapter.notifyDataSetChanged();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Adding new contact
            String name = data.getStringExtra("name");
            String email = data.getStringExtra("email");
            String phoneNumber = data.getStringExtra("phoneNumber");

            Contact newContact = new Contact(name, email, phoneNumber);
            int id = (int) dbhelp.addContact(newContact);

            // Update the contact with the generated ID
            newContact.setId(id);

            contacts.add(newContact);
            originalContacts.add(newContact);
            adapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            // Editing existing contact
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                String name = data.getStringExtra("name");
                String email = data.getStringExtra("email");
                String phoneNumber = data.getStringExtra("phoneNumber");

                Contact updatedContact = new Contact(name, email, phoneNumber);
                updatedContact.setId(contacts.get(position).getId()); // Set the ID from the existing contact

                dbhelp.updateContact(updatedContact);

                contacts.set(position, updatedContact);
                originalContacts.set(position, updatedContact);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    public void  openSettingPAge(View view){
        Intent intent = new Intent(this,MYSetting.class);
        startActivityForResult(intent, 1);
    }
}
