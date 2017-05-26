package com.jeeva.volley.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * This call holds the dialogs and toast message.
 *
 * @author Jeevanandhan
 */
public class AlertContext {


    /**
     * Dialog which is used to show the common message with "Ok" button
     * .i.e., with only positive button.
     *
     * @param context Context of current state of the application/object.
     * @param title   String value which will be displayed as a title for the alert dialog.
     * @param message String value which will be displayed as a message for the alert dialog.
     */

    public static void alertDialog(final Context context, String title, String message) {
        String buttonText = "Ok";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (Utility.isNotNull(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}