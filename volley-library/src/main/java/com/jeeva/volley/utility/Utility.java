package com.jeeva.volley.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * This class holds the code snippet.
 *
 * @author Jeevanandhan
 */
public class Utility {


    /**
     * Check whether the string contains value (OR) not.
     *
     * @param isNotNull string value which has to be checked.
     * @return true if the given string is not null and this method will validate if the give string
     * contains "null" as a String value too.
     */

    public static boolean isNotNull(String isNotNull) {

        if (isNotNull != null) {
            if (!isNotNull.equalsIgnoreCase("") && isNotNull.length() > 0) {
                if (!isNotNull.equalsIgnoreCase("null")) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Checks whether the device currently has a network connection..
     *
     * @param context Context of current state of the application/object
     * @return true if the device has a network connection; false otherwise.
     */

    public static boolean isDeviceOnLine(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


}
