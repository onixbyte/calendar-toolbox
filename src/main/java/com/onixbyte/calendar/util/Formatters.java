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

public final class Formatters {

    public static final DateTimeFormatter ICALENDAR_UTC_TIMESTAMP_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmss'Z'")
            .withZone(ZoneOffset.UTC);

    public static final DateTimeFormatter ICALENDAR_TIMESTAMP_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd'T'HHmmss");

    public static final DateTimeFormatter ICALENDAR_DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyyMMdd");

    public static String formatDuration(Duration duration) {
        return "DURATION:" + duration.toString();
    }

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
     * @return a new StringBuilder containing the folded content
     */
    public static String folding(StringBuilder builder) {
        final var firstLineMaxLength = 75;
        final var subsequentLineMaxLength = 74;

        var folded = new StringBuilder();

        var length = builder.length();
        var pos = 0;

        // Handle first line with max 75 chars
        var end = Math.min(pos + firstLineMaxLength, length);
        folded.append(builder, pos, end);
        folded.append("\r\n");
        pos = end;

        // Handle subsequent lines with max 74 chars, each starting with a space
        while (pos < length) {
            folded.append(' '); // folding space
            end = Math.min(pos + subsequentLineMaxLength, length);
            folded.append(builder, pos, end);
            folded.append("\r\n");
            pos = end;
        }

        return folded.toString();
    }
}
