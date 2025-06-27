package com.onixbyte.calendar.parameter;

public record RsvpExpectation(boolean rsvpExpectation) implements Parameter {

    public static RsvpExpectation of(boolean rsvpExpectation) {
        return new RsvpExpectation(rsvpExpectation);
    }

    @Override
    public String formatted() {
        return "RSVP=" + String.valueOf(rsvpExpectation).toUpperCase();
    }
}
