package com.onixbyte.calendar.parameter;

public record RecurrenceIdentifierRange() {

    public static RecurrenceIdentifierRange of() {
        return new RecurrenceIdentifierRange();
    }

    public String formatted() {
        return "RANGE=THISANDFUTURE";
    }
}
