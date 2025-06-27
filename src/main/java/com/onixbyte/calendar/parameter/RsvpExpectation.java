package com.onixbyte.calendar.parameter;

public class RsvpExpectation implements Parameter {

    private final boolean rsvpExpectation;

    private RsvpExpectation(boolean rsvpExpectation) {
        this.rsvpExpectation = rsvpExpectation;
    }

    public static RsvpExpectation of(boolean rsvpExpectation) {
        return new RsvpExpectation(rsvpExpectation);
    }

    @Override
    public String formatted() {
        return "RSVP=" + String.valueOf(rsvpExpectation).toUpperCase();
    }
}
