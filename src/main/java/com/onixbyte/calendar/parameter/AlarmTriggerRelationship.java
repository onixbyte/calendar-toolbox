package com.onixbyte.calendar.parameter;

public enum AlarmTriggerRelationship {

    START,
    END,
    ;

    public String formatted() {
        return "RELATED=" + name();
    }
}
