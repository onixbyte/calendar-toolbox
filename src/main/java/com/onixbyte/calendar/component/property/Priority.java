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

package com.onixbyte.calendar.component.property;

/**
 * Represents the iCalendar {@code PRIORITY} property, which defines the relative priority for a
 * calendar component.
 * <p>
 * The priority is represented as an integer value ranging from 0 to 9, where:
 * <ul>
 *   <li>0 = Undefined priority</li>
 *   <li>1 = Highest priority</li>
 *   <li>2-4 = High priority</li>
 *   <li>5 = Medium priority</li>
 *   <li>6-8 = Low priority</li>
 *   <li>9 = Lowest priority</li>
 * </ul>
 * <p>
 * This property is commonly used in to-do items and events to indicate their relative importance
 * compared to other calendar components.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Priority implements ComponentProperty {

    /**
     * The priority value, ranging from 0 to 9. Lower values indicate higher priority, with 1 being
     * the highest priority and 9 being the lowest.
     */
    private final int value;

    /**
     * Constructs a new Priority instance with the specified value.
     * <p>
     * The value must be between 0 and 9 inclusive.
     *
     * @param value the priority value (0-9)
     * @throws IllegalArgumentException if the value is not between 0 and 9
     */
    private Priority(int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Property PRIORITY should between 0 and 9.");
        }

        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a Priority.
     *
     * @return a new PriorityBuilder instance
     */
    public static PriorityBuilder builder() {
        return new PriorityBuilder();
    }

    /**
     * Builder class for constructing Priority instances.
     * <p>
     * This builder provides a simple factory method for creating Priority instances
     * with validation.
     */
    public static class PriorityBuilder {

        /**
         * Private constructor to enforce use of the factory method.
         */
        private PriorityBuilder() {
        }

        /**
         * Creates a new Priority instance with the specified value.
         *
         * @param value the priority value (0-9)
         * @return a new Priority instance
         * @throws IllegalArgumentException if the value is not between 0 and 9
         */
        public Priority build(int value) {
            return new Priority(value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this priority property.
     * <p>
     * The format follows the iCalendar specification: PRIORITY:value
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "PRIORITY:" + value;
    }
}
