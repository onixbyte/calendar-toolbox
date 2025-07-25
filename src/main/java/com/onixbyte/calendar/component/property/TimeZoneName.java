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

import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.PropertyComposer;

/**
 * Represents the {@code TZNAME} property in an iCalendar time zone component.
 * <p>
 * This property specifies the customary designation for a time zone description. It provides a
 * human-readable name for the time zone, which can be localised using the optional
 * language parameter.
 * <p>
 * This property is typically used within {@code VTIMEZONE} components to provide descriptive names
 * for different time zone periods (standard time or daylight time).
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class TimeZoneName implements ComponentProperty {

    /**
     * The optional language parameter for this time zone name.
     */
    private final Language language;

    /**
     * The time zone name text value.
     */
    private final String value;

    /**
     * Constructs a new {@code TimeZoneName} instance with the specified parameters.
     *
     * @param language the optional language parameter
     * @param value    the time zone name text value
     */
    private TimeZoneName(Language language, String value) {
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code TimeZoneName} instances.
     *
     * @return a new {@code TimeZoneNameBuilder}
     */
    public static TimeZoneNameBuilder builder() {
        return new TimeZoneNameBuilder();
    }

    /**
     * Builder class for creating {@code TimeZoneName} instances with optional parameters.
     */
    public static class TimeZoneNameBuilder {
        /**
         * The optional language parameter.
         */
        private Language language;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private TimeZoneNameBuilder() {
        }

        /**
         * Sets the language parameter for this time zone name.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public TimeZoneNameBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Builds a new {@code TimeZoneName} instance with the specified time zone name value.
         *
         * @param value the time zone name text value
         * @return a new {@code TimeZoneName} instance
         */
        public TimeZoneName build(String value) {
            return new TimeZoneName(language, value);
        }
    }

    /**
     * Returns the formatted string representation of this time zone name property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code TZNAME} property string
     */
    @Override
    public String formatted() {
        return PropertyComposer.of("TZNAME")
                .append(language)
                .end(value);
    }
}
