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

import com.onixbyte.calendar.parameter.RecurrenceIdentifierRange;
import com.onixbyte.calendar.parameter.TimeZoneIdentifier;
import com.onixbyte.calendar.parameter.ValueDataType;
import com.onixbyte.calendar.util.ParamAppender;

import java.time.ZonedDateTime;

/**
 * Represents the {@code RECURRENCE-ID} property in an iCalendar component.
 * <p>
 * This property is used in conjunction with the {@code UID} and {@code SEQUENCE}
 * properties to identify a specific instance of a recurring calendar component.
 * The value type of this property must match the value type of the {@code DTSTART}
 * property contained within the recurring component.
 * <p>
 * This property supports optional parameters for time zone identification,
 * value data type specification, and recurrence identifier range.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RecurrenceId implements ComponentProperty, DateTimeProperty {

    /**
     * The optional value data type parameter for this recurrence identifier.
     */
    private final ValueDataType valueDataType;

    /**
     * The optional time zone identifier parameter for this recurrence identifier.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The optional recurrence identifier range parameter.
     */
    private final RecurrenceIdentifierRange recurrenceIdentifierRange;

    /**
     * The recurrence identifier date-time value.
     */
    /**
     * The recurrence identifier date-time value.
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new {@code RecurrenceId} instance with the specified parameters.
     *
     * @param valueDataType                the optional value data type parameter
     * @param timeZoneIdentifier          the optional time zone identifier parameter
     * @param recurrenceIdentifierRange   the optional recurrence identifier range parameter
     * @param value                       the recurrence identifier date-time value
     */
    private RecurrenceId(
            ValueDataType valueDataType,
            TimeZoneIdentifier timeZoneIdentifier,
            RecurrenceIdentifierRange recurrenceIdentifierRange,
            ZonedDateTime value
    ) {
        DateTimeProperty.checkValueDataType(valueDataType);

        this.valueDataType = valueDataType;
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.recurrenceIdentifierRange = recurrenceIdentifierRange;
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code RecurrenceId} instances.
     *
     * @return a new {@code RecurrenceIdBuilder}
     */
    public static RecurrenceIdBuilder builder() {
        return new RecurrenceIdBuilder();
    }

    /**
     * Builder class for creating {@code RecurrenceId} instances with optional parameters.
     */
    public static class RecurrenceIdBuilder {
        /**
         * The optional value data type parameter.
         */
        private ValueDataType valueDataType;
        
        /**
         * The optional time zone identifier parameter.
         */
        private TimeZoneIdentifier timeZoneIdentifier;
        
        /**
         * The optional recurrence identifier range parameter.
         */
        private RecurrenceIdentifierRange recurrenceIdentifierRange;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RecurrenceIdBuilder() {
        }

        /**
         * Sets the value data type parameter for this recurrence identifier.
         *
         * @param valueDataType the value data type parameter
         * @return this builder instance for method chaining
         */
        public RecurrenceIdBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Sets the time zone identifier parameter for this recurrence identifier.
         *
         * @param timeZoneIdentifier the time zone identifier parameter
         * @return this builder instance for method chaining
         */
        public RecurrenceIdBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Sets the recurrence identifier range parameter for this recurrence identifier.
         *
         * @return this builder instance for method chaining
         */
        public RecurrenceIdBuilder withRecurrenceIdentifierRange() {
            this.recurrenceIdentifierRange = RecurrenceIdentifierRange.of();
            return this;
        }

        /**
         * Builds a new {@code RecurrenceId} instance with the specified recurrence identifier date-time value.
         *
         * @param value the recurrence identifier date-time value
         * @return a new {@code RecurrenceId} instance
         */
        public RecurrenceId build(ZonedDateTime value) {
            return new RecurrenceId(valueDataType, timeZoneIdentifier, recurrenceIdentifierRange, value);
        }
    }

    /**
     * Returns the value data type parameter for this recurrence identifier.
     *
     * @return the value data type parameter, or {@code null} if not specified
     */
    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Returns the time zone identifier parameter for this recurrence identifier.
     *
     * @return the time zone identifier parameter, or {@code null} if not specified
     */
    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    /**
     * Returns the formatted string representation of this recurrence identifier property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code RECURRENCE-ID} property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("RECURRENCE-ID");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(valueDataType);
        paramAppender.append(timeZoneIdentifier);
        paramAppender.append(recurrenceIdentifierRange);

        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
