/*
 * madushan joel 2023.
 */

package com.example.contactview.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;

import androidx.appcompat.app.AlertDialog;

public class AlertBox {
    public static void showAlertBox(Context context, String message, String title, boolean cancelable, DialogInterface.OnClickListener positiveClickListener) {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set the message for the Alert
        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle(title);

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(cancelable);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", positiveClickListener);

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

}
