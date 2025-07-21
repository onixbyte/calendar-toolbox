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

package com.onixbyte.calendar.value;

import com.onixbyte.calendar.util.Formatters;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Represents a free/busy time value in an iCalendar property.
 * <p>
 * This class encapsulates a time period that defines when someone is free or busy. It consists of a
 * start time and a duration that defines the length of the free/busy period.
 * <p>
 * The formatted output follows RFC 5545 specifications for free/busy time values.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class FreeBusyTimeValue implements PropertyValue {

    /**
     * The start time of the free/busy period.
     */
    private final ZonedDateTime startTime;

    /**
     * The duration of the free/busy period.
     */
    private final Duration duration;

    /**
     * Constructs a new {@code FreeBusyTimeValue} with the specified start time and duration.
     *
     * @param startTime the start time of the free/busy period
     * @param duration  the duration of the free/busy period
     */
    private FreeBusyTimeValue(ZonedDateTime startTime, Duration duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Creates a new free/busy time value with the specified start time and duration.
     *
     * @param startTime the start time of the free/busy period
     * @param duration  the duration of the free/busy period
     * @return a new {@code FreeBusyTimeValue} instance
     */
    public static FreeBusyTimeValue of(ZonedDateTime startTime, Duration duration) {
        return new FreeBusyTimeValue(startTime, duration);
    }

    /**
     * Returns the formatted string representation of this free/busy time value for inclusion in an
     * iCalendar property.
     * <p>
     * The format follows RFC 5545 specifications for free/busy time periods.
     *
     * @return the formatted free/busy time string in the format "startTime/duration"
     */
    @Override
    public String formatted() {
        return startTime.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER) + "/" +
                duration.toString();
    }
}
