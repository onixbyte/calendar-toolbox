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
import java.util.List;
import java.util.Set;

public class RecurrenceDateTimes implements ComponentProperty, DateTimeProperty {

    private final ValueDataType valueDataType;

    private final TimeZoneIdentifier timeZoneIdentifier;

    private final ZonedDateTime value;

    private RecurrenceDateTimes(
            ValueDataType valueDataType,
            TimeZoneIdentifier timeZoneIdentifier,
            ZonedDateTime value
    ) {
        if (valueDataType != null) {
            var _set = Set.of(ValueDataType.DATE_TIME, ValueDataType.DATE, ValueDataType.PERIOD);
            if (!_set.contains(valueDataType)) {
                throw new IllegalArgumentException("Value data type must be DATE-TIME, DATE or PERIOD");
            }
        }

        this.valueDataType = valueDataType;
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.value = value;
    }

    public static RecurrenceDateTimesBuilder builder() {
        return new RecurrenceDateTimesBuilder();
    }

    public static class RecurrenceDateTimesBuilder {
        private ValueDataType valueDataType;
        private TimeZoneIdentifier timeZoneIdentifier;

        private RecurrenceDateTimesBuilder() {
        }

        public RecurrenceDateTimesBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        public RecurrenceDateTimesBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        public RecurrenceDateTimes build(ZonedDateTime value) {
            return new RecurrenceDateTimes(valueDataType, timeZoneIdentifier, value);
        }
    }

    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("RDATE");
        ParamAppender.append(builder, valueDataType);
        ParamAppender.append(builder, timeZoneIdentifier);
        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
