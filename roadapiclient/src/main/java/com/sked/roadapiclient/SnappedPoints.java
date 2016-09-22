package com.sked.roadapiclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
public class SnappedPoints {
    @JsonProperty("snappedPoints")
    private List<Point> points;
    @JsonProperty("warningMessage")
    private String message;

    public SnappedPoints() {
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
