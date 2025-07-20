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

import java.net.URI;
import java.sql.Time;

/**
 * Represents the {@code TZURL} property in an iCalendar time zone component.
 * <p>
 * This property provides a means for a {@code VTIMEZONE} component to point to
 * a network location that can be used to retrieve an up-to-date version of
 * itself. This property should be specified in a {@code VTIMEZONE} component
 * when the definition might be updated.
 * <p>
 * The property value is a URI that points to a resource containing the time zone
 * definition in iCalendar format.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class TimeZoneUrl implements ComponentProperty {

    /**
     * The URI value pointing to the time zone definition resource.
     */
    private final URI value;

    /**
     * Constructs a new {@code TimeZoneUrl} instance with the specified URI.
     *
     * @param value the URI pointing to the time zone definition resource
     */
    private TimeZoneUrl(URI value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code TimeZoneUrl} instances.
     *
     * @return a new {@code TimeZoneUrlBuilder}
     */
    public static TimeZoneUrlBuilder builder() {
        return new TimeZoneUrlBuilder();
    }

    /**
     * Builder class for creating {@code TimeZoneUrl} instances.
     */
    public static class TimeZoneUrlBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private TimeZoneUrlBuilder() {
        }

        /**
         * Builds a new {@code TimeZoneUrl} instance with the specified URI.
         *
         * @param value the URI pointing to the time zone definition resource
         * @return a new {@code TimeZoneUrl} instance
         */
        public TimeZoneUrl build(URI value) {
            return new TimeZoneUrl(value);
        }

        /**
         * Builds a new {@code TimeZoneUrl} instance with the specified URI string.
         *
         * @param value the URI string pointing to the time zone definition resource
         * @return a new {@code TimeZoneUrl} instance
         * @throws IllegalArgumentException if the string violates RFC 2396
         */
        public TimeZoneUrl build(String value) {
            return new TimeZoneUrl(URI.create(value));
        }
    }

    /**
     * Returns the formatted string representation of this time zone URL property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code TZURL} property string
     */
    @Override
    public String formatted() {
        return "TZURL:" + value.toString();
    }
}
