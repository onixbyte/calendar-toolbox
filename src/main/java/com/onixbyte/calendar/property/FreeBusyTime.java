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

import com.onixbyte.calendar.parameter.FreeBusyTimeType;
import com.onixbyte.calendar.util.Formatters;
import com.onixbyte.calendar.util.ParamAppender;
import com.onixbyte.calendar.value.FreeBusyTimeValue;

import java.util.List;
import java.util.Objects;

/**
 * Represents the {@code FREEBUSY} property in an iCalendar free/busy component.
 * <p>
 * This property defines one or more free or busy time intervals. It is used to specify times when
 * a calendar user is free or busy. The property can contain multiple time periods and supports
 * optional free/busy time type specification.
 * <p>
 * The property value consists of one or more period values that define the free/busy
 * time intervals.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class FreeBusyTime implements ComponentProperty {

    /**
     * The optional free/busy time type parameter.
     */
    private final FreeBusyTimeType fbType;

    /**
     * The list of free/busy time period values.
     */
    private final List<FreeBusyTimeValue> values;

    /**
     * Constructs a new {@code FreeBusyTime} instance with the specified parameters.
     *
     * @param fbType the optional free/busy time type parameter
     * @param values the list of free/busy time period values (must not be empty)
     * @throws IllegalArgumentException if the values list is null or empty
     */
    private FreeBusyTime(FreeBusyTimeType fbType, List<FreeBusyTimeValue> values) {
        if (Objects.isNull(values) || values.isEmpty()) {
            throw new IllegalArgumentException("Free/Busy Time values should not be empty.");
        }

        this.fbType = fbType;
        this.values = values;
    }

    /**
     * Creates a new builder for constructing {@code FreeBusyTime} instances.
     *
     * @return a new {@code FreeBusyTimeBuilder}
     */
    public static FreeBusyTimeBuilder builder() {
        return new FreeBusyTimeBuilder();
    }

    /**
     * Builder class for creating {@code FreeBusyTime} instances with optional parameters.
     */
    public static class FreeBusyTimeBuilder {
        /**
         * The optional free/busy time type parameter.
         */
        private FreeBusyTimeType fbType;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private FreeBusyTimeBuilder() {
        }

        /**
         * Sets the free/busy time type parameter for this free/busy time property.
         *
         * @param fbType the free/busy time type parameter
         * @return this builder instance for method chaining
         */
        public FreeBusyTimeBuilder withFreeBusyType(FreeBusyTimeType fbType) {
            this.fbType = fbType;
            return this;
        }

        /**
         * Builds a new {@code FreeBusyTime} instance with the specified list of time period values.
         *
         * @param values the list of free/busy time period values
         * @return a new {@code FreeBusyTime} instance
         * @throws IllegalArgumentException if the values list is null or empty
         */
        public FreeBusyTime build(List<FreeBusyTimeValue> values) {
            return new FreeBusyTime(fbType, values);
        }

        /**
         * Builds a new {@code FreeBusyTime} instance with the specified time period values.
         *
         * @param values the free/busy time period values as varargs
         * @return a new {@code FreeBusyTime} instance
         * @throws IllegalArgumentException if no values are provided
         */
        public FreeBusyTime build(FreeBusyTimeValue... values) {
            return new FreeBusyTime(fbType, List.of(values));
        }
    }

    /**
     * Returns the formatted string representation of this free/busy time property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters. Multiple
     * time periods are separated by commas.
     *
     * @return the formatted {@code FREEBUSY} property string
     */
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("FREEBUSY");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(fbType);

        builder.append(":").append(Formatters.formatValue(",", values));
        return builder.toString();
    }
}
