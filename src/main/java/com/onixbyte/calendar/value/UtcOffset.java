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

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a UTC offset value used in iCalendar time zone properties.
 * <p>
 * UTC offset values specify the offset from Coordinated Universal Time (UTC)
 * for a particular time zone. The offset is represented as a signed time
 * difference in hours, minutes, and optionally seconds.
 * <p>
 * UTC offsets are used in:
 * <ul>
 *   <li>TZOFFSETFROM - the offset from UTC before a time zone transition</li>
 *   <li>TZOFFSETTO - the offset from UTC after a time zone transition</li>
 * </ul>
 * <p>
 * The format follows the pattern: [+/-]HHMM[SS]
 * <ul>
 *   <li>Sign: + for positive offset (east of UTC), - for negative offset (west of UTC)</li>
 *   <li>Hours: 00-12 (time zones do not exceed ±12 hours from UTC)</li>
 *   <li>Minutes: 00-59</li>
 *   <li>Seconds: 00-59 (optional, for sub-minute precision)</li>
 * </ul>
 * <p>
 * Examples:
 * <ul>
 *   <li>+0500 - 5 hours ahead of UTC (e.g., Pakistan Standard Time)</li>
 *   <li>-0800 - 8 hours behind UTC (e.g., Pacific Standard Time)</li>
 *   <li>+0530 - 5 hours 30 minutes ahead of UTC (e.g., India Standard Time)</li>
 *   <li>-0330 - 3 hours 30 minutes behind UTC (e.g., Newfoundland Standard Time)</li>
 * </ul>
 * <p>
 * Special restrictions:
 * <ul>
 *   <li>The values "-0000" and "-000000" are not allowed (use "+0000" for UTC)</li>
 *   <li>Hours must be between 0 and 12 inclusive</li>
 *   <li>Minutes and seconds must be between 0 and 59 inclusive</li>
 * </ul>
 * <p>
 * Instances of this class are immutable and can be created using the static
 * factory methods {@link #ofPositive(int, int)} and {@link #ofNegative(int, int)}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class UtcOffset implements PropertyValue {

    /**
     * The sign of the offset: '+' for positive (east of UTC), '-' for negative (west of UTC).
     */
    private final char sign;

    /**
     * The hours component of the offset (0-12).
     */
    private final int hour;

    /**
     * The minutes component of the offset (0-59).
     */
    private final int minute;

    /**
     * The seconds component of the offset (0-59), optional.
     * May be null if seconds precision is not needed.
     */
    private final Integer second;

    /**
     * Constructs a new UtcOffset with the specified components.
     *
     * @param sign   the sign of the offset ('+' or '-')
     * @param hour   the hours component (0-12)
     * @param minute the minutes component (0-59)
     * @param second the seconds component (0-59), may be null
     * @throws IllegalArgumentException if any component is out of range or if "-0000" is specified
     */
    private UtcOffset(char sign, int hour, int minute, Integer second) {
        // The value of "-0000" and "-000000" are not allowed.
        if (sign == '-' && hour == 0 && minute == 0 && (Objects.isNull(second) || second == 0)) {
            throw new IllegalArgumentException("The value of \"-0000\" and \"-000000\" are not allowed.");
        }

        if (hour < 0 || hour > 12) {
            throw new IllegalArgumentException("Hour MUST between 0 and 12.");
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute MUST between 0 and 59.");
        }

        if (Objects.nonNull(second) && (second < 0 || second > 59)) {
            throw new IllegalArgumentException("Second MUST between 0 and 59.");
        }

        this.sign = sign;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Creates a positive UTC offset (east of UTC) with the specified components.
     *
     * @param hour   the hours component (0-12)
     * @param minute the minutes component (0-59)
     * @param second the seconds component (0-59), may be null
     * @return a new UtcOffset instance
     * @throws IllegalArgumentException if any component is out of range
     */
    public static UtcOffset ofPositive(int hour, int minute, Integer second) {
        return new UtcOffset('+', hour, minute, second);
    }

    /**
     * Creates a positive UTC offset (east of UTC) with the specified hours and minutes.
     *
     * @param hour   the hours component (0-12)
     * @param minute the minutes component (0-59)
     * @return a new UtcOffset instance
     * @throws IllegalArgumentException if any component is out of range
     */
    public static UtcOffset ofPositive(int hour, int minute) {
        return ofPositive(hour, minute, null);
    }

    /**
     * Creates a negative UTC offset (west of UTC) with the specified components.
     *
     * @param hour   the hours component (0-12)
     * @param minute the minutes component (0-59)
     * @param second the seconds component (0-59), may be null
     * @return a new UtcOffset instance
     * @throws IllegalArgumentException if any component is out of range or if "-0000" is specified
     */
    public static UtcOffset ofNegative(int hour, int minute, Integer second) {
        return new UtcOffset('-', hour, minute, second);
    }

    /**
     * Creates a negative UTC offset (west of UTC) with the specified hours and minutes.
     *
     * @param hour   the hours component (0-12)
     * @param minute the minutes component (0-59)
     * @return a new UtcOffset instance
     * @throws IllegalArgumentException if any component is out of range or if "-0000" is specified
     */
    public static UtcOffset ofNegative(int hour, int minute) {
        return ofNegative(hour, minute, null);
    }

    /**
     * Returns the formatted iCalendar representation of this UTC offset.
     * <p>
     * The format follows the pattern: [+/-]HHMM[SS]
     * where seconds are included only if specified.
     *
     * @return the formatted UTC offset string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append(sign)
                .append(String.format("%02d", hour))
                .append(String.format("%02d", minute));
        Optional.ofNullable(second)
                .ifPresent((_second) -> builder.append(String.format("%02d", _second)));
        return builder.toString();
    }
}
