/*
 * madushan joel 2023.
 */

/*
 * madushan joel 2023.
 */

package com.example.contactview;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactview.dto.Contact;
import com.example.contactview.storage.ContactDatabaseHelper;
import com.example.contactview.utils.AlertBox;
import com.example.contactview.utils.ColorFun;

public class ContactDetailsActivity extends Activity {
ContactDatabaseHelper contactDatabaseHelper;
    long id=-1;
    Contact contact=null;
    TextView nameTextView,emailTextView,phoneTextView,phone2TextViews;
    LinearLayout cardlayout;
    ColorFun colorFun=ColorFun.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        contactDatabaseHelper=new ContactDatabaseHelper(this);
        Intent intent = getIntent();
         id = intent.getLongExtra("id",-1);
         contact=contactDatabaseHelper.getContactById(id);
       cardlayout=findViewById(R.id.lincard);
        colorFun.changeRandomColor(cardlayout,this);
         nameTextView = findViewById(R.id.nameTextView);
         emailTextView = findViewById(R.id.emailTextView);
         phoneTextView = findViewById(R.id.phoneTextView);
         phone2TextViews=findViewById(R.id.phone2TextView);
         //
        String lastname=contact.getLastname()!=null?contact.getLastname():"";
        nameTextView.setText(contact.getName()+" " +lastname);
        emailTextView.setText(contact.getEmail());
        phoneTextView.setText(contact.getPhoneNumber());
        phone2TextViews.setText(contact.getPhoneNumber2());

    }

    public void edit(View view){
        Intent intent = new Intent(this, EditContactActivity.class);

        intent.putExtra("id", id);
        //Toast.makeText(this, "ID:"+String.valueOf(contact.getId()), Toast.LENGTH_SHORT).show();

        startActivityForResult(intent, 2);
    }
    public  void delete(View view){
        Intent intent = new Intent(this, MainActivity.class);

        AlertBox.showAlertBox(this, "Do you want to delete Contact?", "Delete", false, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contactDatabaseHelper.deleteContact(id);
                startActivityForResult(intent, 0);
                finish();
            }
        });

    }
    public void call(View view){
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);

        // Set the phone number to dial
        dialIntent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));

        // Start the dialer activity
        startActivity(dialIntent);
    }
    public void message(View view){
        Intent dialIntent = new Intent(Intent.ACTION_VIEW);

        // Set the phone number to dial
        dialIntent.setData(Uri.parse("smsto:" + contact.getPhoneNumber()));

        // Start the dialer activity
        startActivity(dialIntent);
    }
    public void compsHidden(Contact contact){
        if(contact.getPhoneNumber2()==null){
            phone2TextViews.setVisibility(View.INVISIBLE);
        }
        if(contact.getEmail() == null){
            emailTextView.setVisibility(View.INVISIBLE);
        }
    }
}
