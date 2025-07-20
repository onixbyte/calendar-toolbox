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

import com.onixbyte.calendar.parameter.TimeZoneIdentifier;
import com.onixbyte.calendar.parameter.ValueDataType;
import com.onixbyte.calendar.util.ParamAppender;

import java.time.ZonedDateTime;

/**
 * Represents the {@code DUE} property in an iCalendar component.
 * <p>
 * This property defines the date and time when a calendar component (typically a to-do) is due. It
 * is commonly used with {@code VTODO} components to specify when a task should be completed.
 * <p>
 * The property supports optional time zone and value data type parameters for precise
 * date-time representation.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class DateTimeDue implements ComponentProperty, DateTimeProperty {

    /**
     * The optional time zone identifier parameter for this due date-time.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The optional value data type parameter for this due date-time.
     */
    private final ValueDataType valueDataType;

    /**
     * The due date-time value.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new {@code DateTimeDue} instance with the specified parameters.
     *
     * @param timeZoneIdentifier the optional time zone identifier parameter
     * @param valueDataType      the optional value data type parameter
     * @param value              the due date-time value
     */
    private DateTimeDue(
            TimeZoneIdentifier timeZoneIdentifier,
            ValueDataType valueDataType,
            ZonedDateTime value
    ) {
        DateTimeProperty.checkValueDataType(valueDataType);

        this.timeZoneIdentifier = timeZoneIdentifier;
        this.valueDataType = valueDataType;
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code DateTimeDue} instances.
     *
     * @return a new {@code DateTimeDueBuilder}
     */
    public static DateTimeDueBuilder builder() {
        return new DateTimeDueBuilder();
    }

    /**
     * Builder class for creating {@code DateTimeDue} instances with optional parameters.
     */
    public static class DateTimeDueBuilder {
        /**
         * The optional time zone identifier parameter.
         */
        private TimeZoneIdentifier timeZoneIdentifier;

        /**
         * The optional value data type parameter.
         */
        private ValueDataType valueDataType;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private DateTimeDueBuilder() {
        }

        /**
         * Sets the time zone identifier parameter for the due date-time.
         *
         * @param timeZoneIdentifier the time zone identifier parameter
         * @return this builder instance for method chaining
         */
        public DateTimeDueBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Sets the value data type parameter for the due date-time.
         *
         * @param valueDataType the value data type parameter
         * @return this builder instance for method chaining
         */
        public DateTimeDueBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Builds a new {@code DateTimeDue} instance with the specified due date-time value.
         *
         * @param value the due date-time value
         * @return a new {@code DateTimeDue} instance
         */
        public DateTimeDue build(ZonedDateTime value) {
            return new DateTimeDue(timeZoneIdentifier, valueDataType, value);
        }
    }

    /**
     * Returns the value data type parameter for this due date-time.
     *
     * @return the value data type parameter, or {@code null} if not specified
     */
    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Returns the time zone identifier parameter for this due date-time.
     *
     * @return the time zone identifier parameter, or {@code null} if not specified
     */
    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    /**
     * Returns the formatted string representation of this due date-time property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code DUE} property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("DUE");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(valueDataType);
        paramAppender.append(timeZoneIdentifier);

        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
