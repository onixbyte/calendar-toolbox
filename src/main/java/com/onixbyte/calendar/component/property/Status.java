/*
 * Copyright (c) 2024-2025 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.onixbyte.calendar.component.property;

import java.util.List;

/**
 * Enumeration representing the iCalendar {@code STATUS} property values.
 * <p>
 * The {@code STATUS} property defines the overall status or confirmation of a calendar component.
 * Different status values are applicable to different types of calendar components (events,
 * to-dos, and journals) as defined in RFC 5545.
 * <p>
 * This enumeration provides methods to check which status values are valid for each type of
 * calendar component.
 * 
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Status implements ComponentProperty {

    /**
     * Tentative status - indicates that the event is tentative. Valid for events.
     */
    TENTATIVE,
    
    /**
     * Confirmed status - indicates that the event is confirmed. Valid for events.
     */
    CONFIRMED,
    
    /**
     * Cancelled status - indicates that the component has been cancelled. Valid for events, to-dos,
     * and journals.
     */
    CANCELLED,
    
    /**
     * Needs action status - indicates that action is needed. Valid for to-dos.
     */
    NEEDS_ACTION,
    
    /**
     * Completed status - indicates that the to-do has been completed. Valid for to-dos.
     */
    COMPLETED,
    
    /**
     * In progress status - indicates that the to-do is in progress. Valid for to-dos.
     */
    IN_PROGRESS,
    
    /**
     * Draft status - indicates that the journal is a draft. Valid for journals.
     */
    DRAFT,
    
    /**
     * Final status - indicates that the journal is final. Valid for journals.
     */
    FINAL
    ;

    /**
     * List of status values that are valid for event components.
     */
    private final static List<Status> EVENT_STATUSES = List.of(
            TENTATIVE, CONFIRMED, CANCELLED
    );

    /**
     * List of status values that are valid for to-do components.
     */
    private final static List<Status> TODO_STATUSES = List.of(
            NEEDS_ACTION, COMPLETED, IN_PROGRESS, CANCELLED
    );

    /**
     * List of status values that are valid for journal components.
     */
    private final static List<Status> JOURNAL_STATUSES = List.of(
            DRAFT, FINAL, CANCELLED
    );

    /**
     * Checks if this status is valid for event components.
     * 
     * @return true if this status can be used with events, false otherwise
     */
    public boolean isEventStatus() {
        return EVENT_STATUSES.contains(this);
    }

    /**
     * Checks if this status is valid for to-do components.
     * 
     * @return true if this status can be used with to-dos, false otherwise
     */
    public boolean isTodoStatus() {
        return TODO_STATUSES.contains(this);
    }

    /**
     * Checks if this status is valid for journal components.
     * 
     * @return true if this status can be used with journals, false otherwise
     */
    public boolean isJournalStatus() {
        return JOURNAL_STATUSES.contains(this);
    }

    /**
     * Returns the formatted string representation of this status property.
     * <p>
     * The format follows the iCalendar specification: "{@code STATUS:}" followed by the status name
     * with underscores replaced by hyphens.
     * 
     * @return the formatted status string (e.g., "{@code STATUS:IN-PROGRESS}")
     */
    @Override
    public String formatted() {
        return "STATUS:" + name().replaceAll("_", "-");
    }
}
