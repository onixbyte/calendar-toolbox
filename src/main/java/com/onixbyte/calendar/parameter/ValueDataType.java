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

/**
 * Enumerates the different data types that property values can have in iCalendar specifications.
 * This enum implements the {@code Parameter} interface, allowing its values to be formatted
 * into standard string representations for inclusion in calendar data structures.
 * <p>
 * Each data type defines the format and interpretation of the property value. For example,
 * {@code DATE_TIME} indicates a value with date and time components, while {@code URI}
 * represents a uniform resource identifier.
 * <p>
 * The formatted string replaces underscores with hyphens to comply with iCalendar syntax
 * requirements (e.g., {@code DATE-TIME}).
 *
 * @author siujamo
 */
public enum ValueDataType implements Parameter {

    /**
     * Indicates a binary data value.
     */
    BINARY,

    /**
     * Indicates a boolean data value (true or false).
     */
    BOOLEAN,

    /**
     * Indicates a calendar address value.
     */
    CAL_ADDRESS,

    /**
     * Indicates a date-only data value.
     */
    DATE,

    /**
     * Indicates a date and time data value.
     */
    DATE_TIME,

    /**
     * Indicates a duration data value.
     */
    DURATION,

    /**
     * Indicates a floating-point number data value.
     */
    FLOAT,

    /**
     * Indicates an integer number data value.
     */
    INTEGER,

    /**
     * Indicates a period of time data value.
     */
    PERIOD,

    /**
     * Indicates a recurrence rule data value.
     */
    RECUR,

    /**
     * Indicates a text data value.
     */
    TEXT,

    /**
     * Indicates a time-only data value.
     */
    TIME,

    /**
     * Indicates an unknown or unspecified data type.
     */
    UNKNOWN,

    /**
     * Indicates a uniform resource identifier data value.
     */
    URI,

    /**
     * Indicates a UTC offset data value.
     */
    UTC_OFFSET,

    /**
     * Indicates an XML reference data value.
     */
    XML_REFERENCE,

    /**
     * Indicates a unique identifier data value.
     */
    UID,
    ;

    /**
     * Returns the formatted string representation of this data type, converting underscores
     * to hyphens to meet iCalendar syntax standards. The format is {@code VALUE=data-type}.
     *
     * @return a string formatted as {@code VALUE=data-type}
     */
    @Override
    public String formatted() {
        return "VALUE=" + name().replaceAll("_", "-");
    }
}
