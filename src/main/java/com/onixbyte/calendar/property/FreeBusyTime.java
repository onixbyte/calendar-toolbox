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

import com.onixbyte.calendar.parameter.FreeBusyTimeType;
import com.onixbyte.calendar.util.ParamAppender;
import com.onixbyte.calendar.util.Formatters;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class FreeBusyTime {

    private final FreeBusyTimeType fbType;

    private final ZonedDateTime start;

    private final ZonedDateTime end;

    private FreeBusyTime(FreeBusyTimeType fbType, ZonedDateTime start, ZonedDateTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException("Start and end time must not be null.");
        }

        if (end.isBefore(start) || end.equals(start)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }

        this.fbType = fbType;
        this.start = start;
        this.end = end;
    }

    public static FreeBusyTime of(FreeBusyTimeType fbType, ZonedDateTime start, ZonedDateTime end) {
        return new FreeBusyTime(fbType, start, end);
    }

    public static FreeBusyTime of(ZonedDateTime start, ZonedDateTime end) {
        return new FreeBusyTime(null, start, end);
    }

    public FreeBusyTimeType getFbType() {
        return fbType;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public String formatted() {
        var sb = new StringBuilder();
        sb.append("FREEBUSY");
        if (Objects.nonNull(fbType)) {
            ParamAppender.append(sb, fbType);
        }
        sb.append(':');
        var startStr = start.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
        var endStr = end.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
        sb.append(startStr)
                .append('/')
                .append(endStr);
        return sb.toString();
    }
}
