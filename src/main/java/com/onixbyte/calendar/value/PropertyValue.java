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

/**
 * Base interface for all iCalendar property values.
 * <p>
 * This interface defines the common contract for value objects that represent
 * the data portion of iCalendar properties. Property values are the actual
 * content that appears after the colon in iCalendar property lines.
 * <p>
 * Examples of property values include:
 * <ul>
 *   <li>Date and time values (formatted according to iCalendar specifications)</li>
 *   <li>Period of time values (start/end time pairs)</li>
 *   <li>UTC offset values (time zone offset information)</li>
 *   <li>Free/busy time values (time periods with availability status)</li>
 * </ul>
 * <p>
 * Property values are typically more complex than simple strings and require
 * specific formatting rules defined by the iCalendar specification (RFC 5545).
 * <p>
 * All implementing classes must provide a {@link #formatted()} method that returns
 * the iCalendar-compliant string representation of the value.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PropertyValue {

    /**
     * Returns the formatted iCalendar representation of this property value.
     * <p>
     * The format must comply with the iCalendar specification (RFC 5545)
     * for the specific value type being represented.
     *
     * @return the formatted iCalendar value string
     */
    String formatted();
}
