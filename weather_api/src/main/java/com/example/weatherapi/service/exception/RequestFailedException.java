package com.example.weatherapi.service.exception;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class RequestFailedException extends RuntimeException {
    private final Response response;

    public RequestFailedException(Response response) {
        this.response = response;
    }

    @Override
    public String getMessage() {
        if (response != null) {
            return toErrorString(response);
        } else {
            return "no message";
        }
    }

    private String toErrorString(Response response) {
        try {
            StringBuilder sb = new StringBuilder();
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                sb.append("errorBody: ");
                sb.append(responseBody.string());
            }
            sb.append("\n");
            sb.append(response.toString());
            return sb.toString();
        } catch (Exception e) {
            return response.toString();
        }
    }
}
