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
 * Enum representing the iCalendar encoding types for inline content.
 * <p>
 * This parameter specifies the encoding mechanism used for inline content, such as 8-bit data
 * or Base64 encoded data.
 * <p>
 * The supported encoding types are as defined by the iCalendar specification.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public enum InlineEncoding implements Parameter {

    /**
     * Represents 8-bit encoding, allowing raw 8-bit data.
     */
    EIGHT_BIT("8BIT"),

    /**
     * Represents Base64 encoding, used for binary data encoded as ASCII text.
     */
    BASE_64("BASE64"),
    ;

    /**
     * The label used in the formatted parameter string.
     */
    private final String label;

    /**
     * Constructs an {@code InlineEncoding} enum constant with a specific label.
     *
     * @param label the string label for the encoding (e.g., "8BIT", "BASE64")
     */
    InlineEncoding(String label) {
        this.label = label;
    }

    /**
     * Returns the formatted ENCODING parameter string as specified in the iCalendar specification.
     *
     * @return a formatted string in the form {@code ENCODING=value} suitable for inclusion in an
     * iCalendar entity
     */
    @Override
    public String formatted() {
        return "ENCODING=" + label;
    }
}
