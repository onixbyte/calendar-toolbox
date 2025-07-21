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
 * Represents the iCalendar {@code DTSTART} property, which specifies the start date and time for a
 * calendar component.
 * <p>
 * The {@code DTSTART} property defines when a calendar component begins. For events, this
 * represents when the event starts. For to-do items, this can represent when the task should begin.
 * For journal entries, this represents when the entry was made.
 * <p>
 * This property supports various date and time formats:
 * <ul>
 *   <li>Date-time with time zone information</li>
 *   <li>Date-time in UTC (with 'Z' suffix)</li>
 *   <li>Date-time in floating time (no time zone)</li>
 *   <li>Date-only values (for all-day events)</li>
 * </ul>
 * <p>
 * The property can include optional parameters:
 * <ul>
 *   <li>TIME ZONE IDENTIFIER - specifies the time zone</li>
 *   <li>VALUE DATA TYPE - specifies whether the value is DATE or DATE-TIME</li>
 * </ul>
 * <p>
 * This property is typically required for events and to-do items, and is used in conjunction with
 * {@code DTEND} (for events) or DUE (for to-do items) to define the time span of the component.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DateTimeStart implements ComponentProperty, DateTimeProperty {

    /**
     * Optional parameter specifying the value data type (DATE or DATE-TIME). This determines how
     * the date/time value should be interpreted.
     */
    private final ValueDataType valueDataType;

    /**
     * Optional parameter specifying the time zone identifier. This provides time zone context for
     * the date/time value.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The start date and time value. This represents when the calendar component begins.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new DateTimeStart instance with the specified parameters.
     *
     * @param valueDataType      optional value data type parameter
     * @param timeZoneIdentifier optional time zone identifier parameter
     * @param value              the start date and time value
     */
    private DateTimeStart(
            ValueDataType valueDataType,
            TimeZoneIdentifier timeZoneIdentifier,
            ZonedDateTime value
    ) {
        DateTimeProperty.checkValueDataType(valueDataType);

        this.valueDataType = valueDataType;
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a DateTimeStart property.
     *
     * @return a new DateTimeStartBuilder instance
     */
    public static DateTimeStartBuilder builder() {
        return new DateTimeStartBuilder();
    }

    /**
     * Builder class for constructing DateTimeStart instances.
     * <p>
     * This builder allows for optional configuration of value data type and time zone identifier
     * parameters before creating the final DateTimeStart instance with a specific date/time value.
     */
    public static class DateTimeStartBuilder {
        /**
         * Optional value data type parameter.
         */
        private ValueDataType valueDataType;

        /**
         * Optional time zone identifier parameter.
         */
        private TimeZoneIdentifier timeZoneIdentifier;

        /**
         * Private constructor to enforce use of the factory method.
         */
        private DateTimeStartBuilder() {
        }

        /**
         * Sets the value data type parameter for the start date/time.
         *
         * @param valueDataType the value data type parameter
         * @return this builder instance for method chaining
         */
        public DateTimeStartBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Sets the time zone identifier parameter for the start date/time.
         *
         * @param timeZoneIdentifier the time zone identifier parameter
         * @return this builder instance for method chaining
         */
        public DateTimeStartBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Creates a new DateTimeStart instance with the specified date/time value.
         *
         * @param value the start date and time value
         * @return a new DateTimeStart instance
         */
        public DateTimeStart build(ZonedDateTime value) {
            return new DateTimeStart(valueDataType, timeZoneIdentifier, value);
        }
    }

    /**
     * Returns the value data type parameter for this date/time property.
     *
     * @return the value data type parameter, or null if not specified
     */
    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Returns the time zone identifier parameter for this date/time property.
     *
     * @return the time zone identifier parameter, or null if not specified
     */
    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    /**
     * Returns the formatted iCalendar representation of this start date/time property.
     * <p>
     * The format follows the iCalendar specification: {@code DTSTART[;parameters]:value} where
     * parameters may include time zone identifier and value data type if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("DTSTART");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(timeZoneIdentifier);
        paramAppender.append(valueDataType);
        builder.append(":").append(value.format(getDateTimeFormatter()));

        return builder.toString();
    }
}
