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

package com.onixbyte.calendar.component.property;

/**
 * Enumeration representing the iCalendar {@code TRANSP} property values.
 * <p>
 * The {@code TRANSP} property defines whether a calendar component is transparent or opaque with
 * respect to busy time searches. This property is used to indicate whether the time consumed by the
 * component should be considered when performing free/busy time calculations.
 * <p>
 * Time transparency affects how calendar applications handle scheduling:
 * <ul>
 *   <li>OPAQUE events block out time and show as busy</li>
 *   <li>TRANSPARENT events do not block out time and show as free</li>
 * </ul>
 * <p>
 * Common use cases:
 * <ul>
 *   <li>OPAQUE - Regular meetings, appointments, important events</li>
 *   <li>TRANSPARENT - Reminders, birthdays, holidays, tentative events</li>
 * </ul>
 * <p>
 * This property is particularly important for:
 * <ul>
 *   <li>Free/busy time calculations</li>
 *   <li>Automatic scheduling systems</li>
 *   <li>Calendar conflict detection</li>
 *   <li>Resource booking systems</li>
 * </ul>
 * <p>
 * If not specified, the default value is OPAQUE for most calendar components.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum TimeTransparency implements ComponentProperty {

    /**
     * Indicates that the time covered by the component should be considered busy.
     * <p>
     * Events with OPAQUE transparency will block out time in free/busy calculations and appear as
     * busy time slots. This is the typical setting for meetings, appointments, and other events
     * that require the person's full attention.
     */
    OPAQUE,

    /**
     * Indicates that the time covered by the component should be considered free.
     * <p>
     * Events with TRANSPARENT transparency will not block out time in free/busy calculations and
     * allow other events to be scheduled at the same time. This is useful for reminders, birthdays,
     * holidays, or tentative events.
     */
    TRANSPARENT,
    ;

    /**
     * Returns the formatted iCalendar representation of this time transparency property.
     * <p>
     * The format follows the iCalendar specification: {@code TRANSP:value}
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "TRANSP:" + name();
    }
}
