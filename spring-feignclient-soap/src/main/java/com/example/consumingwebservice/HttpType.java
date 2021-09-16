package com.example.consumingwebservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HttpType {
    SOAP("SOAP"),
    REST("REST");

    private String value;

    HttpType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static HttpType fromValue(String value) {
        return HttpType.valueOf(value);
    }
}
