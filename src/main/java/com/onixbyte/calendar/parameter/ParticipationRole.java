package com.onixbyte.calendar.parameter;

public enum ParticipationRole {

    CHAIR,
    REQ_PARTICIPANT,
    OPT_PARTICIPANT,
    NON_PARTICIPANT,
    ;

    public String formatted() {
        return "ROLE=" + name();
    }
}
