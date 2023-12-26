/*
 * madushan joel 2023.
 */

package com.example.contactview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;

import com.example.contactview.perfers.SettingPerfernce;
import com.example.contactview.security.BiometricCallback;

public class FirstScreen extends AppCompatActivity {
    private BiometricPrompt biometricPrompt;
    private CancellationSignal cancellationSignal;

    private SettingPerfernce perfernce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        perfernce=SettingPerfernce.getInstance(this);
        boolean isbio=perfernce.getAuthSetting();
        if(isbio){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                biometricPrompt = new BiometricPrompt.Builder(this)
                        .setTitle("Biometric Authentication")
                        .setSubtitle("Use your fingerprint to access the app")
                        .setDescription("Place your finger on the fingerprint sensor")
                        .setNegativeButton("Cancel", getMainExecutor(), (dialogInterface, i) -> {
                            finish();
                        })
                        .build();
            }
            // Check for biometric authentication support
            if (isBiometricPromptEnabled()) {
                authenticateWithBiometrics();
            }
        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent, 1);
            finish();
        }
    }

    private boolean isBiometricPromptEnabled() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                && getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }

    private void authenticateWithBiometrics() {
        // Use BiometricPrompt to authenticate the user
        if (biometricPrompt != null) {
            cancellationSignal = new CancellationSignal();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                biometricPrompt.authenticate(cancellationSignal, getMainExecutor(), new BiometricCallback(this));
            }
        }
    }

}