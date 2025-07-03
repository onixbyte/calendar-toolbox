package com.onixbyte.calendar.parameter;

/**
 * Enumerates the possible relationships for the {@code RELATED} parameter in an iCalendar
 * alarm trigger.
 * <p>
 * This parameter defines whether an alarm is related to the start or end of the associated
 * calendar component.
 * <p>
 * Instances of this enum are formatted as specified in RFC 5545 for use in iCalendar properties.
 *
 * @author siujamo
 */
public enum AlarmTriggerRelationship implements Parameter {

    /**
     * Indicates that the alarm is related to the start of the calendar component.
     */
    START,

    /**
     * Indicates that the alarm is related to the end of the calendar component.
     */
    END,
    ;

    /**
     * Returns the formatted parameter string for inclusion in an iCalendar alarm trigger.
     *
     * @return a string formatted as {@code RELATED=START} or {@code RELATED=END}
     */
    @Override
    public String formatted() {
        return "RELATED=" + name();
    }
}
