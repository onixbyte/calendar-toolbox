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

package com.onixbyte.calendar.property;

/**
 * Enumeration representing the iCalendar METHOD property values.
 * <p>
 * The METHOD property defines the calendar's intended use and the processing
 * requirements for calendar objects. These values are used to indicate whether
 * a calendar is being published, used for scheduling requests, or for other
 * calendar-related operations.
 * <p>
 * Each method value has specific semantics as defined in RFC 5545:
 * <ul>
 * <li>{@code PUBLISH} - Used for publishing calendar information</li>
 * <li>{@code REQUEST} - Used for scheduling requests</li>
 * <li>{@code REPLY} - Used for replying to scheduling requests</li>
 * <li>{@code ADD} - Used for adding calendar components</li>
 * <li>{@code CANCEL} - Used for cancelling calendar components</li>
 * <li>{@code REFRESH} - Used for requesting updated calendar information</li>
 * <li>{@code COUNTER} - Used for counter-proposing meeting times</li>
 * <li>{@code DECLINE_COUNTER} - Used for declining counter-proposals</li>
 * </ul>
 * 
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Method implements CalendarProperty {

    /**
     * Method value for publishing calendar information.
     * Used when calendar information is being made available to others.
     */
    PUBLISH,
    
    /**
     * Method value for scheduling requests.
     * Used when requesting meetings or appointments with others.
     */
    REQUEST,
    
    /**
     * Method value for replying to scheduling requests.
     * Used when responding to meeting or appointment requests.
     */
    REPLY,
    
    /**
     * Method value for adding calendar components.
     * Used when adding new components to an existing calendar.
     */
    ADD,
    
    /**
     * Method value for cancelling calendar components.
     * Used when cancelling meetings or appointments.
     */
    CANCEL,
    
    /**
     * Method value for requesting updated calendar information.
     * Used when requesting fresh calendar data from the organiser.
     */
    REFRESH,
    
    /**
     * Method value for counter-proposing meeting times.
     * Used when suggesting alternative meeting times.
     */
    COUNTER,
    
    /**
     * Method value for declining counter-proposals.
     * Used when rejecting alternative meeting time suggestions.
     */
    DECLINE_COUNTER,
    ;

    /**
     * Returns the formatted string representation of this method property.
     * <p>
     * The format follows the iCalendar specification: "METHOD:" followed by
     * the method name with underscores replaced by hyphens.
     * 
     * @return the formatted method string (e.g., "METHOD:DECLINE-COUNTER")
     */
    @Override
    public String formatted() {
        return "METHOD:" + name().replaceAll("_", "-");
    }
}
