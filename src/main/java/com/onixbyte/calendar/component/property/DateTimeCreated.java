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
 * Represents the {@code CREATED} property in an iCalendar component.
 * <p>
 * This property specifies the date and time when a calendar component was created. It is used to
 * record when the calendar entry was first created in the calendar store.
 * <p>
 * The property value is always expressed in UTC time format according to RFC 5545 specifications.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class DateTimeCreated implements ComponentProperty {

    /**
     * The creation date-time value in UTC.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new {@code DateTimeCreated} instance with the specified creation date-time.
     *
     * @param value the creation date-time value in UTC
     */
    private DateTimeCreated(ZonedDateTime value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code DateTimeCreated} instances.
     *
     * @return a new {@code DateTimeCreatedBuilder}
     */
    public static DateTimeCreatedBuilder builder() {
        return new DateTimeCreatedBuilder();
    }

    /**
     * Builder class for creating {@code DateTimeCreated} instances.
     */
    public static class DateTimeCreatedBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private DateTimeCreatedBuilder() {
        }

        /**
         * Builds a new {@code DateTimeCreated} instance with the specified creation date-time value.
         *
         * @param value the creation date-time value in UTC
         * @return a new {@code DateTimeCreated} instance
         */
        public DateTimeCreated build(ZonedDateTime value) {
            return new DateTimeCreated(value);
        }
    }

    /**
     * Returns the formatted string representation of this creation date-time property for inclusion
     * in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and uses UTC timestamp format.
     *
     * @return the formatted {@code CREATED} property string
     */
    @Override
    public String formatted() {
        return "CREATED:" + value.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
