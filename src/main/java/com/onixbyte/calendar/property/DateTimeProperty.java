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
import com.onixbyte.calendar.util.Formatters;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface for iCalendar properties that contain date and time values.
 * <p>
 * This interface provides common functionality for properties that represent date and time
 * information in iCalendar components. It handles the complexities of date/time formatting, time
 * zone handling, and value data type management.
 * <p>
 * Date/time properties can contain:
 * <ul>
 *   <li>DATE values - date-only without time information</li>
 *   <li>DATE-TIME values - date and time information</li>
 *   <li>TIME zone information for proper interpretation</li>
 * </ul>
 * <p>
 * Common date/time properties include:
 * <ul>
 *   <li>{@code DTSTART} - start date/time of a component</li>
 *   <li>{@code DTEND} - end date/time of a component</li>
 *   <li>{@code DTSTAMP} - timestamp when the component was created</li>
 *   <li>{@code CREATED} - creation date/time of the component</li>
 *   <li>{@code LAST-MODIFIED} - last modification date/time</li>
 * </ul>
 * <p>
 * This interface provides utility methods for formatting dates according to iCalendar
 * specifications and validating value data types.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DateTimeProperty {

    /**
     * Validates that the specified value data type is appropriate for date/time properties.
     * <p>
     * Date/time properties only accept DATE and DATE-TIME value data types. Other types will result
     * in an IllegalArgumentException.
     *
     * @param valueDataType the value data type to validate
     * @throws IllegalArgumentException if the value data type is not DATE or DATE-TIME
     */
    static void checkValueDataType(ValueDataType valueDataType) {
        if (Objects.nonNull(valueDataType) && !List.of(ValueDataType.DATE, ValueDataType.DATE_TIME).contains(valueDataType)) {
            throw new IllegalArgumentException("Value Type accepts DATE and DATE-TIME in property `Date-Time End`.");
        }
    }

    /**
     * Returns the value data type parameter for this date/time property.
     * <p>
     * This indicates whether the property contains a DATE or DATE-TIME value.
     *
     * @return the value data type parameter, or null if not specified
     */
    ValueDataType getValueDataType();

    /**
     * Returns the time zone identifier parameter for this date/time property.
     * <p>
     * This provides time zone context for interpreting the date/time value.
     *
     * @return the time zone identifier parameter, or null if not specified
     */
    TimeZoneIdentifier getTimeZoneIdentifier();

    /**
     * Returns the appropriate date/time formatter for this property.
     * <p>
     * The formatter is selected based on the value data type and time zone identifier parameters.
     * It handles various iCalendar date/time formats:
     * <ul>
     *   <li>UTC timestamp format for DATE-TIME without time zone</li>
     *   <li>Local timestamp format for DATE-TIME with time zone</li>
     *   <li>Date-only format for DATE values</li>
     * </ul>
     *
     * @return the appropriate DateTimeFormatter for formatting this property's value
     */
    default DateTimeFormatter getDateTimeFormatter() {
        return Optional.ofNullable(getTimeZoneIdentifier())
                .map((timeZone) -> switch (getValueDataType()) {
                    case DATE_TIME -> Formatters.ICALENDAR_TIMESTAMP_FORMATTER
                            .withZone(timeZone.getZoneId());
                    case DATE -> Formatters.ICALENDAR_DATE_FORMATTER;
                    default -> throw new IllegalArgumentException();
                })
                .orElse(Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER);
    }
}
