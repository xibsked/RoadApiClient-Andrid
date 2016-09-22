package com.sked.roadapiclient;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
public class ResponseWrapper {
    private SnappedPoints snappedPoints;
    private Error error;

    public ResponseWrapper(SnappedPoints snappedPoints, Error error) {
        this.snappedPoints = snappedPoints;
        this.error = error;
    }

    public ResponseWrapper() {

    }

    public SnappedPoints getSnappedPoints() {
        return snappedPoints;
    }

    public void setSnappedPoints(SnappedPoints snappedPoints) {
        this.snappedPoints = snappedPoints;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
