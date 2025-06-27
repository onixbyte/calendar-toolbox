package com.onixbyte.calendar.parameter;

import java.util.Objects;

public final class RecurrenceIdentifierRange implements Parameter {

    private static RecurrenceIdentifierRange _instance;

    public static RecurrenceIdentifierRange of() {
        if (Objects.nonNull(_instance)) {
            synchronized (RecurrenceIdentifierRange.class) {
                if (Objects.nonNull(_instance)) {
                    _instance = new RecurrenceIdentifierRange();
                }
            }
        }
        return _instance;
    }

    @Override
    public String formatted() {
        return "RANGE=THISANDFUTURE";
    }
}
