package com.onixbyte.calendar.parameter;

public record RsvpExpectation(boolean rsvpExpectation) {

    public static RsvpExpectation of(boolean rsvpExpectation) {
        return new RsvpExpectation(rsvpExpectation);
    }

    public String formatted() {
        return "RSVP=" + String.valueOf(rsvpExpectation).toUpperCase();
    }
}
