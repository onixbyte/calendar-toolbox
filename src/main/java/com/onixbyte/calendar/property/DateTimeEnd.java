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
import com.onixbyte.calendar.util.AppendUtil;
import com.onixbyte.calendar.util.Formatters;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class DateTimeEnd implements ComponentProperty {

    private final TimeZoneIdentifier timeZoneIdentifier;

    private final ValueDataType valueDataType;

    private final ZonedDateTime value;

    private DateTimeEnd(
            TimeZoneIdentifier timeZoneIdentifier,
            ValueDataType valueDataType,
            ZonedDateTime value
    ) {
        if (Objects.nonNull(valueDataType) && List.of(ValueDataType.DATE, ValueDataType.DATE_TIME).contains(valueDataType)) {
            throw new IllegalArgumentException("Value Type accepts DATE and DATE-TIME in property `Date-Time End`.");
        }

        this.timeZoneIdentifier = timeZoneIdentifier;
        this.valueDataType = valueDataType;
        this.value = value;
    }

    public static DateTimeEnd of(
            TimeZoneIdentifier timeZoneIdentifier,
            ValueDataType valueDataType,
            ZonedDateTime value
    ) {
        return new DateTimeEnd(timeZoneIdentifier, valueDataType, value);
    }

    public static DateTimeEnd of(TimeZoneIdentifier timeZoneIdentifier, ZonedDateTime value) {
        return new DateTimeEnd(timeZoneIdentifier, null, value);
    }

    public static DateTimeEnd of(ValueDataType valueDataType, ZonedDateTime value) {
        return new DateTimeEnd(null, valueDataType, value);
    }

    public static DateTimeEnd of(ZonedDateTime value) {
        return new DateTimeEnd(null, null, value);
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("DTEND");

        AppendUtil.append(builder, valueDataType);
        AppendUtil.append(builder, timeZoneIdentifier);

        var _dateTimeFormatter = switch (valueDataType) {
            case DATE_TIME -> {
                if (Objects.nonNull(timeZoneIdentifier)) {
                    yield Formatters.ICALENDAR_TIMESTAMP_FORMATTER
                            .withZone(timeZoneIdentifier.getZoneId());
                } else {
                    yield Formatters.ICALENDAR_DATE_FORMATTER;
                }
            }
            case DATE -> Formatters.ICALENDAR_DATE_FORMATTER;
            default -> throw new IllegalArgumentException();
        };

        builder.append(":").append(value.format(_dateTimeFormatter));
        return builder.toString();
    }
}
