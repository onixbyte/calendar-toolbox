package com.onixbyte.calendar.parameter;

public enum ValueDataType {

    BINARY,
    BOOLEAN,
    CAL_ADDRESS,
    DATE,
    DATE_TIME,
    DURATION,
    FLOAT,
    INTEGER,
    PERIOD,
    RECUR,
    TEXT,
    TIME,
    UNKNOWN,
    URI,
    UTC_OFFSET,
    XML_REFERENCE,
    UID,
    ;

    ValueDataType() {
    }

    public String formatted() {
        return "VALUE=" + name().replaceAll("_", "-");
    }
}
