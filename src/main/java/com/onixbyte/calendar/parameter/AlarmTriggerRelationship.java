package com.onixbyte.calendar.parameter;

public enum AlarmTriggerRelationship implements Parameter {

    START,
    END,
    ;

    @Override
    public String formatted() {
        return "RELATED=" + name();
    }
}
