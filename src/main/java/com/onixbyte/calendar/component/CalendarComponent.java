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

package com.onixbyte.calendar.component;

/**
 * Base interface for all iCalendar components.
 * <p>
 * This interface defines the common contract for calendar components that can be contained within
 * an iCalendar object. Components represent the core entities in a calendar system, each with their
 * own set of properties and behaviors.
 * <p>
 * The main types of calendar components include:
 * <ul>
 *   <li>VEVENT - represents a scheduled event</li>
 *   <li>VTODO - represents a to-do item or task</li>
 *   <li>VJOURNAL - represents a journal entry</li>
 *   <li>VFREEBUSY - represents free/busy time information</li>
 *   <li>VTIMEZONE - represents time zone information</li>
 *   <li>VALARM - represents alarm/reminder information</li>
 * </ul>
 * <p>
 * Each component is delimited by {@code BEGIN:COMPONENT} and {@code END:COMPONENT} markers in the
 * iCalendar format, with properties specified between these markers.
 * <p>
 * All implementing classes must provide a {@link #formatted()} method that returns the complete
 * iCalendar string representation of the component.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CalendarComponent {

    /**
     * Returns the formatted iCalendar representation of this component.
     * <p>
     * The format includes the {@code BEGIN:COMPONENT} and {@code END:COMPONENT} delimiters with all
     * component properties formatted between them.
     *
     * @return the formatted iCalendar component string
     */
    String formatted();
}
