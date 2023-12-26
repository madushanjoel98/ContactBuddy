/*
 * madushan joel 2023.
 */

package com.example.contactview.security;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.contactview.MYSetting;
import com.example.contactview.MainActivity;

@RequiresApi(api = Build.VERSION_CODES.P)
public class BiometricCallback extends BiometricPrompt.AuthenticationCallback {
    Context context;

    public BiometricCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        // Authentication successful, allow access to the app
        Toast.makeText(context, "Biometric authentication successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        ((Activity) context).  startActivityForResult(intent, 1);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        // Authentication failed, handle accordingly
        Toast.makeText(context, "Biometric authentication failed", Toast.LENGTH_SHORT).show();
        ((Activity) context).finish();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        // Authentication error, handle accordingly
        Toast.makeText(context, "Biometric authentication error: " + errString, Toast.LENGTH_SHORT).show();
        ((Activity) context).finish();
        if (errorCode == BiometricPrompt.BIOMETRIC_ERROR_CANCELED) {
            Toast.makeText(context, "Biometric authentication canceled by the user", Toast.LENGTH_SHORT).show();
            ((Activity) context).finish(); // Close the activity
        }
    }

}




