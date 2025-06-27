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
import com.onixbyte.calendar.util.Formatters;
import com.onixbyte.calendar.util.ParamAppender;
import com.onixbyte.calendar.value.FreeBusyTimeValue;

import java.util.List;
import java.util.Objects;

public final class FreeBusyTime {

    private final FreeBusyTimeType fbType;

    private final List<FreeBusyTimeValue> values;

    private FreeBusyTime(FreeBusyTimeType fbType, List<FreeBusyTimeValue> values) {
        if (Objects.isNull(values) || values.isEmpty()) {
            throw new IllegalArgumentException("Free/Busy Time values should not be empty.");
        }

        this.fbType = fbType;
        this.values = values;
    }

    public static FreeBusyTime of(FreeBusyTimeType fbType, FreeBusyTimeValue... values) {
        return new FreeBusyTime(fbType, List.of(values));
    }

    public String formatted() {
        var builder = new StringBuilder();
        builder.append("FREEBUSY");

        ParamAppender.append(builder, fbType);

        builder.append(":").append(Formatters.formatValue(",", values));
        return builder.toString();
    }
}
