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
 * Enumeration representing the iCalendar {@code VERSION} property values.
 * <p>
 * The {@code VERSION} property specifies the identifier corresponding to the highest version number
 * or the minimum and maximum range of the iCalendar specification that is required in order to
 * interpret the iCalendar object.
 * <p>
 * This property is required and must appear exactly once in an iCalendar object. It identifies the
 * version of the iCalendar specification that the calendar data conforms to.
 * <p>
 * Currently, version 2.0 is the standard version as defined in RFC 5545. This version superseded
 * the earlier 1.0 version defined in RFC 2445.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Version implements CalendarProperty {

    /**
     * iCalendar specification version 2.0.
     * <p>
     * This is the current standard version of the iCalendar specification as defined in RFC 5545.
     * It includes all the features and fixes from the previous 1.0 version.
     */
    VERSION_2_0("2.0"),
    ;

    /**
     * The version number label used in the iCalendar format.
     */
    private final String label;

    /**
     * Constructs a Version enum value with the specified label.
     *
     * @param label the version number label
     */
    Version(String label) {
        this.label = label;
    }

    /**
     * Returns the formatted iCalendar representation of this version property.
     * <p>
     * The format follows the iCalendar specification: {@code VERSION:value}
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "VERSION:" + label;
    }
}
