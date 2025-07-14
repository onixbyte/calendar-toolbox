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

package com.onixbyte.calendar.parameter;

import java.time.ZoneId;

/**
 * Represents the iCalendar {@code TZID} parameter, which specifies the time zone identifier
 * for a time-related property. This parameter is used to associate date and time values with
 * a specific time zone, ensuring correct interpretation across different locales.
 * <p>
 * Instances of this class are immutable and can be created via the static factory methods
 * {@link #of(ZoneId)} and {@link #of(String)}. The time zone identifier is formatted according
 * to the rules of the underlying {@link ZoneId}.
 *
 * @author siujamo
 */
public final class TimeZoneIdentifier implements Parameter {

    /**
     * The time zone identifier.
     */
    private final ZoneId value;

    /**
     * Constructs a {@code TimeZoneIdentifier} with the specified zone ID.
     *
     * @param value the time zone identifier
     */
    private TimeZoneIdentifier(ZoneId value) {
        this.value = value;
    }

    /**
     * Creates a {@code TimeZoneIdentifier} instance from a {@link ZoneId}.
     *
     * @param value the time zone identifier
     * @return a new instance of {@code TimeZoneIdentifier}
     */
    public static TimeZoneIdentifier of(ZoneId value) {
        return new TimeZoneIdentifier(value);
    }

    /**
     * Creates a {@code TimeZoneIdentifier} instance from a string representing a time zone ID.
     * <p>
     * The string must be a valid time zone ID as recognized by {@link ZoneId#of(String)}.
     *
     * @param value the time zone identifier string
     * @return a new instance of {@code TimeZoneIdentifier}
     */
    public static TimeZoneIdentifier of(String value) {
        return new TimeZoneIdentifier(ZoneId.of(value));
    }

    /**
     * Returns the underlying {@link ZoneId} instance.
     *
     * @return the time zone identifier as a {@code ZoneId}
     */
    public ZoneId getZoneId() {
        return value;
    }

    /**
     * Returns the formatted {@code TZID} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code TZID=zone-id} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "TZID=" + value.getId();
    }
}
