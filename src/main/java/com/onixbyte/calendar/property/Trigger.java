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

public final class Trigger implements ComponentProperty {

    private final ValueDataType valueDataType;

    private final AlarmTriggerRelationship relationship;

    private final Duration durationValue;

    private final ZonedDateTime dateTimeValue;

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

    public static TriggerBuilder builder() {
        return new TriggerBuilder();
    }

    public static class TriggerBuilder {
        private AlarmTriggerRelationship relationship;

        private TriggerBuilder() {
        }

        public TriggerBuilder withRelationship(AlarmTriggerRelationship relationship) {
            this.relationship = relationship;
            return this;
        }

        public Trigger build(Duration value) {
            return new Trigger(ValueDataType.DURATION, relationship, value, null);
        }

        public Trigger build(ZonedDateTime value) {
            return new Trigger(ValueDataType.DATE_TIME, relationship, null, value);
        }
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("TRIGGER");
        ParamAppender.append(builder, valueDataType);
        ParamAppender.append(builder, relationship);

        builder.append(":");
        if (Objects.equals(ValueDataType.DURATION, valueDataType) && Objects.nonNull(durationValue)) {
            builder.append(durationValue);
        } else {
            builder.append(dateTimeValue.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER));
        }
        return builder.toString();
    }
}
