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
 * Enumerates the possible relationships for the {@code RELATED} parameter in an iCalendar
 * alarm trigger.
 * <p>
 * This parameter defines whether an alarm is related to the start or end of the associated
 * calendar component.
 * <p>
 * Instances of this enum are formatted as specified in RFC 5545 for use in iCalendar properties.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public enum AlarmTriggerRelationship implements Parameter {

    /**
     * Indicates that the alarm is related to the start of the calendar component.
     */
    START,

    /**
     * Indicates that the alarm is related to the end of the calendar component.
     */
    END,
    ;

    /**
     * Returns the formatted parameter string for inclusion in an iCalendar alarm trigger.
     *
     * @return a string formatted as {@code RELATED=START} or {@code RELATED=END}
     */
    @Override
    public String formatted() {
        return "RELATED=" + name();
    }
}
