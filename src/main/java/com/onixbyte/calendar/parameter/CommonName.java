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


import java.util.Objects;

/**
 * Represents the iCalendar {@code CN} parameter.
 * <p>
 * This parameter specifies a common name associated with a calendar user or resource, typically
 * used to provide a human-readable identifier.
 * <p>
 * Instances of this class are immutable and can be created via the static factory
 * method {@link #of(String)}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class CommonName implements Parameter {

    /**
     * The value of the common name.
     */
    private final String value;

    /**
     * Constructs a {@code CommonName} instance with the specified name value.
     *
     * @param value the common name value
     */
    private CommonName(String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("Common Name value cannot be null.");
        }
        this.value = value;
    }

    /**
     * Creates a {@code CommonName} instance from a given string value.
     *
     * @param value the string representing the common name
     * @return a new instance of {@code CommonName}
     */
    public static CommonName of(String value) {
        return new CommonName(value);
    }

    /**
     * Returns the formatted {@code CN} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code CN=value} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "CN=" + value;
    }
}
