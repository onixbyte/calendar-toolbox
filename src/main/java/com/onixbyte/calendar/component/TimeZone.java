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

package com.onixbyte.calendar.component;

import com.onixbyte.calendar.property.*;
import com.onixbyte.calendar.util.PropertyAppender;

import java.util.List;

public final class TimeZone implements CalendarComponent {

    private final static String COMPONENT_NAME = "VTIMEZONE";

    // `TZID` is required, but must not occur more than once
    private final TimeZoneIdentifier timeZoneIdentifier;

    // `last-mod` and `tzurl` are optional, but must not occur more than once
    private final LastModified lastModified;

    private final TimeZoneUrl timeZoneUrl;

    // one of `standardc` or `daylightc` must occur and each may occur more than once
    private final List<TimeZoneProperty> timeZoneProperties;

    private TimeZone(
            TimeZoneIdentifier timeZoneIdentifier,
            LastModified lastModified,
            TimeZoneUrl timeZoneUrl,
            List<TimeZoneProperty> timeZoneProperties
    ) {
        this.timeZoneIdentifier = timeZoneIdentifier;
        this.lastModified = lastModified;
        this.timeZoneUrl = timeZoneUrl;
        this.timeZoneProperties = timeZoneProperties;
    }

    public static TimeZoneBuilder builder() {
        return new TimeZoneBuilder();
    }

    public static class TimeZoneBuilder {
        private TimeZoneBuilder() {
        }

        private TimeZoneIdentifier timeZoneIdentifier;
        private LastModified lastModified;
        private TimeZoneUrl timeZoneUrl;
        private List<TimeZoneProperty> timeZoneProperties;

        public TimeZoneBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        public TimeZoneBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public TimeZoneBuilder withTimeZoneUrl(TimeZoneUrl timeZoneUrl) {
            this.timeZoneUrl = timeZoneUrl;
            return this;
        }

        public TimeZoneBuilder withTimeZoneProperties(TimeZoneProperty... timeZoneProperties) {
            this.timeZoneProperties = List.of(timeZoneProperties);
            return this;
        }

        public TimeZone build() {
            return new TimeZone(timeZoneIdentifier, lastModified, timeZoneUrl, timeZoneProperties);
        }
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);
        
        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(timeZoneIdentifier);
        propertyAppender.append(lastModified);
        propertyAppender.append(timeZoneUrl);
        timeZoneProperties.forEach(propertyAppender::append);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);
        return builder.toString();
    }

    public static class TimeZoneProperty implements ComponentProperty {
        private final String componentName;

        // the following are required, but must not occur more than once
        private final DateTimeStart dateTimeStart;
        private final TimeZoneOffsetTo timeZoneOffsetTo;
        private final TimeZoneOffsetFrom timeZoneOffsetFrom;

        // the following is optional, but should not occur more than once
        private final RecurrenceRule recurrenceRule;

        // the following are optional, and may occur more than once
        private final List<Comment> comments;
        private final List<RecurrenceDateTimes> recurrenceDateTimes;
        private final List<TimeZoneName> timeZoneNames;

        private TimeZoneProperty(
                String componentName,
                DateTimeStart dateTimeStart,
                TimeZoneOffsetTo timeZoneOffsetTo,
                TimeZoneOffsetFrom timeZoneOffsetFrom,
                RecurrenceRule recurrenceRule,
                List<Comment> comments,
                List<RecurrenceDateTimes> recurrenceDateTimes,
                List<TimeZoneName> timeZoneNames
        ) {
            this.componentName = componentName;
            this.dateTimeStart = dateTimeStart;
            this.timeZoneOffsetTo = timeZoneOffsetTo;
            this.timeZoneOffsetFrom = timeZoneOffsetFrom;
            this.recurrenceRule = recurrenceRule;
            this.comments = comments;
            this.recurrenceDateTimes = recurrenceDateTimes;
            this.timeZoneNames = timeZoneNames;
        }

        public static TimeZonePropertyBuilder builder() {
            return new TimeZonePropertyBuilder();
        }

        public static class TimeZonePropertyBuilder {
            // the following are required, but must not occur more than once
            private DateTimeStart dateTimeStart;
            private TimeZoneOffsetTo timeZoneOffsetTo;
            private TimeZoneOffsetFrom timeZoneOffsetFrom;

            // the following is optional, but should not occur more than once
            private RecurrenceRule recurrenceRule;

            // the following are optional, and may occur more than once
            private List<Comment> comments;
            private List<RecurrenceDateTimes> recurrenceDateTimes;
            private List<TimeZoneName> timeZoneNames;

            private TimeZonePropertyBuilder() {
            }

            public TimeZoneProperty buildAsStandard() {
                return new TimeZoneProperty("STANDARD", dateTimeStart,
                        timeZoneOffsetTo, timeZoneOffsetFrom, recurrenceRule, comments,
                        recurrenceDateTimes, timeZoneNames);
            }

            public TimeZoneProperty buildAsDaylight() {
                return new TimeZoneProperty("DAYLIGHT", dateTimeStart,
                        timeZoneOffsetTo, timeZoneOffsetFrom, recurrenceRule, comments,
                        recurrenceDateTimes, timeZoneNames);
            }
        }

        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append("BEGIN").append(":").append(componentName);

            propertyAppender.append(dateTimeStart);
            propertyAppender.append(timeZoneOffsetTo);
            propertyAppender.append(timeZoneOffsetFrom);
            propertyAppender.append(recurrenceRule);

            comments.forEach(propertyAppender::append);
            recurrenceDateTimes.forEach(propertyAppender::append);
            timeZoneNames.forEach(propertyAppender::append);

            builder.append("\n").append("END").append(":").append(componentName);
            return builder.toString();
        }
    }
}
