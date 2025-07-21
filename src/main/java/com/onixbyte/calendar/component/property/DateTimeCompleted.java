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
 * Represents the {@code COMPLETED} property in an iCalendar component.
 * <p>
 * This property defines the date and time when a calendar component (typically a to-do)
 * was completed. It is commonly used with {@code VTODO} components to track when a task
 * was finished.
 * <p>
 * The property value is always expressed in UTC time format according to RFC 5545 specifications.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class DateTimeCompleted implements ComponentProperty {

    /**
     * The completion date-time value in UTC.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new {@code DateTimeCompleted} instance with the specified completion date-time.
     *
     * @param value the completion date-time value in UTC
     */
    private DateTimeCompleted(ZonedDateTime value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code DateTimeCompleted} instances.
     *
     * @return a new {@code DateTimeCompletedBuilder}
     */
    public static DateTimeCompletedBuilder builder() {
        return new DateTimeCompletedBuilder();
    }

    /**
     * Builder class for creating {@code DateTimeCompleted} instances.
     */
    public static class DateTimeCompletedBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private DateTimeCompletedBuilder() {
        }

        /**
         * Builds a new {@code DateTimeCompleted} instance with the specified completion
         * date-time value.
         *
         * @param value the completion date-time value in UTC
         * @return a new {@code DateTimeCompleted} instance
         */
        public DateTimeCompleted build(ZonedDateTime value) {
            return new DateTimeCompleted(value);
        }
    }

    /**
     * Returns the formatted string representation of this completion date-time property for
     * inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and uses UTC timestamp format.
     *
     * @return the formatted {@code COMPLETED} property string
     */
    @Override
    public String formatted() {
        return "COMPLETED:" + value.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
