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

import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.ParamAppender;
import com.onixbyte.calendar.util.PropertyAppender;

public final class TimeZoneName implements ComponentProperty {

    private final Language language;

    private final String value;

    private TimeZoneName(Language language, String value) {
        this.language = language;
        this.value = value;
    }

    public static class TimeZoneNameBuilder {
        private Language language;

        private TimeZoneNameBuilder() {
        }

        public TimeZoneNameBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        public TimeZoneName build(String value) {
            return new TimeZoneName(language, value);
        }
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("TZNAME");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(language);

        builder.append(":").append(value);
        return builder.toString();
    }
}
