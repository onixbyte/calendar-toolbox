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
import com.onixbyte.calendar.util.Formatters;
import com.onixbyte.calendar.util.ParamAppender;
import com.onixbyte.calendar.value.PeriodOfTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 */
public class RecurrenceDateTimes implements ComponentProperty {

    /**
     * This parameter represents the type of the value. It can be set to {@link
     * ValueDataType#DATE_TIME DATE-TIME}, {@link ValueDataType#DATE DATE} and {@link
     * ValueDataType#PERIOD}.
     * <p>
     * When this is {@code null}, the {@link ValueDataType#DATE_TIME DATE-TIME} should be used.
     */
    private final ValueDataType valueDataType;

    /**
     * This parameter represents the time zone of the time. When this is {@code null}, the UTC
     * time zone should be used.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The date-time value.
     */
    private final LocalDateTime dateTimeValue;

    /**
     * The date value.
     */
    private final LocalDate dateValue;

    /**
     * The period value.
     */
    private final PeriodOfTime periodValue;

    /**
     * All allowed value types.
     */
    private final static Set<ValueDataType> ALLOWED_VALUE_TYPES = Set.of(
            ValueDataType.DATE_TIME,
            ValueDataType.DATE,
            ValueDataType.PERIOD
    );

    /**
     * Private constructor prevent being instantiated by non-standard operations.
     *
     * @param valueDataType      type of the value
     * @param timeZoneIdentifier time zone of date-time value
     * @param dateTimeValue      date time value
     * @param dateValue          date value
     * @param periodValue        period value
     */
    private RecurrenceDateTimes(
            ValueDataType valueDataType,
            TimeZoneIdentifier timeZoneIdentifier,
            LocalDateTime dateTimeValue,
            LocalDate dateValue,
            PeriodOfTime periodValue
    ) {
        this.valueDataType = valueDataType;
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.dateTimeValue = dateTimeValue;
        this.dateValue = dateValue;
        this.periodValue = periodValue;
    }

    /**
     * Get a builder for recurrence date-times.
     *
     * @return a builder instance
     */
    public static RecurrenceDateTimesBuilder builder() {
        return new RecurrenceDateTimesBuilder();
    }

    /**
     * Builder class for recurrence date-times.
     */
    public static class RecurrenceDateTimesBuilder {
        /**
         * The type of the value.
         *
         * @see RecurrenceDateTimes#valueDataType
         */
        private ValueDataType valueDataType;

        /**
         * The time zone of the time.
         *
         * @see RecurrenceDateTimes#timeZoneIdentifier
         */
        private TimeZoneIdentifier timeZoneIdentifier;

        /**
         * Private constructor prevents from being instantiated from non-standard operations.
         */
        private RecurrenceDateTimesBuilder() {
        }

        /**
         * Set the value type.
         *
         * @param valueDataType the value type, accepts {@code ValueDataType.DATE},
         *                      {@code ValueDataType.DATE_TIME} and {@code ValueDataType.PERIOD}
         * @return the builder instance
         */
        public RecurrenceDateTimesBuilder withValueDataType(ValueDataType valueDataType) {
            if (!ALLOWED_VALUE_TYPES.contains(valueDataType)) {
                throw new IllegalArgumentException("Only date-time, date and period type are allowed.");
            }
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Set the time zone.
         *
         * @param timeZoneIdentifier the time zone of the time
         * @return the builder instance
         */
        public RecurrenceDateTimesBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Create a recurrence date-times instance with a date-time value.
         *
         * @param value the date-time value
         * @return a recurrence date-time instance with date-time value
         */
        public RecurrenceDateTimes build(LocalDateTime value) {
            // `null` and `ValueDataType.DATE_TIME` are accepted for this value
            if (Objects.nonNull(valueDataType) && valueDataType != ValueDataType.DATE_TIME) {
                var dataTypeName = valueDataType.name().replaceAll("_", "-");
                throw new IllegalArgumentException("""
                        You have specified the value as a %s value, you can not initialise with a\
                         date-time value.
                        """.formatted(dataTypeName));
            }
            return new RecurrenceDateTimes(valueDataType, timeZoneIdentifier, value, null, null);
        }

        /**
         * Create a recurrence date-times instance with a date value.
         *
         * @param value the date value
         * @return a recurrence date-times instance with date value
         */
        public RecurrenceDateTimes build(LocalDate value) {
            // value type must be `ValueDataType.DATE`
            if (Objects.isNull(valueDataType) || valueDataType != ValueDataType.DATE) {
                var dataTypeName = valueDataType.name().replaceAll("_", "-");
                throw new IllegalArgumentException("""
                        You have specified the value as a %s value, you can not initialise with a\
                         date value.
                        """.formatted(dataTypeName));
            }
            return new RecurrenceDateTimes(valueDataType, timeZoneIdentifier, null, value, null);
        }

        /**
         * Create a recurrence date-times instance with a period value.
         *
         * @param value the period value
         * @return a recurrence date-times instance with period value
         */
        public RecurrenceDateTimes build(PeriodOfTime value) {
            // value type must be `ValueDataType.PERIOD`
            if (Objects.isNull(valueDataType) || valueDataType != ValueDataType.PERIOD) {
                var dataTypeName = valueDataType.name().replaceAll("_", "-");
                throw new IllegalArgumentException("""
                        You have specified the value as a %s value, you can not initialise with a\
                         period value.
                        """.formatted(dataTypeName));
            }
            return new RecurrenceDateTimes(valueDataType, timeZoneIdentifier, null, null, value);
        }
    }

    /**
     * Output the ics content of RFC5545 standard format.
     *
     * @return ics content
     */
    @Override
    public String formatted() {
        // confirm the type of value
        var _valueType = Optional.ofNullable(valueDataType)
                .orElse(ValueDataType.DATE_TIME);

        // create builder
        var builder = new StringBuilder();
        builder.append("RDATE");

        // append property parameters
        ParamAppender.append(builder, valueDataType);
        ParamAppender.append(builder, timeZoneIdentifier);

        // append colon
        builder.append(":");

        // append value
        switch (_valueType) {
            case DATE_TIME -> {
                var formatter = Objects.nonNull(timeZoneIdentifier) ?
                        Formatters.ICALENDAR_TIMESTAMP_FORMATTER.withZone(timeZoneIdentifier.getZoneId()) :
                        Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
                builder.append(dateTimeValue.format(formatter));
            }
            case DATE -> builder.append(dateValue.format(Formatters.ICALENDAR_DATE_FORMATTER));
            case PERIOD -> builder.append(periodValue.formatted());
            // this situation should never happen
            default -> throw new NullPointerException("No value is existed.");
        }

        return builder.toString();
    }
}
