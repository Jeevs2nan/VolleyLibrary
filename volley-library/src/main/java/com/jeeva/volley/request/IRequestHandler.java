package com.jeeva.volley.request;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by jeeva on 5/24/2017.
 */

public interface IRequestHandler {

    /**
     * Set retry policy.
     *
     * @param defaultRetryPolicy default retry policy class object.
     */

    void setRetryPolicy(DefaultRetryPolicy defaultRetryPolicy);

    /**
     * Returns the header.
     *
     * @return header.
     */

    Map<String, String> getRequestHeaders();

    /**
     * set the header for the api.
     *
     * @param header which is to be used for api call.
     */

    void setRequestHeaders(Map<String, String> header);

    /**
     * Returns the default retry policy.
     *
     * @return default retry policy class object.
     */

    DefaultRetryPolicy setRetryPolicy();

    /**
     * Returns the content type.
     *
     * @return content type for api call in string type.
     */

    String getContentType();

    /**
     * set the content type.
     *
     * @param contentType string
     */

    void setContentType(String contentType);

    /**
     * Returns the tag for api call.
     *
     * @return tag
     */

    String getTag();

    /**
     * set the tag for api call.
     *
     * @param tag string
     */

    void setTag(String tag);

    /**
     * This method handles JsonObjectRequest for all the methods like GET,POST,etc..
     *
     * @param context    Context of current state of the application/object
     * @param method     by which the api is to be called {Request.Method.GET}
     * @param url        full url which is to be called
     * @param jsonObject request to be send for the api
     * @param listener   Interface in which the response is handled
     * @param e          enum which helps the segregate the responses
     * @param param      request to be send for the api
     *                   <p>
     *                   {NOTE: Either the request can be send as Json object or Map}
     */

    void jsonObjectRequest(Context context, int method, String url, JSONObject jsonObject, IResponseListener listener, Enum e, Map<String, String> param);


    /**
     * This method handles StringRequest for all the methods like GET,POST,etc..
     *
     * @param context  Context of current state of the application/object
     * @param method   by which the api is to be called {Request.Method.GET}
     * @param url      full url which is to be called
     * @param listener Interface in which the response is handled
     * @param e        which helps the segregate the responses
     * @param param    request to be send for the api.
     */

    void stringRequest(Context context, int method, String url, IResponseListener listener, Enum e, Map<String, String> param);

    /**
     * This method handles JsonArrayRequest for all the methods like GET,POST,etc..
     *
     * @param context  Context of current state of the application/object
     * @param method   by which the api is to be called {Request.Method.GET}
     * @param url      full url which is to be called
     * @param listener Interface in which the response is handled
     * @param e        which helps the segregate the responses
     * @param param    request to be send for the api.
     */

    void jsonArrayRequest(Context context, int method, String url, Enum e, IResponseListener listener, Map<String, String> param);


}
