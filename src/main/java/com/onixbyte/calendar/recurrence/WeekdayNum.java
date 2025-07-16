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
 * Represents a weekday number combination in a recurrence rule.
 */
public final class WeekdayNum {

    private final Integer ordinal;
    private final DayOfWeek dayOfWeek;

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
     *
     * @param dayOfWeek the day of week
     * @return a new WeekdayNum instance
     */
    public static WeekdayNum of(DayOfWeek dayOfWeek) {
        return new WeekdayNum(null, dayOfWeek);
    }

    /**
     * Creates a WeekdayNum with an ordinal and day of week.
     *
     * @param ordinal a positive or negative integer representing the nth occurrence
     * @param dayOfWeek the day of week
     * @return a new WeekdayNum instance
     */
    public static WeekdayNum of(int ordinal, DayOfWeek dayOfWeek) {
        return new WeekdayNum(ordinal, dayOfWeek);
    }

    /**
     * Returns the formatted representation of this weekday number.
     *
     * @return the formatted string (e.g. "MO", "+1MO", "-1FR")
     */
    public String formatted() {
        String dayCode = DayOfWeekUtil.getAbbreviation(dayOfWeek);
        if (ordinal == null) {
            return dayCode;
        }
        return ordinal > 0 ? "+" + ordinal + dayCode : ordinal + dayCode;
    }
}
