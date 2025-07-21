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

import com.onixbyte.calendar.value.UtcOffset;

/**
 * Represents the {@code TZOFFSETFROM} property in an iCalendar time zone component.
 * <p>
 * This property specifies the offset that is in use prior to the onset of this time
 * zone observance. It is used to specify the UTC offset for the time zone period that is
 * being replaced.
 * <p>
 * The property value is a UTC offset value that indicates the number of hours and minutes from
 * Coordinated Universal Time (UTC).
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class TimeZoneOffsetFrom implements ComponentProperty {

    /**
     * The UTC offset value for the previous time zone period.
     */
    private final UtcOffset value;

    /**
     * Constructs a new {@code TimeZoneOffsetFrom} instance with the specified UTC offset.
     *
     * @param value the UTC offset value
     */
    private TimeZoneOffsetFrom(UtcOffset value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code TimeZoneOffsetFrom} instances.
     *
     * @return a new {@code TimeZoneOffsetFromBuilder}
     */
    public static TimeZoneOffsetFromBuilder builder() {
        return new TimeZoneOffsetFromBuilder();
    }

    /**
     * Builder class for creating {@code TimeZoneOffsetFrom} instances.
     */
    public static class TimeZoneOffsetFromBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private TimeZoneOffsetFromBuilder() {
        }

        /**
         * Builds a new {@code TimeZoneOffsetFrom} instance with the specified UTC offset value.
         *
         * @param value the UTC offset value
         * @return a new {@code TimeZoneOffsetFrom} instance
         */
        public TimeZoneOffsetFrom build(UtcOffset value) {
            return new TimeZoneOffsetFrom(value);
        }
    }

    /**
     * Returns the formatted string representation of this time zone offset from property for
     * inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code TZOFFSETFROM} property string
     */
    @Override
    public String formatted() {
        return "TZOFFSETFROM:" + value.formatted();
    }
}
