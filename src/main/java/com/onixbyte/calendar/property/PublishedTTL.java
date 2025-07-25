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

import java.time.Duration;

/**
 * This property specifies a suggested iCalendar file download frequency for clients and servers
 * with sync capabilities.
 *
 * @author siujamo
 */
public final class PublishedTTL implements CalendarProperty {

    private final Duration value;

    private PublishedTTL(Duration value) {
        this.value = value;
    }

    /**
     * Get a new builder instance for building a <code>PublishedTTL</code>.
     *
     * @return a new builder instance
     */
    public static PublishedTTLBuilder builder() {
        return new PublishedTTLBuilder();
    }

    /**
     * Builder for building <code>PublishedTTL</code>.
     */
    public static class PublishedTTLBuilder {
        private PublishedTTLBuilder() {
        }

        /**
         * Build <code>PublishedTTL</code> with specific value.
         *
         * @param value the duration value
         * @return the <code>PublishedTTL</code> instance built with given duration value
         */
        public PublishedTTL build(Duration value) {
            return new PublishedTTL(value);
        }
    }

    /**
     * Output this property in <code>ics</code> format.
     *
     * @return <code>ics</code>-formatted string
     */
    @Override
    public String formatted() {
        var propertyComposer = PropertyComposer.of("X-PUBLISHED-TTL");
        return propertyComposer.end(value);
    }
}
