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
 * Represents the {@code REPEAT} property in an iCalendar component.
 * <p>
 * This property defines the number of times an alarm should be repeated after
 * its initial trigger. It is used in conjunction with the {@code DURATION}
 * property to specify how often the alarm should repeat.
 * <p>
 * A value of zero indicates that the alarm should not repeat beyond its
 * initial trigger. Positive values indicate the number of additional repetitions.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RepeatCount implements ComponentProperty {

    /**
     * The number of times the alarm should repeat.
     */
    private final int value;

    /**
     * Constructs a new {@code RepeatCount} instance with the specified repeat count.
     *
     * @param value the number of times the alarm should repeat
     */
    private RepeatCount(int value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code RepeatCount} instances.
     *
     * @return a new {@code RepeatCountBuilder}
     */
    public static RepeatCountBuilder builder() {
        return new RepeatCountBuilder();
    }

    /**
     * Builder class for creating {@code RepeatCount} instances.
     */
    public static class RepeatCountBuilder {
        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RepeatCountBuilder() {
        }

        /**
         * Builds a new {@code RepeatCount} instance with the specified repeat count value.
         *
         * @param value the number of times the alarm should repeat
         * @return a new {@code RepeatCount} instance
         */
        public RepeatCount build(int value) {
            return new RepeatCount(value);
        }
    }

    /**
     * Returns the formatted string representation of this repeat count property
     * for inclusion in an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code REPEAT} property string
     */
    @Override
    public String formatted() {
        return "REPEAT:" + value;
    }
}
