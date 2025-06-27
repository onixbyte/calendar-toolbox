package com.onixbyte.calendar.parameter;

import java.util.List;

public enum ParticipationStatus implements Parameter {

    NEEDS_ACTION,
    ACCEPTED,
    DECLINED,
    TENTATIVE,
    DELEGATED,
    COMPLETED,
    IN_PROGRESS,
    ;

    private final static List<ParticipationStatus> EVENT_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED, TENTATIVE, DELEGATED
    );

    public boolean isEventParticipationStatus() {
        return EVENT_PARTICIPATION_STATUS.contains(this);
    }

    private final static List<ParticipationStatus> TODO_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED, TENTATIVE, DELEGATED, COMPLETED, IN_PROGRESS
    );

    public boolean isTodoParticipationStatus() {
        return TODO_PARTICIPATION_STATUS.contains(this);
    }

    private final static List<ParticipationStatus> JOURNAL_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED
    );

    public boolean isJournalParticipationStatus() {
        return JOURNAL_PARTICIPATION_STATUS.contains(this);
    }

    @Override
    public String formatted() {
        return "PARTSTAT=" + name().replaceAll("_", "-");
    }
}
