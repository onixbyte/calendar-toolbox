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

package com.onixbyte.calendar.value;

import java.util.Objects;
import java.util.Optional;

public final class UtcOffset implements PropertyValue {

    private final char sign;

    private final int hour;

    private final int minute;

    private final Integer second;

    private UtcOffset(char sign, int hour, int minute, Integer second) {
        // The value of "-0000" and "-000000" are not allowed.
        if (sign == '-' && hour == 0 && minute == 0 && (Objects.isNull(second) || second == 0)) {
            throw new IllegalArgumentException("The value of \"-0000\" and \"-000000\" are not allowed.");
        }

        if (hour < 0 || hour > 12) {
            throw new IllegalArgumentException("Hour MUST between 0 and 12.");
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute MUST between 0 and 59.");
        }

        if (Objects.nonNull(second) && (second < 0 || second > 59)) {
            throw new IllegalArgumentException("Second MUST between 0 and 59.");
        }

        this.sign = sign;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static UtcOffset ofPositive(int hour, int minute, Integer second) {
        return new UtcOffset('+', hour, minute, second);
    }

    public static UtcOffset ofPositive(int hour, int minute) {
        return ofPositive(hour, minute, null);
    }

    public static UtcOffset ofNegative(int hour, int minute, Integer second) {
        return new UtcOffset('-', hour, minute, second);
    }

    public static UtcOffset ofNegative(int hour, int minute) {
        return ofNegative(hour, minute, null);
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append(sign)
                .append(hour)
                .append(minute);
        Optional.ofNullable(second).ifPresent(builder::append);
        return builder.toString();
    }
}
