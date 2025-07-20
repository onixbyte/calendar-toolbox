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
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a period of time value in an iCalendar property.
 * <p>
 * This class can represent a time period in two different formats:
 * <ul>
 *   <li>Explicit period: defined by a start time and an end time</li>
 *   <li>Start period: defined by a start time and a duration</li>
 * </ul>
 * <p>
 * The formatted output follows RFC 5545 specifications for period values.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public class PeriodOfTime implements PropertyValue {

    /**
     * The explicit period value (start time and end time), if this period uses explicit format.
     */
    private final Explicit explicitValue;

    /**
     * The start period value (start time and duration), if this period uses start format.
     */
    private final Start startValue;

    /**
     * Constructs a new {@code PeriodOfTime} instance with the specified explicit or start period.
     *
     * @param explicitValue the explicit period value (start and end times)
     * @param startValue    the start period value (start time and duration)
     */
    private PeriodOfTime(Explicit explicitValue, Start startValue) {
        this.explicitValue = explicitValue;
        this.startValue = startValue;
    }

    /**
     * Creates a period of time defined by explicit start and end times.
     *
     * @param startTime the start time of the period
     * @param endTime   the end time of the period
     * @return a new {@code PeriodOfTime} instance using explicit format
     */
    public static PeriodOfTime ofExplicit(LocalDateTime startTime, LocalDateTime endTime) {
        return new PeriodOfTime(new Explicit(startTime, endTime), null);
    }

    /**
     * Creates a period of time defined by a start time and duration.
     *
     * @param startTime the start time of the period
     * @param duration  the duration of the period
     * @return a new {@code PeriodOfTime} instance using start format
     */
    public static PeriodOfTime ofStart(LocalDateTime startTime, Duration duration) {
        return new PeriodOfTime(null, new Start(startTime, duration));
    }

    /**
     * Returns the formatted string representation of this period of time for inclusion in an
     * iCalendar property value.
     * <p>
     * The format follows RFC 5545 specifications for period values.
     *
     * @return the formatted period string
     */
    @Override
    public String formatted() {
        if (Objects.nonNull(explicitValue)) {
            return explicitValue.formatted();
        } else {
            return startValue.formatted();
        }
    }

    /**
     * Represents an explicit period defined by start and end times.
     */
    public static class Explicit {
        /**
         * The start time of the period.
         */
        private final LocalDateTime startTime;

        /**
         * The end time of the period.
         */
        private final LocalDateTime endTime;

        /**
         * Constructs a new explicit period with the specified start and end times.
         *
         * @param startTime the start time of the period
         * @param endTime   the end time of the period
         */
        private Explicit(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        /**
         * Returns the formatted string representation of this explicit period.
         *
         * @return the formatted period string in the format "startTime/endTime"
         */
        public String formatted() {
            var formatter = Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
            return startTime.format(formatter) + "/" + endTime.format(formatter);
        }
    }

    /**
     * Represents a start period defined by start time and duration.
     */
    public static class Start {
        /**
         * The start time of the period.
         */
        private final LocalDateTime startTime;

        /**
         * The duration of the period.
         */
        private final Duration duration;

        /**
         * Constructs a new start period with the specified start time and duration.
         *
         * @param startTime the start time of the period
         * @param duration  the duration of the period
         */
        private Start(LocalDateTime startTime, Duration duration) {
            this.startTime = startTime;
            this.duration = duration;
        }

        /**
         * Returns the formatted string representation of this start period.
         *
         * @return the formatted period string in the format "startTime/duration"
         */
        public String formatted() {
            var formatter = Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
            return startTime.format(formatter) + "/" + duration.toString();
        }
    }
}
