package com.onixbyte.calendar.parameter;

public record RecurrenceIdentifierRange() implements CalendarParameter {

    public static RecurrenceIdentifierRange of() {
        return new RecurrenceIdentifierRange();
    }

    @Override
    public String formatted() {
        return "RANGE=THISANDFUTURE";
    }
}
