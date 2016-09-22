package com.sked.roadapiclient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
public class RoadApi implements Response.ErrorListener, Response.Listener<ResponseWrapper> {
    private Context context;
    private String key;
    private boolean interpolate;
    private List<Location> spots;
    private RoadSnapCallback roadSnapCallback;


    private RoadApi(Builder builder) {
        context = builder.context;
        key = builder.key;
        interpolate = builder.interpolate;
        spots = builder.spots;
        roadSnapCallback = builder.roadSnapCallback;
    }

    public static final class Builder {
        private Context context;
        private String key;
        private boolean interpolate;
        private RoadSnapCallback roadSnapCallback;
        private List<Location> spots;

        public Builder() {
        }

        public Builder context(Context val) {
            context = val;
            return this;
        }

        public Builder key(String val) {
            key = val;
            return this;
        }

        public Builder interpolate(boolean val) {
            interpolate = val;
            return this;
        }


        public Builder spots(List<Location> spots) {
            this.spots = spots;
            return this;
        }

        public Builder roadSnapCallback(RoadSnapCallback val) {
            roadSnapCallback = val;
            return this;
        }

        public RoadApi execute() {
            RoadApi roadApi = new RoadApi(this);
            roadApi.execute();
            return roadApi;
        }
    }

    private void execute() {
        RoadApiRequest apiRequest = new RoadApiRequest(Request.Method.GET, getFormattedPath(), null, this, this);
        Volley.newRequestQueue(context).add(apiRequest);
    }


    private String getFormattedPath() {
        if (spots == null) return "";
        String paths = "";
        for (Location stop : spots) {
            paths = paths + stop.getLatitude() + "," + stop.getLongitude() + "|";
        }
        paths = paths.substring(0, paths.length() - 1);
        return RoadUrls.ROAD_API_URL + "?path=" + paths + "&interpolate=" + interpolate + "&key=" + key;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Error wrappedError = new Error();
        wrappedError.setCode(500);
        wrappedError.setMessage(error.getMessage());
        wrappedError.setStatus(error.getLocalizedMessage());
        roadSnapCallback.onSnappedPointError(wrappedError);
    }

    @Override
    public void onResponse(ResponseWrapper response) {
        if (response.getSnappedPoints() != null) {
            roadSnapCallback.onSnappedPointSuccess(response.getSnappedPoints());
        } else {
            roadSnapCallback.onSnappedPointError(response.getError());
        }
    }

}
