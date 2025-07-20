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

/**
 * Represents the iCalendar DTEND property, which specifies the end date and time 
 * for a calendar component.
 * <p>
 * The DTEND property defines when a calendar component ends. For events, this
 * represents when the event concludes. The end time is exclusive, meaning the
 * event runs up to but not including the end time.
 * <p>
 * This property supports various date and time formats:
 * <ul>
 *   <li>Date-time with time zone information</li>
 *   <li>Date-time in UTC (with 'Z' suffix)</li>
 *   <li>Date-time in floating time (no time zone)</li>
 *   <li>Date-only values (for all-day events)</li>
 * </ul>
 * <p>
 * The property can include optional parameters:
 * <ul>
 *   <li>TIME ZONE IDENTIFIER - specifies the time zone</li>
 *   <li>VALUE DATA TYPE - specifies whether the value is DATE or DATE-TIME</li>
 * </ul>
 * <p>
 * Important rules for DTEND:
 * <ul>
 *   <li>Must be later than or equal to DTSTART</li>
 *   <li>Must use the same value data type as DTSTART</li>
 *   <li>For all-day events, DTEND should be the day after the event ends</li>
 *   <li>Cannot be used together with DURATION property</li>
 * </ul>
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DateTimeEnd implements ComponentProperty, DateTimeProperty {

    /**
     * Optional parameter specifying the time zone identifier.
     * This provides time zone context for the date/time value.
     */
    private final TimeZoneIdentifier timeZoneIdentifier;

    /**
     * Optional parameter specifying the value data type (DATE or DATE-TIME).
     * This determines how the date/time value should be interpreted.
     */
    private final ValueDataType valueDataType;

    /**
     * The end date and time value.
     * This represents when the calendar component ends (exclusive).
     */
    private final ZonedDateTime value;

    /**
     * Constructs a new DateTimeEnd instance with the specified parameters.
     *
     * @param timeZoneIdentifier optional time zone identifier parameter
     * @param valueDataType optional value data type parameter
     * @param value the end date and time value
     */
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

    /**
     * Creates a new builder instance for constructing a DateTimeEnd property.
     *
     * @return a new DateTimeEndBuilder instance
     */
    public static DateTimeEndBuilder builder() {
        return new DateTimeEndBuilder();
    }

    /**
     * Builder class for constructing DateTimeEnd instances.
     * <p>
     * This builder allows for optional configuration of time zone identifier and
     * value data type parameters before creating the final DateTimeEnd instance
     * with a specific date/time value.
     */
    public static class DateTimeEndBuilder {
        /**
         * Optional time zone identifier parameter.
         */
        private TimeZoneIdentifier timeZoneIdentifier;
        
        /**
         * Optional value data type parameter.
         */
        private ValueDataType valueDataType;

        /**
         * Private constructor to enforce use of the factory method.
         */
        private DateTimeEndBuilder() {
        }

        /**
         * Sets the time zone identifier parameter for the end date/time.
         *
         * @param timeZoneIdentifier the time zone identifier parameter
         * @return this builder instance for method chaining
         */
        public DateTimeEndBuilder withTimeZoneIdentifier(TimeZoneIdentifier timeZoneIdentifier) {
            this.timeZoneIdentifier = timeZoneIdentifier;
            return this;
        }

        /**
         * Sets the value data type parameter for the end date/time.
         *
         * @param valueDataType the value data type parameter
         * @return this builder instance for method chaining
         */
        public DateTimeEndBuilder withValueDataType(ValueDataType valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        /**
         * Creates a new DateTimeEnd instance with the specified date/time value.
         *
         * @param value the end date and time value
         * @return a new DateTimeEnd instance
         */
        public DateTimeEnd build(ZonedDateTime value) {
            return new DateTimeEnd(timeZoneIdentifier, valueDataType, value);
        }
    }

    /**
     * Returns the value data type parameter for this date/time property.
     *
     * @return the value data type parameter, or null if not specified
     */
    @Override
    public ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Returns the time zone identifier parameter for this date/time property.
     *
     * @return the time zone identifier parameter, or null if not specified
     */
    @Override
    public TimeZoneIdentifier getTimeZoneIdentifier() {
        return timeZoneIdentifier;
    }

    /**
     * Returns the formatted iCalendar representation of this end date/time property.
     * <p>
     * The format follows the iCalendar specification: DTEND[;parameters]:value
     * where parameters may include value data type and time zone identifier if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("DTEND");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(valueDataType);
        paramAppender.append(timeZoneIdentifier);

        builder.append(":").append(value.format(getDateTimeFormatter()));
        return builder.toString();
    }
}
