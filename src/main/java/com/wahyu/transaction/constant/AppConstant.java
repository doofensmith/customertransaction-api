package com.wahyu.transaction.constant;

public enum AppConstant {

    SUCCESS("Success"),

    BAD_REQUEST("Bad request"),

    NOT_FOUND("Not found"),

    ERROR("Error");

    public final String message;
    private AppConstant(String message) {
        this.message = message;
    }

}
