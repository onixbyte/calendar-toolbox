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

package com.onixbyte.calendar.parameter;

/**
 * Enumerates the different types of free/busy time statuses as defined in scheduling
 * specifications. This enum implements the {@code Parameter} interface, allowing its values to be
 * formatted into standard string representations for inclusion in calendar data structures.
 * <p>
 * Each status indicates the availability of a calendar entity. For example, {@code FREE} denotes an
 * available time slot, while {@code BUSY} indicates an occupied time slot.
 * <p>
 * The formatted string replaces underscores with hyphens to comply with iCalendar syntax
 * requirements (e.g., {@code BUSY-TENTATIVE}).
 *
 * @author siujamo
 */
public enum FreeBusyTimeType implements Parameter {

    /**
     * Indicates a free time slot where the calendar entity is available.
     */
    FREE,

    /**
     * Indicates a busy time slot where the calendar entity is occupied.
     */
    BUSY,

    /**
     * Indicates an unavailable time slot where the calendar entity is busy and cannot be disturbed.
     */
    BUSY_UNAVAILABLE,

    /**
     * Indicates a tentatively busy time slot where the calendar entity's availability is not
     * yet confirmed.
     */
    BUSY_TENTATIVE,
    ;

    /**
     * Returns the formatted string representation of this free/busy time type, converting
     * underscores to hyphens to meet iCalendar syntax standards. The format
     * is {@code FBTYPE=status-hyphenated}.
     *
     * @return a string formatted as {@code FBTYPE=status-hyphenated}
     */
    @Override
    public String formatted() {
        return "FBTYPE=" + name().replaceAll("_", "-");
    }
}
