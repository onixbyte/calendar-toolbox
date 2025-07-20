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

/**
 * Represents the {@code GEO} property in an iCalendar component.
 * <p>
 * This property specifies information related to the global position for the activity specified by
 * a calendar component. The property value consists of latitude and longitude coordinates expressed
 * as decimal degrees.
 * <p>
 * The latitude represents the location north or south of the equator, and the longitude represents
 * the location east or west of the prime meridian. Both values are expressed as floating-point
 * numbers in decimal degrees.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class GeographicPosition implements ComponentProperty {

    /**
     * The latitude coordinate in decimal degrees. Positive values represent north of the equator,
     * negative values represent south.
     */
    private final double latitude;

    /**
     * The longitude coordinate in decimal degrees. Positive values represent east of the prime
     * meridian, negative values represent west.
     */
    private final double longitude;

    /**
     * Constructs a new {@code GeographicPosition} instance with the specified coordinates.
     *
     * @param latitude  the latitude coordinate in decimal degrees
     * @param longitude the longitude coordinate in decimal degrees
     */
    private GeographicPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Creates a new builder for constructing {@code GeographicPosition} instances.
     *
     * @return a new {@code GeographicPositionBuilder}
     */
    public static GeographicPositionBuilder builder() {
        return new GeographicPositionBuilder();
    }

    /**
     * Builder class for creating {@code GeographicPosition} instances.
     */
    public static class GeographicPositionBuilder {

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private GeographicPositionBuilder() {
        }

        /**
         * Builds a new {@code GeographicPosition} instance with the specified coordinates.
         *
         * @param latitude  the latitude coordinate in decimal degrees
         * @param longitude the longitude coordinate in decimal degrees
         * @return a new {@code GeographicPosition} instance
         */
        public GeographicPosition build(double latitude, double longitude) {
            return new GeographicPosition(latitude, longitude);
        }
    }

    /**
     * Returns the formatted string representation of this geographic position property for
     * inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications with latitude and longitude separated by
     * a semicolon.
     *
     * @return the formatted {@code GEO} property string in the format "{@code GEO:latitude;longitude}"
     */
    @Override
    public String formatted() {
        return "GEO:" + latitude + ";" + longitude;
    }

}
