package com.onixbyte.calendar.parameter;

public enum ParticipationRole implements CalendarParameter {

    CHAIR,
    REQ_PARTICIPANT,
    OPT_PARTICIPANT,
    NON_PARTICIPANT,
    ;

    @Override
    public String formatted() {
        return "ROLE=" + name();
    }
}
