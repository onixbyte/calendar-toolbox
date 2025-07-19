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
 * Represents the iCalendar {@code FMTTYPE} parameter, which specifies the format type of a
 * property value. This parameter is used to indicate the media type (MIME type) of the value
 * of a property, such as the format of a calendar attachment or the content type of a
 * descriptive property.
 * <p>
 * Instances of this class are immutable and can be created via the static factory method
 * {@link #of(String)}. The media type string should conform to the standard MIME type format
 * (e.g., "text/plain", "application/pdf").
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class FormatType implements Parameter {

    /**
     * The media type (MIME type) string.
     */
    private final String value;

    /**
     * Constructs a {@code FormatType} with the specified media type.
     *
     * @param value the media type string (MIME type)
     */
    private FormatType(String value) {
        this.value = value;
    }

    /**
     * Creates a {@code FormatType} instance from the given media type string.
     *
     * @param value the media type string (MIME type)
     * @return a new instance of {@code FormatType}
     * @throws IllegalArgumentException if the given media type is null or empty
     */
    public static FormatType of(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Media type cannot be null or empty");
        }
        return new FormatType(value);
    }

    /**
     * Returns the formatted {@code FMTTYPE} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code FMTTYPE=value} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "FMTTYPE=" + value;
    }
}
