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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Represents the iCalendar {@code SENT-BY} parameter, which is used to identify the calendar user
 * that is acting on behalf of the calendar user specified in the property. This parameter is
 * typically used in group scheduling scenarios where one user is acting as a proxy for another.
 * <p>
 * Instances of this class are immutable and can be created via the static factory methods
 * {@link #of(URI)} and {@link #of(String)}. The URI value is enclosed in double quotes when
 * formatted, as required by the iCalendar specification.
 *
 * @author siujamo
 */
public final class SentBy implements Parameter {

    /**
     * The URI identifying the calendar user acting on behalf of another.
     */
    private final URI value;

    /**
     * Constructs a {@code SentBy} with the specified URI.
     *
     * @param value the URI identifying the calendar user acting on behalf of another
     */
    private SentBy(URI value) {
        this.value = value;
    }

    /**
     * Creates a {@code SentBy} instance from a URI.
     *
     * @param value the URI identifying the calendar user acting on behalf of another
     * @return a new instance of {@code SentBy}
     */
    public static SentBy of(URI value) {
        return new SentBy(value);
    }

    /**
     * Creates a {@code SentBy} instance from a string representation of a URI.
     *
     * @param value the string representing the URI of the calendar user acting on behalf of another
     * @return a new instance of {@code SentBy}
     * @throws IllegalArgumentException if the given string violates URI syntax
     */
    public static SentBy of(String value) {
        try {
            var _value = new URI(value);
            return new SentBy(_value);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI: " + value, e);
        }
    }

    /**
     * Returns the formatted {@code SENT-BY} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code SENT-BY="URI"} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "SENT-BY=\"" + value.toASCIIString() + '"';
    }
}
