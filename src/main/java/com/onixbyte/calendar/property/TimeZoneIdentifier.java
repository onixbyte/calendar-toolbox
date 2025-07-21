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

import java.time.ZoneId;

/**
 * Represents the {@code TZID} property in an iCalendar component.
 * <p>
 * This property specifies the identifier for the time zone definition for a time component in the
 * property value. This property parameter is commonly used with date-time properties to specify the
 * time zone for interpreting the date-time value.
 * <p>
 * The property value is a time zone identifier that references a time zone definition in the
 * iCalendar object.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class TimeZoneIdentifier implements ComponentProperty {

    /**
     * The time zone identifier value.
     */
    private final ZoneId value;

    /**
     * Constructs a new {@code TimeZoneIdentifier} instance with the specified time zone.
     *
     * @param value the time zone identifier
     */
    private TimeZoneIdentifier(final ZoneId value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code TimeZoneIdentifier} instances.
     *
     * @return a new {@code TimeZoneIdentifierBuilder}
     */
    public static TimeZoneIdentifierBuilder builder() {
        return new TimeZoneIdentifierBuilder();
    }

    /**
     * Builder class for creating {@code TimeZoneIdentifier} instances.
     */
    public static class TimeZoneIdentifierBuilder {
        /**
         * The time zone identifier value.
         */
        private ZoneId zoneId;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private TimeZoneIdentifierBuilder() {
        }

        /**
         * Builds a new {@code TimeZoneIdentifier} instance with the specified zone ID.
         *
         * @param value the time zone identifier as a {@code ZoneId}
         * @return a new {@code TimeZoneIdentifier} instance
         */
        public TimeZoneIdentifier build(ZoneId value) {
            return new TimeZoneIdentifier(value);
        }

        /**
         * Builds a new {@code TimeZoneIdentifier} instance with the specified zone ID string.
         *
         * @param value the time zone identifier as a string
         * @return a new {@code TimeZoneIdentifier} instance
         * @throws java.time.DateTimeException if the zone ID string is invalid
         */
        public TimeZoneIdentifier build(String value) {
            return new TimeZoneIdentifier(ZoneId.of(value));
        }
    }

    /**
     * Returns the formatted string representation of this time zone identifier property for
     * inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code TZID} property string
     */
    @Override
    public String formatted() {
        return "TZID:" + value.getId();
    }
}
