package com.onixbyte.calendar.parameter;

import java.util.List;

public enum ParticipationStatus {

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

    public boolean isEventParticipationStatus(ParticipationStatus participationStatus) {
        return EVENT_PARTICIPATION_STATUS.contains(participationStatus);
    }

    private final static List<ParticipationStatus> TODO_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED, TENTATIVE, DELEGATED, COMPLETED, IN_PROGRESS
    );

    public boolean isTodoParticipationStatus(ParticipationStatus participationStatus) {
        return TODO_PARTICIPATION_STATUS.contains(participationStatus);
    }

    private final static List<ParticipationStatus> JOURNAL_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED
    );

    public boolean isJournalParticipationStatus(ParticipationStatus participationStatus) {
        return JOURNAL_PARTICIPATION_STATUS.contains(participationStatus);
    }

    public String formatted() {
        return "PARTSTAT=" + name().replaceAll("_", "-");
    }
}
