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
 * Represents the iCalendar UID property, which specifies a unique identifier 
 * for a calendar component.
 * <p>
 * The UID property is used to uniquely identify a calendar component within
 * a calendar system. This identifier should be globally unique and persistent
 * across different calendar systems and updates to the component.
 * <p>
 * The UID is essential for:
 * <ul>
 *   <li>Synchronization between calendar systems</li>
 *   <li>Updating existing calendar components</li>
 *   <li>Canceling or deleting calendar components</li>
 *   <li>Relating components to each other</li>
 * </ul>
 * <p>
 * The identifier format is typically a combination of a timestamp, random data,
 * and a domain name to ensure global uniqueness (e.g., "20050101T120000Z@example.com").
 * <p>
 * This property is required for most calendar components including events,
 * to-do items, and journal entries.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class UniqueIdentifier implements ComponentProperty {

    /**
     * The unique identifier value.
     * This should be a globally unique string that identifies the calendar component.
     */
    private final String value;

    /**
     * Constructs a new UniqueIdentifier instance with the specified value.
     *
     * @param value the unique identifier value
     */
    private UniqueIdentifier(String value) {
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a UniqueIdentifier.
     *
     * @return a new UniqueIdentifierBuilder instance
     */
    public static UniqueIdentifierBuilder builder() {
        return new UniqueIdentifierBuilder();
    }

    /**
     * Builder class for constructing UniqueIdentifier instances.
     * <p>
     * This builder provides a simple factory method for creating UniqueIdentifier
     * instances with the specified identifier value.
     */
    public static class UniqueIdentifierBuilder {

        /**
         * Private constructor to enforce use of the factory method.
         */
        private UniqueIdentifierBuilder() {
        }

        /**
         * Creates a new UniqueIdentifier instance with the specified value.
         *
         * @param value the unique identifier value
         * @return a new UniqueIdentifier instance
         */
        public UniqueIdentifier build(String value) {
            return new UniqueIdentifier(value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this unique identifier property.
     * <p>
     * The format follows the iCalendar specification: UID:value
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "UID:" + value;
    }
}
