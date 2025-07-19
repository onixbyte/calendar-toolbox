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
 * Defines the iCalendar {@code CUTYPE} parameter.
 * <p>
 * This parameter specifies the type of calendar user associated with a calendar component.
 * Valid types include individual users, groups, resources, rooms, and unknown types.
 * <p>
 * Each constant corresponds to a specific user type defined by the iCalendar specification.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public enum CalendarUserType implements Parameter {

    /**
     * An individual calendar user.
     */
    INDIVIDUAL,

    /**
     * A group of calendar users.
     */
    GROUP,

    /**
     * A resource associated with a calendar.
     */
    RESOURCE,

    /**
     * A room associated with a calendar.
     */
    ROOM,

    /**
     * An unknown type of calendar user.
     */
    UNKNOWN,
    ;

    /**
     * Returns the formatted {@code CUTYPE} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code CUTYPE=value} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "CUTYPE=" + name();
    }
}
