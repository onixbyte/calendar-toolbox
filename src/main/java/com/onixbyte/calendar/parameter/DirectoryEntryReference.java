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
 * Represents the iCalendar {@code DIR} parameter.
 * <p>
 * This parameter specifies a reference to the directory entry associated with the calendar user
 * specified by the property.
 * <p>
 * Instances of this class are immutable and can be created from one or more URIs representing
 * the delegatees.
 *
 * @author siujamo
 */
public final class DirectoryEntryReference implements Parameter {

    /**
     * The value of the directory reference.
     */
    private final URI value;

    /**
     * Constructs a {@code DirectoryEntryReference} instance with the specified directory
     * reference value.
     *
     * @param value the common name value
     */
    private DirectoryEntryReference(URI value) {
        this.value = value;
    }

    /**
     * Creates an {@code DirectoryEntryReference} instance from a string representation of a URI.
     *
     * @param value the string representing the URI of the alternate text representation
     * @return a new instance of {@code DirectoryEntryReference}
     * @throws IllegalArgumentException if the given string violates URI syntax
     */
    public static DirectoryEntryReference of(String value) {
        return new DirectoryEntryReference(URI.create(value));
    }

    /**
     * Creates an {@code DirectoryEntryReference} instance from a URI.
     *
     * @param value the URI representing the directory reference
     * @return a new instance of {@code DirectoryEntryReference}
     */
    public static DirectoryEntryReference of(URI value) {
        return new DirectoryEntryReference(value);
    }

    /**
     * Returns the formatted {@code DIR} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code DIR=value} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "DIR=\"" + value.toString() + '"';
    }
}
