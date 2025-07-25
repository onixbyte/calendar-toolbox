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

import com.onixbyte.calendar.util.PropertyComposer;

/**
 * This property specifies the description of a calendar.
 *
 * @author siujamo
 */
public final class CalendarDescription implements CalendarProperty {

    private final String value;

    private CalendarDescription(String value) {
        this.value = value;
    }

    /**
     * Create a new builder instance of <code>CalendarDescription</code>.
     *
     * @return a builder instance
     */
    public static CalendarDescriptionBuilder builder() {
        return new CalendarDescriptionBuilder();
    }

    /**
     * Builder for <code>CalendarDescription</code>
     */
    public static class CalendarDescriptionBuilder {
        private CalendarDescriptionBuilder() {
        }

        /**
         * Build a calendar description with given value.
         *
         * @param value description content
         * @return a <code>CalendarDescription</code> with given value
         */
        public CalendarDescription build(String value) {
            return new CalendarDescription(value);
        }
    }

    /**
     * Output this property in <code>ics</code> format.
     *
     * @return <code>ics</code>-formatted string
     */
    @Override
    public String formatted() {
        var propertyComposer = PropertyComposer.of("X-WR-CALDESC");
        return propertyComposer.end(value);
    }
}
