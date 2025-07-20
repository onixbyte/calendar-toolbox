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

package com.onixbyte.calendar.recurrence;

import com.onixbyte.calendar.util.DayOfWeekUtil;

import java.time.DayOfWeek;

/**
 * Represents a weekday number used in iCalendar recurrence rules.
 * <p>
 * A weekday number combines a day of the week with an optional ordinal number
 * to specify which occurrence of that day within a month or year. This is used
 * in recurrence rules to define patterns like "the first Monday of each month"
 * or "the last Friday of the year".
 * <p>
 * The weekday number can be:
 * <ul>
 *   <li>A simple day of the week (e.g., "MO" for Monday)</li>
 *   <li>A positive ordinal with day (e.g., "+1MO" for first Monday)</li>
 *   <li>A negative ordinal with day (e.g., "-1FR" for last Friday)</li>
 * </ul>
 * <p>
 * Examples of weekday numbers:
 * <ul>
 *   <li>"MO" - Every Monday</li>
 *   <li>"+1MO" - The first Monday of the period</li>
 *   <li>"-1FR" - The last Friday of the period</li>
 *   <li>"+2TU" - The second Tuesday of the period</li>
 *   <li>"-2WE" - The second-to-last Wednesday of the period</li>
 * </ul>
 * <p>
 * The ordinal number ranges from -53 to +53 (excluding 0), allowing for
 * week-based calculations across different time periods:
 * <ul>
 *   <li>Positive values count from the beginning of the period</li>
 *   <li>Negative values count from the end of the period</li>
 *   <li>Zero is not allowed as it has no meaningful interpretation</li>
 * </ul>
 * <p>
 * Instances of this class are immutable and can be created using the static
 * factory methods {@link #of(DayOfWeek)} and {@link #of(int, DayOfWeek)}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class WeekdayNum {

    /**
     * The ordinal number indicating which occurrence of the day within the period.
     * Positive values count from the beginning, negative values from the end.
     * May be null if no ordinal is specified.
     */
    private final Integer ordinal;

    /**
     * The day of the week.
     */
    private final DayOfWeek dayOfWeek;

    /**
     * Constructs a new WeekdayNum with the specified ordinal and day of week.
     *
     * @param ordinal   the ordinal number (-53 to +53, excluding 0), may be null
     * @param dayOfWeek the day of the week
     * @throws IllegalArgumentException if ordinal is 0 or outside the valid range
     */
    private WeekdayNum(Integer ordinal, DayOfWeek dayOfWeek) {
        // Validate ordinal range if specified (-53 to 53, excluding 0)
        if (ordinal != null && (ordinal == 0 || ordinal < -53 || ordinal > 53)) {
            throw new IllegalArgumentException("Weekday ordinal must be between -53 and 53 (excluding 0)");
        }
        this.ordinal = ordinal;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Creates a WeekdayNum for a specific day of week with no ordinal.
     * <p>
     * This represents every occurrence of the specified day within the
     * recurrence pattern (e.g., "every Monday").
     *
     * @param dayOfWeek the day of week
     * @return a new WeekdayNum instance
     */
    public static WeekdayNum of(DayOfWeek dayOfWeek) {
        return new WeekdayNum(null, dayOfWeek);
    }

    /**
     * Creates a WeekdayNum with an ordinal and day of week.
     * <p>
     * This represents a specific occurrence of the day within the period,
     * such as the first Monday or last Friday.
     *
     * @param ordinal   a positive or negative integer representing the nth occurrence
     * @param dayOfWeek the day of week
     * @return a new WeekdayNum instance
     * @throws IllegalArgumentException if ordinal is 0 or outside the valid range
     */
    public static WeekdayNum of(int ordinal, DayOfWeek dayOfWeek) {
        return new WeekdayNum(ordinal, dayOfWeek);
    }

    /**
     * Returns the formatted iCalendar representation of this weekday number.
     * <p>
     * The format follows these patterns:
     * <ul>
     *   <li>Day only: "MO", "TU", "WE", "TH", "FR", "SA", "SU"</li>
     *   <li>Positive ordinal: "+1MO", "+2TU", etc.</li>
     *   <li>Negative ordinal: "-1FR", "-2WE", etc.</li>
     * </ul>
     *
     * @return the formatted string suitable for use in iCalendar recurrence rules
     */
    public String formatted() {
        String dayCode = DayOfWeekUtil.getAbbreviation(dayOfWeek);
        if (ordinal == null) {
            return dayCode;
        }
        return ordinal > 0 ? "+" + ordinal + dayCode : ordinal + dayCode;
    }
}
