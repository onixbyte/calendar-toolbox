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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface DateTimeProperty {

    static void checkValueDataType(ValueDataType valueDataType) {
        if (Objects.nonNull(valueDataType) && !List.of(ValueDataType.DATE, ValueDataType.DATE_TIME).contains(valueDataType)) {
            throw new IllegalArgumentException("Value Type accepts DATE and DATE-TIME in property `Date-Time End`.");
        }
    }

    ValueDataType getValueDataType();

    TimeZoneIdentifier getTimeZoneIdentifier();

    default DateTimeFormatter getDateTimeFormatter() {
        return Optional.ofNullable(getTimeZoneIdentifier())
                .map((timeZone) -> switch (getValueDataType()) {
                    case DATE_TIME -> Formatters.ICALENDAR_TIMESTAMP_FORMATTER
                            .withZone(timeZone.getZoneId());
                    case DATE -> Formatters.ICALENDAR_DATE_FORMATTER;
                    default -> throw new IllegalArgumentException();
                })
                .orElse(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
