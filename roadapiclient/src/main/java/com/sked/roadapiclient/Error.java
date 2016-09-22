package com.sked.roadapiclient;

/**
 * All Rights Reserved, QuikSeek
 * Created by quikseek1 on 17-08-2016.
 */

public class Error {
    private int code;
    private String message;
    private String status;

    public Error() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + " : code : " + this.code + " : message : " + this.message + " : status : " + this.status;
    }
}
