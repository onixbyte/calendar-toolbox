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

import com.onixbyte.calendar.parameter.TimeZoneIdentifier;
import com.onixbyte.calendar.parameter.ValueDataType;

import java.time.ZonedDateTime;

/**
 * Represents the {@code EXDATE} property in an iCalendar component.
 * <p>
 * This property defines a list of date-time values that represent exceptions to a recurring
 * calendar component. These dates specify instances that should be excluded from the
 * recurrence pattern.
 * <p>
 * The property supports optional time zone and value data type parameters for precise date-time
 * representation and formatting.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class ExceptionDateTimes implements ComponentProperty, DateTimeProperty {

    /**
     * The optional value data type parameter for this exception date-time.
     */
    private final ValueDataType valueDataType;

    /**
     * The optional time zone identifier parameter for this exception date-time.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The exception date-time value.
     */
    private final ZonedDateTime zonedDateTime;

    /**
     * Constructs a new {@code ExceptionDateTimes} instance with the specified parameters.
     *
     * @param valueDataType      the optional value data type parameter
     * @param timeZoneIdentifier the optional time zone identifier parameter
     * @param zonedDateTime      the exception date-time value
     */
    private ExceptionDateTimes(
            ValueDataType valueDataType,
            TimeZoneIdentifier timeZoneIdentifier,
            ZonedDateTime zonedDateTime
    ) {
        this.valueDataType = valueDataType;
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.zonedDateTime = zonedDateTime;
    }

    /**
     * Creates a new builder for constructing {@code ExceptionDateTimes} instances.
     *
     * @return a new {@code ExceptionDateTimesBuilder}
     */
    public static ExceptionDateTimesBuilder builder() {
        return new ExceptionDateTimesBuilder();
    }

    /**
     * Builder class for creating {@code ExceptionDateTimes} instances with optional parameters.
     */
    public static class ExceptionDateTimesBuilder {
        /**
         * The optional value data type parameter.
         */
        private ValueDataType valueDataType;

        /**
         * The optional time zone identifier parameter.
         */
        private TimeZoneIdentifier timeZoneIdentifier;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private ExceptionDateTimesBuilder() {
        }

        /**
         * Sets the value data type parameter for the exception date-time.
         *
         * @param valueDataType the value data type parameter
         * @return this builder instance for method chaining
         */
        public ExceptionDateTimesBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Sets the time zone identifier parameter for the exception date-time.
         *
         * @param timeZoneIdentifier the time zone identifier parameter
         * @return this builder instance for method chaining
         */
        public ExceptionDateTimesBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Builds a new {@code ExceptionDateTimes} instance with the specified exception
         * date-time value.
         *
         * @param zonedDateTime the exception date-time value
         * @return a new {@code ExceptionDateTimes} instance
         */
        public ExceptionDateTimes build(ZonedDateTime zonedDateTime) {
            return new ExceptionDateTimes(valueDataType, timeZoneIdentifier, zonedDateTime);
        }
    }

    /**
     * Returns the value data type parameter for this exception date-time.
     *
     * @return the value data type parameter, or {@code null} if not specified
     */
    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Returns the time zone identifier parameter for this exception date-time.
     *
     * @return the time zone identifier parameter, or {@code null} if not specified
     */
    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    /**
     * Returns the formatted string representation of this exception date-times property for
     * inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code EXDATE} property string
     */
    @Override
    public String formatted() {
        var composer = PropertyComposer.of("EXDATE")
                .append(valueDataType)
                .append(timeZoneIdentifier);
        return composer.end(zonedDateTime.format(getDateTimeFormatter()));
    }
}
