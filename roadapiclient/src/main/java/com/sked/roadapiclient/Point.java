package com.sked.roadapiclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {
    private Location location;
    private int originalIndex = -1;
    private String placeId;

    public Point() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    public void setOriginalIndex(int originalIndex) {
        this.originalIndex = originalIndex;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
