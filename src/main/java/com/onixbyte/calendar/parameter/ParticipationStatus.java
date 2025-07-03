package com.onixbyte.calendar.parameter;

import java.util.List;

/**
 * Enumeration representing the participation statuses for calendar components as defined in the
 * iCalendar specification (RFC 5545).
 * <p>
 * This enumeration includes status values applicable to various calendar component types such as
 * events, to-dos, and journals. Each status represents the response or state of a participant
 * regarding a calendar item.
 *
 * @author siujamo
 */
public enum ParticipationStatus implements Parameter {

    /**
     * Indicates that participation action is still required.
     */
    NEEDS_ACTION,

    /**
     * Indicates that the participation has been accepted.
     */
    ACCEPTED,

    /**
     * Indicates that the participation has been declined.
     */
    DECLINED,

    /**
     * Indicates that the participation is tentative.
     */
    TENTATIVE,

    /**
     * Indicates that the participation has been delegated.
     */
    DELEGATED,

    /**
     * Indicates that the to-do has been completed.
     * Applicable only to to-do components.
     */
    COMPLETED,

    /**
     * Indicates that work is in progress on the to-do.
     * Applicable only to to-do components.
     */
    IN_PROGRESS;

    /**
     * List of participation statuses applicable to event components.
     */
    private final static List<ParticipationStatus> EVENT_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED, TENTATIVE, DELEGATED
    );

    /**
     * Determines if this status is valid for an event participation.
     *
     * @return {@code true} if this status is applicable to events, otherwise {@code false}
     */
    public boolean isEventParticipationStatus() {
        return EVENT_PARTICIPATION_STATUS.contains(this);
    }

    /**
     * List of participation statuses applicable to to-do components.
     */
    private final static List<ParticipationStatus> TODO_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED, TENTATIVE, DELEGATED, COMPLETED, IN_PROGRESS
    );

    /**
     * Determines if this status is valid for a to-do participation.
     *
     * @return {@code true} if this status is applicable to to-dos, otherwise {@code false}
     */
    public boolean isTodoParticipationStatus() {
        return TODO_PARTICIPATION_STATUS.contains(this);
    }

    /**
     * List of participation statuses applicable to journal components.
     */
    private final static List<ParticipationStatus> JOURNAL_PARTICIPATION_STATUS = List.of(
            NEEDS_ACTION, ACCEPTED, DECLINED
    );

    /**
     * Determines if this status is valid for a journal participation.
     *
     * @return {@code true} if this status is applicable to journals, otherwise {@code false}
     */
    public boolean isJournalParticipationStatus() {
        return JOURNAL_PARTICIPATION_STATUS.contains(this);
    }

    /**
     * Returns the formatted string representation of this participation status
     * as defined in the iCalendar specification.
     * <p>
     * The returned string is suitable for use in iCalendar properties such as
     * ATTENDEE, where the status is represented in uppercase with hyphens
     * replacing underscores (e.g., NEEDS-ACTION).
     *
     * @return a formatted string representing this participation status
     */
    @Override
    public String formatted() {
        return "PARTSTAT=" + name().replaceAll("_", "-");
    }
}
