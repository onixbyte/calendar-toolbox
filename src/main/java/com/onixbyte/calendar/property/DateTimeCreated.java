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

import com.onixbyte.calendar.util.Formatters;

import java.time.ZonedDateTime;

public final class DateTimeCreated implements ComponentProperty {

    private final ZonedDateTime value;

    private DateTimeCreated(ZonedDateTime value) {
        this.value = value;
    }

    public static DateTimeCreatedBuilder builder() {
        return new DateTimeCreatedBuilder();
    }

    public static class DateTimeCreatedBuilder {
        private DateTimeCreatedBuilder() {
        }

        public DateTimeCreated build(ZonedDateTime value) {
            return new DateTimeCreated(value);
        }
    }

    @Override
    public String formatted() {
        return "CREATED:" + value.format(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
