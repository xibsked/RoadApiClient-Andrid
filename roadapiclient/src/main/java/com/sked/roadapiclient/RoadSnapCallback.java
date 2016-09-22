package com.sked.roadapiclient;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
public interface RoadSnapCallback {
    void onSnappedPointSuccess(SnappedPoints snappedPoints);

    void onSnappedPointError(Error error);
}
