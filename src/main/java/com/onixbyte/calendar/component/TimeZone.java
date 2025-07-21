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

/**
 * Represents a {@code VTIMEZONE} component in an iCalendar object.
 * <p>
 * This component provides a grouping of component properties that define a time zone. It is used to
 * specify the rules for converting between local time and Coordinated Universal Time (UTC).
 * <p>
 * A time zone component includes a required time zone identifier and may include optional
 * properties such as last modification date and time zone URL. It must contain one or more time
 * zone property components that define the standard and daylight time rules.
 * <p>
 * This component follows RFC 5545 specifications for time zone components.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class TimeZone implements CalendarComponent {

    /**
     * The component name identifier for this time zone component.
     */
    private final static String COMPONENT_NAME = "VTIMEZONE";

    /**
     * The time zone identifier property.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * The optional last modified property.
     */
    private final LastModified lastModified;

    /**
     * The optional time zone URL property.
     */
    private final TimeZoneUrl timeZoneUrl;

    /**
     * The list of time zone properties (standard and daylight time rules).
     */
    private final List<TimeZoneProperty> timeZoneProperties;

    /**
     * Constructs a new TimeZone instance with the specified properties.
     * <p>
     * This constructor is private to enforce the use of the builder pattern for creating
     * {@code TimeZone} instances. Use {@link #builder()} to create new TimeZone objects.
     *
     * @param timeZoneIdentifier the time zone identifier property
     * @param lastModified       the last modified property
     * @param timeZoneUrl        the time zone URL property
     * @param timeZoneProperties the list of time zone properties defining standard and
     *                           daylight rules
     */
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

    /**
     * Creates a new builder instance for constructing TimeZone objects.
     * <p>
     * This is the preferred way to create TimeZone instances, as it provides a flexible and
     * readable approach to setting the various optional and required properties.
     *
     * @return a new TimeZoneBuilder instance
     */
    public static TimeZoneBuilder builder() {
        return new TimeZoneBuilder();
    }

    /**
     * Builder class for constructing TimeZone instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating TimeZone objects with the required
     * {@code timeZoneIdentifier} property and various optional properties. The builder enforces
     * RFC 5545 constraints for time zone components.
     * <p>
     * Example usage:
     * <pre>{@code
     * TimeZone timeZone = TimeZone.builder()
     *     .withTimeZoneIdentifier(timeZoneId)
     *     .withTimeZoneUrl(timeZoneUrl)
     *     .withTimeZoneProperties(standardTime, daylightTime)
     *     .build();
     * }</pre>
     */
    public static class TimeZoneBuilder {
        /**
         * Private constructor to enforce use through the factory method.
         */
        private TimeZoneBuilder() {
        }

        /**
         * The time zone identifier for the time zone being built.
         */
        private TimeZoneIdentifier timeZoneIdentifier;

        /**
         * The last modified property for the time zone being built.
         */
        private LastModified lastModified;

        /**
         * The time zone URL for the time zone being built.
         */
        private TimeZoneUrl timeZoneUrl;

        /**
         * The list of time zone properties for the time zone being built.
         */
        private List<TimeZoneProperty> timeZoneProperties;

        /**
         * Sets the time zone identifier for the time zone.
         *
         * @param timeZoneIdentifier the time zone identifier property
         * @return this builder instance for method chaining
         */
        public TimeZoneBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Sets the last modified property for the time zone.
         *
         * @param lastModified the last modified property
         * @return this builder instance for method chaining
         */
        public TimeZoneBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        /**
         * Sets the time zone URL for the time zone.
         *
         * @param timeZoneUrl the time zone URL property
         * @return this builder instance for method chaining
         */
        public TimeZoneBuilder withTimeZoneUrl(TimeZoneUrl timeZoneUrl) {
            this.timeZoneUrl = timeZoneUrl;
            return this;
        }

        /**
         * Sets the time zone properties for the time zone.
         * <p>
         * At least one time zone property (either standard or daylight) must be provided
         * to create a valid time zone component.
         *
         * @param timeZoneProperties the time zone properties defining standard and daylight rules
         * @return this builder instance for method chaining
         */
        public TimeZoneBuilder withTimeZoneProperties(TimeZoneProperty... timeZoneProperties) {
            this.timeZoneProperties = List.of(timeZoneProperties);
            return this;
        }

        /**
         * Builds and returns a new TimeZone instance.
         * <p>
         * Creates a TimeZone component with all the properties that have been set on this builder.
         * Note that timeZoneIdentifier is a required property and at least one time zone property
         * should be provided for a valid time zone component according to RFC 5545.
         *
         * @return a new TimeZone instance
         */
        public TimeZone build() {
            return new TimeZone(timeZoneIdentifier, lastModified, timeZoneUrl, timeZoneProperties);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this time zone component.
     * <p>
     * The format follows the iCalendar specification (RFC 5545) with {@code BEGIN:VTIMEZONE} and
     * {@code END:VTIMEZONE} delimiters containing all the time zone properties. The time zone
     * identifier is included first, followed by optional properties and time zone
     * property components.
     *
     * @return the formatted iCalendar {@code VTIMEZONE} component string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(timeZoneIdentifier);
        propertyAppender.append(lastModified);
        propertyAppender.append(timeZoneUrl);
        propertyAppender.append(timeZoneProperties);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);
        return builder.toString();
    }

    /**
     * Represents a time zone property component that defines standard or daylight time rules.
     * <p>
     * This nested class implements the standard and daylight time zone sub-components
     * that are contained within a VTIMEZONE component. Each TimeZoneProperty defines
     * the specific rules for either standard time or daylight saving time transitions,
     * including the effective date, time zone offsets, and recurrence rules.
     * <p>
     * TimeZoneProperty instances are created using the nested {@link TimeZonePropertyBuilder}
     * which provides methods to build either standard or daylight time components.
     */
    public static class TimeZoneProperty implements ComponentProperty {
        /**
         * The component name for this time zone property (either "STANDARD" or "DAYLIGHT").
         */
        private final String componentName;

        /**
         * The start date and time when this time zone rule becomes effective.
         */
        private final DateTimeStart dateTimeStart;

        /**
         * The time zone offset to which this rule applies.
         */
        private final TimeZoneOffsetTo timeZoneOffsetTo;

        /**
         * The time zone offset from which this rule applies.
         */
        private final TimeZoneOffsetFrom timeZoneOffsetFrom;

        /**
         * The recurrence rule defining when this time zone rule repeats (optional).
         */
        private final RecurrenceRule recurrenceRule;

        /**
         * The list of comments associated with this time zone property (optional).
         */
        private final List<Comment> comments;

        /**
         * The list of recurrence date and times for this time zone property (optional).
         */
        private final List<RecurrenceDateTimes> recurrenceDateTimes;

        /**
         * The list of time zone names for this time zone property (optional).
         */
        private final List<TimeZoneName> timeZoneNames;

        /**
         * Constructs a new TimeZoneProperty instance with the specified properties.
         * <p>
         * This constructor is private to enforce the use of the builder pattern for creating
         * {@code TimeZoneProperty} instances. Use {@link #builder()} to create new
         * {@code TimeZoneProperty} objects.
         *
         * @param componentName       the component name ("STANDARD" or "DAYLIGHT")
         * @param dateTimeStart       the start date and time when this rule becomes effective
         * @param timeZoneOffsetTo    the time zone offset to which this rule applies
         * @param timeZoneOffsetFrom  the time zone offset from which this rule applies
         * @param recurrenceRule      the recurrence rule defining when this rule repeats
         * @param comments            the list of comments associated with this time zone property
         * @param recurrenceDateTimes the list of recurrence date and times
         * @param timeZoneNames       the list of time zone names
         */
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

        /**
         * Creates a new builder instance for constructing TimeZoneProperty objects.
         * <p>
         * This is the preferred way to create TimeZoneProperty instances, as it provides a flexible
         * and readable approach to setting the various required and optional properties.
         *
         * @return a new TimeZonePropertyBuilder instance
         */
        public static TimeZonePropertyBuilder builder() {
            return new TimeZonePropertyBuilder();
        }

        /**
         * Builder class for constructing TimeZoneProperty instances using the builder pattern.
         * <p>
         * This builder provides a fluent interface for creating TimeZoneProperty objects with
         * required properties (dateTimeStart, timeZoneOffsetTo, timeZoneOffsetFrom) and various
         * optional properties. The builder provides separate build methods for creating standard
         * and daylight time zone properties.
         * <p>
         * Example usage:
         * <pre>{@code
         * TimeZoneProperty standard = TimeZoneProperty.builder()
         *     .withDateTimeStart(startTime)
         *     .withTimeZoneOffsetTo(offsetTo)
         *     .withTimeZoneOffsetFrom(offsetFrom)
         *     .buildAsStandard();
         * }</pre>
         */
        public static class TimeZonePropertyBuilder {
            /**
             * The start date and time for the time zone property being built.
             */
            private DateTimeStart dateTimeStart;

            /**
             * The time zone offset to for the time zone property being built.
             */
            private TimeZoneOffsetTo timeZoneOffsetTo;

            /**
             * The time zone offset from for the time zone property being built.
             */
            private TimeZoneOffsetFrom timeZoneOffsetFrom;

            /**
             * The recurrence rule for the time zone property being built.
             */
            private RecurrenceRule recurrenceRule;

            /**
             * The list of comments for the time zone property being built.
             */
            private List<Comment> comments;

            /**
             * The list of recurrence date and times for the time zone property being built.
             */
            private List<RecurrenceDateTimes> recurrenceDateTimes;

            /**
             * The list of time zone names for the time zone property being built.
             */
            private List<TimeZoneName> timeZoneNames;

            /**
             * Private constructor to enforce use through the factory method.
             */
            private TimeZonePropertyBuilder() {
            }

            /**
             * Sets the start date and time for the time zone property.
             *
             * @param dateTimeStart the start date and time when this rule becomes effective
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
                this.dateTimeStart = dateTimeStart;
                return this;
            }

            /**
             * Sets the time zone offset to for the time zone property.
             *
             * @param timeZoneOffsetTo the time zone offset to which this rule applies
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withTimeZoneOffsetTo(TimeZoneOffsetTo timeZoneOffsetTo) {
                this.timeZoneOffsetTo = timeZoneOffsetTo;
                return this;
            }

            /**
             * Sets the time zone offset from for the time zone property.
             *
             * @param timeZoneOffsetFrom the time zone offset from which this rule applies
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withTimeZoneOffsetFrom(TimeZoneOffsetFrom timeZoneOffsetFrom) {
                this.timeZoneOffsetFrom = timeZoneOffsetFrom;
                return this;
            }

            /**
             * Sets the recurrence rule for the time zone property.
             *
             * @param recurrenceRule the recurrence rule defining when this rule repeats
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
                this.recurrenceRule = recurrenceRule;
                return this;
            }

            /**
             * Sets the comments for the time zone property.
             *
             * @param comments the comments associated with this time zone property
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withComments(Comment... comments) {
                this.comments = List.of(comments);
                return this;
            }

            /**
             * Sets the recurrence date and times for the time zone property.
             *
             * @param recurrenceDateTimes the recurrence date and times for this time zone property
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withRecurrenceDateTimes(RecurrenceDateTimes... recurrenceDateTimes) {
                this.recurrenceDateTimes = List.of(recurrenceDateTimes);
                return this;
            }

            /**
             * Sets the time zone names for the time zone property.
             *
             * @param timeZoneNames the time zone names for this time zone property
             * @return this builder instance for method chaining
             */
            public TimeZonePropertyBuilder withTimeZoneNames(TimeZoneName... timeZoneNames) {
                this.timeZoneNames = List.of(timeZoneNames);
                return this;
            }

            /**
             * Builds and returns a new TimeZoneProperty instance as a standard time component.
             * <p>
             * Creates a {@code TimeZoneProperty} with component name "STANDARD" representing
             * standard time rules for the time zone.
             *
             * @return a new TimeZoneProperty instance for standard time
             */
            public TimeZoneProperty buildAsStandard() {
                return new TimeZoneProperty("STANDARD", dateTimeStart,
                        timeZoneOffsetTo, timeZoneOffsetFrom, recurrenceRule, comments,
                        recurrenceDateTimes, timeZoneNames);
            }

            /**
             * Builds and returns a new TimeZoneProperty instance as a daylight time component.
             * <p>
             * Creates a TimeZoneProperty with component name "{@code DAYLIGHT}" representing
             * daylight saving time rules for the time zone.
             *
             * @return a new TimeZoneProperty instance for daylight time
             */
            public TimeZoneProperty buildAsDaylight() {
                return new TimeZoneProperty("DAYLIGHT", dateTimeStart,
                        timeZoneOffsetTo, timeZoneOffsetFrom, recurrenceRule, comments,
                        recurrenceDateTimes, timeZoneNames);
            }
        }

        /**
         * Returns the formatted iCalendar representation of this time zone property component.
         * <p>
         * The format follows the iCalendar specification (RFC 5545) with BEGIN and END delimiters
         * containing the component name (either "STANDARD" or "DAYLIGHT") and all the time zone
         * property details. Required properties are included first, followed by
         * optional properties.
         *
         * @return the formatted iCalendar time zone property component string
         */
        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append("BEGIN").append(":").append(componentName);

            propertyAppender.append(dateTimeStart);
            propertyAppender.append(timeZoneOffsetTo);
            propertyAppender.append(timeZoneOffsetFrom);
            propertyAppender.append(recurrenceRule);

            propertyAppender.append(comments);
            propertyAppender.append(recurrenceDateTimes);
            propertyAppender.append(timeZoneNames);

            builder.append("\n").append("END").append(":").append(componentName);
            return builder.toString();
        }
    }
}
