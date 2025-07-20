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
 * Base interface for all iCalendar component properties.
 * <p>
 * This interface defines the common contract for properties that can be associated with calendar
 * components such as events, to-do items, journal entries, and alarms. Component properties contain
 * descriptive information about the calendar component they belong to.
 * <p>
 * Examples of component properties include:
 * <ul>
 *   <li>{@code SUMMARY} - a brief description of the component</li>
 *   <li>{@code DESCRIPTION} - a detailed description of the component</li>
 *   <li>{@code DTSTART} - the start date/time of the component</li>
 *   <li>{@code DTEND} - the end date/time of the component</li>
 *   <li>{@code LOCATION} - the location where the component takes place</li>
 *   <li>{@code PRIORITY} - the priority level of the component</li>
 * </ul>
 * <p>
 * All implementing classes must provide a {@link #formatted()} method that returns the iCalendar
 * string representation of the property.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ComponentProperty {

    /**
     * Returns the formatted iCalendar representation of this component property.
     * <p>
     * The format typically follows the pattern: {@code PROPERTY[;parameters]:value} where
     * parameters are optional and depend on the specific property type.
     *
     * @return the formatted iCalendar property string
     */
    String formatted();
}
