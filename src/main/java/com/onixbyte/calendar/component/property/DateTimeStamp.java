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

import com.onixbyte.calendar.util.Formatters;

import java.time.ZonedDateTime;

/**
 * Represents the iCalendar {@code DTSTAMP} property, which specifies the date and time when a
 * calendar component was created or last modified.
 * <p>
 * The {@code DTSTAMP} property indicates when the calendar component instance was created or last
 * modified by the calendar user agent. This property is required for most calendar components and
 * provides important metadata about the component's lifecycle.
 * <p>
 * Key characteristics of the date/time stamp:
 * <ul>
 *   <li>Always expressed in UTC time (with 'Z' suffix)</li>
 *   <li>Automatically set by the calendar application</li>
 *   <li>Updated whenever the component is modified</li>
 *   <li>Used for synchronization and conflict resolution</li>
 * </ul>
 * <p>
 * The {@code DTSTAMP} property is essential for:
 * <ul>
 *   <li>Calendar synchronization protocols</li>
 *   <li>Determining the most recent version of a component</li>
 *   <li>Conflict resolution in collaborative calendaring</li>
 *   <li>Audit trails and change tracking</li>
 *   <li>Performance optimization in calendar applications</li>
 * </ul>
 * <p>
 * Unlike {@code CREATED} and {@code LAST-MODIFIED} properties, {@code DTSTAMP} is updated every
 * time the component is processed, even if no substantive changes are made. This makes it useful
 * for tracking when components were last processed by the calendar system.
 * <p>
 * The value is always formatted in UTC using the pattern: {@code YYYYMMDDTHHMMSSZ}
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DateTimeStamp implements ComponentProperty {

    /**
     * The date and time stamp value. This represents when the component was created or
     * last modified.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new DateTimeStamp instance with the specified date/time value.
     *
     * @param value the date and time stamp value
     */
    private DateTimeStamp(ZonedDateTime value) {
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a DateTimeStamp.
     *
     * @return a new DateTimeStampBuilder instance
     */
    public static DateTimeStampBuilder builder() {
        return new DateTimeStampBuilder();
    }

    /**
     * Builder class for constructing DateTimeStamp instances.
     * <p>
     * This builder provides a simple factory method for creating {@code DateTimeStamp} instances
     * with the specified date/time value.
     */
    public static class DateTimeStampBuilder {
        /**
         * Private constructor to enforce use of the factory method.
         */
        private DateTimeStampBuilder() {
        }

        /**
         * Creates a new DateTimeStamp instance with the specified date/time value.
         *
         * @param value the date and time stamp value
         * @return a new DateTimeStamp instance
         */
        public DateTimeStamp build(ZonedDateTime value) {
            return new DateTimeStamp(value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this date/time stamp property.
     * <p>
     * The format follows the iCalendar specification: {@code DTSTAMP:YYYYMMDDTHHMMSSZ}. The value
     * is always formatted in UTC with the 'Z' suffix.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "DTSTAMP:" + value.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
