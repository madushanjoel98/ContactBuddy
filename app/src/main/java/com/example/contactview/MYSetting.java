/*
 * madushan joel 2023.
 */

package com.example.contactview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactview.dto.Contact;
import com.example.contactview.perfers.SettingPerfernce;
import com.example.contactview.storage.ContactDatabaseHelper;
import com.example.contactview.utils.AlertBox;

public class MYSetting extends AppCompatActivity {
    Switch bioswitch;
    SettingPerfernce perfernce;
    ContactDatabaseHelper databaseHelper;
    TextView textView;
Layout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysetting);

        perfernce = SettingPerfernce.getInstance(this);
        bioswitch = findViewById(R.id.bioswitch);
        textView=findViewById(R.id.biomessage);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            bioswitch.setVisibility(View.INVISIBLE);
            textView.setText("SDK Version not Supported for biometric login");
            textView.setVisibility(View.VISIBLE);
        }
        bioswitch.setChecked(perfernce.getAuthSetting());

        databaseHelper = new ContactDatabaseHelper(this);

        bioswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                perfernce.setAuthSetting(bioswitch.isChecked());
                String message = bioswitch.isChecked() ? "Biometric Enabled" : "Normal login enabled";
                Toast.makeText(MYSetting.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

public void deleteAll(View view){
AlertBox.showAlertBox(this, "Are you sure ? ", "Delete All Contact", false, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        databaseHelper.deleteAllContacts();
       finishAffinity();
    }
});

}
}