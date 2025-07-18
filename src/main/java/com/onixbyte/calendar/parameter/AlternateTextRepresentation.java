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

/**
 * Represents the iCalendar {@code ALTREP} parameter.
 * <p>
 * This parameter provides a URI that points to an alternate representation of the property's text,
 * such as a URL to an HTML version.
 * <p>
 * Instances of this class are immutable and can be created via the static factory methods
 * {@link #of(URI)} and {@link #of(String)}.
 *
 * @author siujamo
 */
public final class AlternateTextRepresentation implements Parameter {

    /**
     * The URI value representing the alternate text representation.
     */
    private final URI value;

    /**
     * Constructs an {@code AlternateTextRepresentation} with the specified URI.
     *
     * @param value the URI representing the alternate text representation
     */
    private AlternateTextRepresentation(URI value) {
        this.value = value;
    }

    /**
     * Creates an {@code AlternateTextRepresentation} instance from a URI.
     *
     * @param value the URI representing the alternate text representation
     * @return a new instance of {@code AlternateTextRepresentation}
     */
    public static AlternateTextRepresentation of(URI value) {
        return new AlternateTextRepresentation(value);
    }

    /**
     * Creates an {@code AlternateTextRepresentation} instance from a string representation of
     * a URI.
     *
     * @param value the string representing the URI of the alternate text representation
     * @return a new instance of {@code AlternateTextRepresentation}
     * @throws IllegalArgumentException if the given string violates URI syntax
     */
    public static AlternateTextRepresentation of(String value) {
        return new AlternateTextRepresentation(URI.create(value));
    }

    /**
     * Returns the formatted {@code ALTREP} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code ALTREP="URI"} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "ALTREP=\"" + value.toASCIIString() + '"';
    }
}
