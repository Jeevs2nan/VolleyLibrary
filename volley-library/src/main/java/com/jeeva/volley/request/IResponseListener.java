package com.jeeva.volley.request;

import org.json.JSONObject;

/**
 * This controller handles the network operations and returns the response.
 *
 * @author Jeevanandhan
 */

public interface IResponseListener <E extends Enum<E>>  {

    void successResponse(String successResponse, E e);

    void successResponse(JSONObject jsonObject, E e);

    void errorResponse(String errorResponse, E e);

    void removeProgress(Boolean hideFlag);

}
