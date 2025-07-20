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
 * Represents the iCalendar SEQUENCE property, which defines the revision sequence 
 * number for a calendar component.
 * <p>
 * The SEQUENCE property is used to indicate the revision number of a calendar
 * component within a sequence of revisions. When a component is updated, the
 * sequence number should be incremented to indicate that it's a newer version.
 * <p>
 * Key characteristics of the sequence number:
 * <ul>
 *   <li>Must be a non-negative integer (0 or greater)</li>
 *   <li>Starts at 0 for the initial version of a component</li>
 *   <li>Incremented with each significant revision</li>
 *   <li>Used by calendar applications to determine the most recent version</li>
 * </ul>
 * <p>
 * Usage scenarios:
 * <ul>
 *   <li>Updating event details (time, location, description)</li>
 *   <li>Modifying to-do item status or due date</li>
 *   <li>Synchronizing calendar data between different systems</li>
 *   <li>Resolving conflicts when multiple updates occur</li>
 * </ul>
 * <p>
 * The sequence number is particularly important for:
 * <ul>
 *   <li>Calendar synchronization protocols</li>
 *   <li>Conflict resolution in collaborative calendaring</li>
 *   <li>Determining which version of a component to use</li>
 *   <li>Tracking the history of component changes</li>
 * </ul>
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SequenceNumber implements ComponentProperty {

    /**
     * The sequence number value.
     * This must be a non-negative integer representing the revision number.
     */
    private final int value;

    /**
     * Constructs a new SequenceNumber instance with the specified value.
     *
     * @param value the sequence number value (must be non-negative)
     * @throws IllegalArgumentException if the value is negative
     */
    private SequenceNumber(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Sequence Number is a unsigned integer number.");
        }

        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a SequenceNumber.
     *
     * @return a new SequenceNumberBuilder instance
     */
    public static SequenceNumberBuilder builder() {
        return new SequenceNumberBuilder();
    }

    /**
     * Builder class for constructing SequenceNumber instances.
     * <p>
     * This builder provides a simple factory method for creating SequenceNumber
     * instances with validation.
     */
    public static class SequenceNumberBuilder {
        /**
         * Private constructor to enforce use of the factory method.
         */
        private SequenceNumberBuilder() {
        }

        /**
         * Creates a new SequenceNumber instance with the specified value.
         *
         * @param value the sequence number value (must be non-negative)
         * @return a new SequenceNumber instance
         * @throws IllegalArgumentException if the value is negative
         */
        public SequenceNumber build(int value) {
            return new SequenceNumber(value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this sequence number property.
     * <p>
     * The format follows the iCalendar specification: SEQUENCE:value
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "SEQUENCE:" + value;
    }
}
