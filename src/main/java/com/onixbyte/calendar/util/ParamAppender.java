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

import com.onixbyte.calendar.parameter.*;

import java.util.Objects;

/**
 * Utility class for appending iCalendar parameters to a StringBuilder.
 * <p>
 * This class provides a convenient way to append parameter strings to iCalendar
 * property lines. It handles the proper formatting of parameters including the
 * semicolon separator and parameter formatting.
 * <p>
 * Parameters in iCalendar format are appended after the property name and before
 * the property value, separated by semicolons. For example:
 * <pre>
 * DTSTART;TZID=America/New_York:20231225T120000
 * ATTENDEE;CN=John Doe;RSVP=TRUE:mailto:john@example.com
 * </pre>
 * <p>
 * This utility automatically handles null parameters by skipping them, ensuring
 * that only valid parameters are included in the output.
 * <p>
 * Usage example:
 * <pre>
 * StringBuilder builder = new StringBuilder("DTSTART");
 * ParamAppender appender = ParamAppender.of(builder);
 * appender.append(timeZoneId);
 * appender.append(valueType);
 * builder.append(":").append(value);
 * </pre>
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ParamAppender {

    /**
     * The StringBuilder instance to which parameters are appended.
     */
    private final StringBuilder builder;

    /**
     * Constructs a new ParamAppender with the specified StringBuilder.
     *
     * @param builder the StringBuilder to append parameters to
     */
    private ParamAppender(StringBuilder builder) {
        this.builder = builder;
    }

    /**
     * Creates a new ParamAppender instance for the specified StringBuilder.
     *
     * @param builder the StringBuilder to append parameters to
     * @return a new ParamAppender instance
     */
    public static ParamAppender of(StringBuilder builder) {
        return new ParamAppender(builder);
    }

    /**
     * Appends the specified parameter to the StringBuilder.
     * <p>
     * If the parameter is not null, it is formatted and appended to the builder
     * with a semicolon separator. If the parameter is null, it is ignored.
     *
     * @param param the parameter to append, may be null
     * @return the underlying StringBuilder for method chaining
     */
    public StringBuilder append(Parameter param) {
        if (Objects.nonNull(param)) {
            builder.append(";").append(param.formatted());
        }

        return builder;
    }
}
