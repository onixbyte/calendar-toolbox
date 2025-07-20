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

import com.onixbyte.calendar.parameter.AlarmTriggerRelationship;
import com.onixbyte.calendar.parameter.ValueDataType;
import com.onixbyte.calendar.util.Formatters;
import com.onixbyte.calendar.util.ParamAppender;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Represents the {@code TRIGGER} property in an iCalendar alarm component.
 * <p>
 * This property specifies when an alarm will trigger. It can specify either
 * a duration before or after the start or end of the event, or an absolute
 * date-time when the alarm should trigger.
 * <p>
 * The property supports optional parameters for value data type specification
 * and alarm trigger relationship to define relative triggering.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Trigger implements ComponentProperty {

    /**
     * The value data type parameter for this trigger (DURATION or DATE-TIME).
     */
    private final ValueDataType valueDataType;

    /**
     * The optional alarm trigger relationship parameter (START or END).
     */
    private final AlarmTriggerRelationship relationship;

    /**
     * The duration value for relative triggers.
     */
    private final Duration durationValue;

    /**
     * The absolute date-time value for absolute triggers.
     */
    private final ZonedDateTime dateTimeValue;

    /**
     * Constructs a new {@code Trigger} instance with the specified parameters.
     *
     * @param valueDataType  the value data type parameter (DURATION or DATE-TIME)
     * @param relationship   the optional alarm trigger relationship parameter
     * @param durationValue  the duration value for relative triggers
     * @param dateTimeValue  the absolute date-time value for absolute triggers
     */
    private Trigger(
            ValueDataType valueDataType,
            AlarmTriggerRelationship relationship,
            Duration durationValue,
            ZonedDateTime dateTimeValue
    ) {
        this.valueDataType = valueDataType;
        this.relationship = relationship;
        this.durationValue = durationValue;
        this.dateTimeValue = dateTimeValue;
    }

    /**
     * Creates a new builder for constructing {@code Trigger} instances.
     *
     * @return a new {@code TriggerBuilder}
     */
    public static TriggerBuilder builder() {
        return new TriggerBuilder();
    }

    /**
     * Builder class for creating {@code Trigger} instances with optional parameters.
     */
    public static class TriggerBuilder {
        /**
         * The optional alarm trigger relationship parameter.
         */
        private AlarmTriggerRelationship relationship;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private TriggerBuilder() {
        }

        /**
         * Sets the alarm trigger relationship parameter for this trigger.
         *
         * @param relationship the alarm trigger relationship parameter
         * @return this builder instance for method chaining
         */
        public TriggerBuilder withRelationship(AlarmTriggerRelationship relationship) {
            this.relationship = relationship;
            return this;
        }

        /**
         * Builds a new {@code Trigger} instance with a duration value for relative triggering.
         *
         * @param value the duration value relative to the event start or end
         * @return a new {@code Trigger} instance
         */
        public Trigger build(Duration value) {
            return new Trigger(ValueDataType.DURATION, relationship, value, null);
        }

        /**
         * Builds a new {@code Trigger} instance with an absolute date-time value.
         *
         * @param value the absolute date-time value when the alarm should trigger
         * @return a new {@code Trigger} instance
         */
        public Trigger build(ZonedDateTime value) {
            return new Trigger(ValueDataType.DATE_TIME, relationship, null, value);
        }
    }

    /**
     * Returns the formatted string representation of this trigger property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code TRIGGER} property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("TRIGGER");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(valueDataType);
        paramAppender.append(relationship);

        builder.append(":");
        if (Objects.equals(ValueDataType.DURATION, valueDataType) && Objects.nonNull(durationValue)) {
            builder.append(durationValue);
        } else {
            builder.append(dateTimeValue.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER));
        }
        return builder.toString();
    }
}
