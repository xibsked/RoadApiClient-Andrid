package com.sked.roadapiclient;

import android.content.Context;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 16-09-2016.
 */
public class RoadApiClient {
    public static RoadApi.Builder with(Context context) {
        RoadApi.Builder builder = new RoadApi.Builder();
        return builder.context(context);
    }
}
