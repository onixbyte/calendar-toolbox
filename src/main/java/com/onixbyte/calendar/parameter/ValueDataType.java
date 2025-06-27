package com.onixbyte.calendar.parameter;

public enum ValueDataType implements CalendarParameter {

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

    @Override
    public String formatted() {
        return "VALUE=" + name().replaceAll("_", "-");
    }
}
