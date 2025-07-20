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

/**
 * Enumeration representing the iCalendar CLASS property values.
 * <p>
 * The CLASS property defines the access classification for a calendar component. This property is
 * used to specify the intended access level of the calendar information, which can be used by
 * calendar applications to determine how to handle and display the calendar data.
 * <p>
 * The three standard classification levels are:
 * <ul>
 * <li>{@code PUBLIC} - The calendar component is accessible to anyone</li>
 * <li>{@code PRIVATE} - The calendar component is restricted to the owner</li>
 * <li>{@code CONFIDENTIAL} - The calendar component is restricted to authorised users</li>
 * </ul>
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Classification implements ComponentProperty {

    /**
     * Public classification level. Indicates that the calendar component is accessible to anyone
     * and can be freely shared or displayed.
     */
    PUBLIC,

    /**
     * Private classification level. Indicates that the calendar component is restricted to the
     * owner and should not be shared with others.
     */
    PRIVATE,

    /**
     * Confidential classification level. Indicates that the calendar component is restricted to
     * authorised users only and should be handled with special care.
     */
    CONFIDENTIAL,
    ;

    /**
     * Returns the formatted string representation of this classification property.
     * <p>
     * The format follows the iCalendar specification: "{@code CLASS:}" followed by the
     * classification name in uppercase.
     *
     * @return the formatted classification string (e.g., "CLASS:PRIVATE")
     */
    @Override
    public String formatted() {
        return "CLASS:" + name();
    }
}
