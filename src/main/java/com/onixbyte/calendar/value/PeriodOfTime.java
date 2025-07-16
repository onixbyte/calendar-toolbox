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

import com.onixbyte.calendar.util.Formatters;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class PeriodOfTime implements PropertyValue {

    private final Explicit explicitValue;

    private final Start startValue;

    private PeriodOfTime(Explicit explicitValue, Start startValue) {
        this.explicitValue = explicitValue;
        this.startValue = startValue;
    }

    public static PeriodOfTime ofExplicit(LocalDateTime startTime, LocalDateTime endTime) {
        return new PeriodOfTime(new Explicit(startTime, endTime), null);
    }

    public static PeriodOfTime ofStart(LocalDateTime startTime, Duration duration) {
        return new PeriodOfTime(null, new Start(startTime, duration));
    }

    @Override
    public String formatted() {
        if (Objects.nonNull(explicitValue)) {
            return explicitValue.formatted();
        } else {
            return startValue.formatted();
        }
    }

    public static class Explicit {
        private final LocalDateTime startTime;

        private final LocalDateTime endTime;

        private Explicit(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String formatted() {
            var formatter = Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
            return startTime.format(formatter) + "/" + endTime.format(formatter);
        }
    }

    public static class Start {
        private final LocalDateTime startTime;
        private final Duration duration;

        private Start(LocalDateTime startTime, Duration duration) {
            this.startTime = startTime;
            this.duration = duration;
        }

        public String formatted() {
            var formatter = Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
            return startTime.format(formatter) + "/" + duration.toString();
        }
    }
}
