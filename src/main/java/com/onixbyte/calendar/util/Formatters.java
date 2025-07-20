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

package com.onixbyte.calendar.util;

import com.onixbyte.calendar.value.PropertyValue;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility class providing formatting methods for iCalendar properties and values.
 * <p>
 * The Formatters class contains static methods and constants for formatting various iCalendar data
 * types according to RFC 5545 specifications. This includes date and time formatting,
 * duration formatting, and value list formatting.
 * <p>
 * This class provides predefined {@link DateTimeFormatter} instances for common iCalendar date and
 * time formats, as well as methods for formatting complex property values.
 * <p>
 * All methods in this class are static and the class cannot be instantiated.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Formatters {

    /**
     * Date and time formatter for UTC timestamps in iCalendar format.
     * <p>
     * This formatter produces timestamps in the format "{@code yyyyMMdd'T'HHmmss'Z'}" and is
     * configured to use UTC timezone.
     */
    public static final DateTimeFormatter ICALENDAR_UTC_TIMESTAMP_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmss'Z'")
            .withZone(ZoneOffset.UTC);

    /**
     * Date and time formatter for local timestamps in iCalendar format.
     * <p>
     * This formatter produces timestamps in the format "{@code yyyyMMdd'T'HHmmss}" without
     * timezone information.
     */
    public static final DateTimeFormatter ICALENDAR_TIMESTAMP_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmss");

    /**
     * Date formatter for iCalendar date values.
     * <p>
     * This formatter produces dates in the format "{@code yyyyMMdd}".
     */
    public static final DateTimeFormatter ICALENDAR_DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd");

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This class is a utility class with only static methods and should not be instantiated.
     */
    private Formatters() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Formats a Duration object into an iCalendar DURATION property string.
     * <p>
     * This method converts a Java Duration object into the iCalendar {@code DURATION} property
     * format as specified in RFC 5545.
     *
     * @param duration the duration to format
     * @return a formatted DURATION property string
     */
    public static String formatDuration(Duration duration) {
        return "DURATION:" + duration.toString();
    }

    /**
     * Formats a list of PropertyValue objects into a delimited string.
     * <p>
     * This method takes a list of PropertyValue objects and formats them into
     * a single string with the specified delimiter between values.
     *
     * @param delimiter the delimiter to use between values
     * @param values    the list of PropertyValue objects to format
     * @return a formatted string with delimited values
     */
    public static String formatValue(String delimiter, List<? extends PropertyValue> values) {
        var _values = values.stream()
                .map(PropertyValue::formatted)
                .toList();
        return String.join(delimiter, _values);
    }

    /**
     * Folds the lines of text in the given StringBuilder according to the iCalendar specification.
     * <p>
     * Lines of text SHOULD NOT be longer than 75 octets, excluding the line break. Long content
     * lines are split into multiple lines using a line “folding” technique. That is, a long line
     * is split between any two characters by inserting a CRLF immediately followed by a single
     * linear white-space character (SPACE).
     * <p>
     * For example, a long line:
     * <pre>
     * DESCRIPTION:This is a long description that exists on a long line.
     * </pre>
     * would be folded as:
     * <pre>
     * DESCRIPTION:This is a lo
     *  ng description
     *   that exists on a long line.
     * </pre>
     *
     * @param builder the StringBuilder containing the text line(s) to be folded
     * @return a folded string with proper line breaks and spacing
     */
    public static String folding(StringBuilder builder) {
        return folding(builder.toString());
    }

    /**
     * Folds the lines of text in the given StringBuilder according to the iCalendar specification.
     * <p>
     * Lines of text SHOULD NOT be longer than 75 octets, excluding the line break. Long content
     * lines are split into multiple lines using a line “folding” technique. That is, a long line
     * is split between any two characters by inserting a CRLF immediately followed by a single
     * linear white-space character (SPACE).
     * <p>
     * For example, a long line:
     * <pre>
     * DESCRIPTION:This is a long description that exists on a long line.
     * </pre>
     * would be folded as:
     * <pre>
     * DESCRIPTION:This is a lo
     *  ng description
     *   that exists on a long line.
     * </pre>
     *
     * @param string the string containing the text line(s) to be folded
     * @return a new StringBuilder containing the folded content
     */
    public static String folding(String string) {
        final var firstLineMaxLength = 75;
        final var subsequentLineMaxLength = 74;

        var folded = new StringBuilder();

        var length = string.length();
        var pos = 0;

        // Handle first line with max 75 chars
        var end = Math.min(pos + firstLineMaxLength, length);
        folded.append(string, pos, end);
        pos = end;

        // Handle subsequent lines with max 74 chars, each starting with a space
        while (pos < length) {
            folded.append("\r\n");
            folded.append(' '); // folding space
            end = Math.min(pos + subsequentLineMaxLength, length);
            folded.append(string, pos, end);
            pos = end;
        }

        return folded.toString();
    }
}
