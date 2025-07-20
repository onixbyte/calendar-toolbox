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

/**
 * Represents the {@code PERCENT-COMPLETE} property in an iCalendar component.
 * <p>
 * This property is used to specify the percent completion of a calendar component.
 * It is commonly used with {@code VTODO} components to track the progress of a task.
 * <p>
 * The property value is an integer between 0 and 100, where 0 indicates no completion
 * and 100 indicates full completion.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class PercentComplete implements ComponentProperty {

    /**
     * The percent completion value (0-100).
     */
    /**
     * The percent completion value (0-100).
     */
    private final int value;

    /**
     * Constructs a new {@code PercentComplete} instance with the specified completion percentage.
     *
     * @param value the percent completion value (must be between 0 and 100 inclusive)
     * @throws IllegalArgumentException if the value is not between 0 and 100
     */
    private PercentComplete(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Property PERCENT-COMPLETE should between 0 and 100.");
        }

        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code PercentComplete} instances.
     *
     * @return a new {@code PercentCompleteBuilder}
     */
    public static PercentCompleteBuilder builder() {
        return new PercentCompleteBuilder();
    }

    /**
     * Builder class for creating {@code PercentComplete} instances.
     */
    public static class PercentCompleteBuilder {

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private PercentCompleteBuilder() {
        }

        /**
         * Builds a new {@code PercentComplete} instance with the specified completion percentage.
         *
         * @param value the percent completion value (must be between 0 and 100 inclusive)
         * @return a new {@code PercentComplete} instance
         * @throws IllegalArgumentException if the value is not between 0 and 100
         */
        public PercentComplete build(int value) {
            return new PercentComplete(value);
        }
    }

    /**
     * Returns the formatted string representation of this percent complete property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code PERCENT-COMPLETE} property string
     */
    @Override
    public String formatted() {
        return "PERCENT-COMPLETE:" + value;
    }
}
