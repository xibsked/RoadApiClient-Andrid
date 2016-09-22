package com.sked.roadapiclient;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
public class RoadApiRequest extends JsonRequest<ResponseWrapper> {
    public RoadApiRequest(int method, String url, String requestBody, Response.Listener<ResponseWrapper> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    protected Response<ResponseWrapper> parseNetworkResponse(NetworkResponse response) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            ObjectMapper objectMapper = new ObjectMapper();
            if (jsonString.contains("snappedPoints")) {
                SnappedPoints snappedPoints = objectMapper.readValue(jsonString, SnappedPoints.class);
                responseWrapper.setSnappedPoints(snappedPoints);
            } else {
                Error error = objectMapper.readValue(jsonString, Error.class);
                responseWrapper.setError(error);
            }
            return Response.success(responseWrapper,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        } catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }
}
