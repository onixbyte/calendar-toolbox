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

package com.onixbyte.calendar.util;

import java.time.DayOfWeek;

/**
 * Utility class for working with day-of-week values in iCalendar format.
 * <p>
 * This class provides helper methods for converting between Java's {@link DayOfWeek} enumeration
 * and the two-letter abbreviations used in iCalendar specifications.
 * <p>
 * The iCalendar specification uses specific two-letter codes for days of the week:
 * <ul>
 *   <li>MO - Monday</li>
 *   <li>TU - Tuesday</li>
 *   <li>WE - Wednesday</li>
 *   <li>TH - Thursday</li>
 *   <li>FR - Friday</li>
 *   <li>SA - Saturday</li>
 *   <li>SU - Sunday</li>
 * </ul>
 * <p>
 * These abbreviations are used in recurrence rules, weekday numbers, and other iCalendar properties
 * where days of the week need to be specified.
 * <p>
 * This is a utility class with only static methods and cannot be instantiated.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DayOfWeekUtil {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private DayOfWeekUtil() {
    }

    /**
     * Returns the iCalendar two-letter abbreviation for the specified day of week.
     * <p>
     * This method converts a Java {@link DayOfWeek} enumeration value to the corresponding
     * iCalendar abbreviation used in recurrence rules and other calendar properties.
     *
     * @param dayOfWeek the day of week to convert
     * @return the two-letter iCalendar abbreviation
     * @throws NullPointerException if dayOfWeek is null
     */
    public static String getAbbreviation(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "MO";
            case TUESDAY -> "TU";
            case WEDNESDAY -> "WE";
            case THURSDAY -> "TH";
            case FRIDAY -> "FR";
            case SATURDAY -> "SA";
            case SUNDAY -> "SU";
        };
    }
}
