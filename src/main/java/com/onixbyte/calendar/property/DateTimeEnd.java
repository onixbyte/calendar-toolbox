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

public class DateTimeEnd implements ComponentProperty, DateTimeProperty {

    private final TimeZoneIdentifier timeZoneIdentifier;

    private final ValueDataType valueDataType;

    private final ZonedDateTime value;

    private DateTimeEnd(
            TimeZoneIdentifier timeZoneIdentifier,
            ValueDataType valueDataType,
            ZonedDateTime value
    ) {
        DateTimeProperty.checkValueDataType(valueDataType);

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
        builder.append("DTEND");

        ParamAppender.append(builder, valueDataType);
        ParamAppender.append(builder, timeZoneIdentifier);

        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
