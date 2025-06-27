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
import com.onixbyte.calendar.util.PropertyAppender;

import java.time.ZonedDateTime;

public class RecurrenceId implements ComponentProperty, DateTimeProperty {

    private final ValueDataType valueDataType;

    private final TimeZoneIdentifier timeZoneIdentifier;

    private final RecurrenceIdentifierRange recurrenceIdentifierRange;

    private final ZonedDateTime value;

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

    public static RecurrenceIdBuilder builder() {
        return new RecurrenceIdBuilder();
    }

    public static class RecurrenceIdBuilder {
        private ValueDataType valueDataType;
        private TimeZoneIdentifier timeZoneIdentifier;
        private RecurrenceIdentifierRange recurrenceIdentifierRange;

        private RecurrenceIdBuilder() {
        }

        public RecurrenceIdBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        public RecurrenceIdBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        public RecurrenceIdBuilder withRecurrenceIdentifierRange() {
            this.recurrenceIdentifierRange = RecurrenceIdentifierRange.of();
            return this;
        }

        public RecurrenceId build(ZonedDateTime value) {
            return new RecurrenceId(valueDataType, timeZoneIdentifier, recurrenceIdentifierRange, value);
        }
    }

    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("RECURRENCE-ID");
        ParamAppender.append(builder, valueDataType);
        ParamAppender.append(builder, timeZoneIdentifier);
        ParamAppender.append(builder, recurrenceIdentifierRange);
        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
