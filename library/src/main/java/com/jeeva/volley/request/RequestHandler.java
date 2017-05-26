package com.jeeva.volley.request;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.jeeva.volley.R;
import com.jeeva.volley.utility.AlertContext;
import com.jeeva.volley.utility.Utility;
import com.jeeva.volley.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * This class holds the server request methods,
 * .ie., GET, POST method server request.
 *
 * @author Jeevanadhan
 */
public class RequestHandler implements IRequestHandler {


    //Single ton object...
    private static RequestHandler requestHandler = null;
    private String headerMissing = "Header Missing!";
    private String contentTypeMissing = "Content Type Mising!";
    private String contentType = null;
    private DefaultRetryPolicy defaultRetryPolicy = null;
    private Map<String, String> header = null;
    private String tag = null;

    //Single ton method...
    public static RequestHandler getInstance() {
        if (requestHandler != null) {
            return requestHandler;
        } else {
            requestHandler = new RequestHandler();
            return requestHandler;
        }
    }


    @Override
    public void setRetryPolicy(DefaultRetryPolicy defaultRetryPolicy) {
        this.defaultRetryPolicy = defaultRetryPolicy;
    }

    @Override
    public Map<String, String> getRequestHeaders() {
        return header;
    }

    @Override
    public void setRequestHeaders(Map<String, String> header) {
        this.header = header;
    }

    @Override
    public DefaultRetryPolicy setRetryPolicy() {
        return defaultRetryPolicy;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void jsonObjectRequest(final Context context, int method, String url, JSONObject jsonObject, final IResponseListener listener, final Enum e, final Map<String, String> param) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.DELETE, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {

                /** Success Response
                 *
                 */

                listener.successResponse(jsonObject, e);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /** Error Response
                 *
                 */

                listener.removeProgress(true);

                NetworkResponse networkResponse = error.networkResponse;
                String local = error.getLocalizedMessage();
                if (!TextUtils.isEmpty(local)) {
                    listener.errorResponse(local, e);
                } else if (networkResponse != null) {
                    if (networkResponse.statusCode != 200) {
                        byte[] arr = networkResponse.data;
                        AlertContext.alertDialog(context, context.getResources().getString(R.string.responseError), exceptionMessage(arr));
                    }
                }

            }

        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return (param != null && param.size() > 0) ? param : super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return (getRequestHeaders() != null && getRequestHeaders().size() > 0) ? getRequestHeaders() : super.getHeaders();
            }

            /** Setting the content type
             *
             */

            @Override
            public String getBodyContentType() {
                return (Utility.isNotNull(getContentType()) ? getContentType() : super.getBodyContentType());
            }

        };


        if (setRetryPolicy() != null) {
            jsonObjReq.setRetryPolicy(setRetryPolicy());
        }

        if (Utility.isNotNull(getTag())) {
            jsonObjReq.setTag(getTag());
        }

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjReq);
    }


    @Override
    public void stringRequest(final Context context, int method, String url, final IResponseListener listener, final Enum e, final Map<String, String> param) {

        if (Utility.isNotNull(getContentType())) {
            throw new RuntimeException(contentTypeMissing);
        }


        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.successResponse(response, e);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /** Error Response
                 *
                 */

                listener.removeProgress(true);

                NetworkResponse networkResponse = error.networkResponse;
                String local = error.getLocalizedMessage();
                if (!TextUtils.isEmpty(local)) {
                    listener.errorResponse(local, e);
                } else if (networkResponse != null) {

                    if (networkResponse.statusCode != 200) {
                        byte[] arr = networkResponse.data;
                        AlertContext.alertDialog(context, context.getResources().getString(R.string.responseError), exceptionMessage(arr));
                    }
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return (param != null && param.size() > 0) ? param : super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return (getRequestHeaders() != null && getRequestHeaders().size() > 0) ? getRequestHeaders() : super.getHeaders();
            }

            /**
             * Setting the content type
             */

            @Override
            public String getBodyContentType() {
                return (Utility.isNotNull(getContentType()) ? getContentType() : super.getBodyContentType());
            }

        };


        if (setRetryPolicy() != null) {
            sr.setRetryPolicy(setRetryPolicy());
        }

        if (Utility.isNotNull(getTag())) {
            sr.setTag(getTag());
        }

        VolleySingleton.getInstance(context).addToRequestQueue(sr);
    }


    @Override
    public void jsonArrayRequest(final Context context, int method, String url, final Enum e, final IResponseListener listener, final Map<String, String> param) {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                VolleyLog.d("JSonArray", "" + jsonArray.toString());

                listener.successResponse(jsonArray.toString(), e);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /** Error Response
                 *
                 */

                listener.removeProgress(true);

                NetworkResponse networkResponse = error.networkResponse;
                String local = error.getLocalizedMessage();
                if (!TextUtils.isEmpty(local)) {
                    listener.errorResponse(local, e);
                } else if (networkResponse != null) {

                    if (networkResponse.statusCode != 200) {
                        byte[] arr = networkResponse.data;
                        AlertContext.alertDialog(context, context.getResources().getString(R.string.responseError), exceptionMessage(arr));
                    }
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return (param != null && param.size() > 0) ? param : super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return (getRequestHeaders() != null && getRequestHeaders().size() > 0) ? getRequestHeaders() : super.getHeaders();
            }

            /**
             * Setting the content type
             */

            @Override
            public String getBodyContentType() {
                return (Utility.isNotNull(getContentType()) ? getContentType() : super.getBodyContentType());
            }

        };


        if (setRetryPolicy() != null) {
            request.setRetryPolicy(setRetryPolicy());
        }

        if (Utility.isNotNull(getTag())) {
            request.setTag(getTag());
        }

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }


    /**
     * Converts the byte[] message to json object and send the exceptional message
     *
     * @param message error message in byte [] format.
     * @return error message in string format.
     */


    private String exceptionMessage(byte[] message) {
        String strMessage = new String(message);
        try {
            JSONObject jsonObject = new JSONObject(strMessage);
            if (jsonObject.length() > 0) {
                return jsonObject.optString("exceptionMessage");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
