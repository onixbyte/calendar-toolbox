package com.onixbyte.calendar.parameter;

public enum AlarmTriggerRelationship implements CalendarParameter {

    START,
    END,
    ;

    @Override
    public String formatted() {
        return "RELATED=" + name();
    }
}
