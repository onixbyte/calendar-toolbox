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

import com.onixbyte.calendar.util.Formatters;

import java.time.ZonedDateTime;

/**
 * Represents the {@code LAST-MODIFIED} property in an iCalendar component.
 * <p>
 * This property specifies the date and time when a calendar component was last
 * modified. It is used to track when the calendar entry was last updated in
 * the calendar store.
 * <p>
 * The property value is always expressed in UTC time format according to
 * RFC 5545 specifications.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class LastModified implements ComponentProperty {

    /**
     * The last modification date-time value in UTC.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new {@code LastModified} instance with the specified last modification date-time.
     *
     * @param value the last modification date-time value in UTC
     */
    private LastModified(ZonedDateTime value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code LastModified} instances.
     *
     * @return a new {@code LastModifiedBuilder}
     */
    public static LastModifiedBuilder builder() {
        return new LastModifiedBuilder();
    }

    /**
     * Builder class for creating {@code LastModified} instances.
     */
    public static class LastModifiedBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private LastModifiedBuilder() {
        }

        /**
         * Builds a new {@code LastModified} instance with the specified last modification date-time value.
         *
         * @param value the last modification date-time value in UTC
         * @return a new {@code LastModified} instance
         */
        public LastModified build(ZonedDateTime value) {
            return new LastModified(value);
        }
    }

    /**
     * Returns the formatted string representation of this last modified property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and uses UTC timestamp format.
     *
     * @return the formatted {@code LAST-MODIFIED} property string
     */
    @Override
    public String formatted() {
        return "LAST-MODIFIED:" + value.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
