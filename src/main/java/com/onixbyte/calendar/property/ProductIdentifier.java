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
 * Represents the iCalendar {@code PRODID} property, which specifies the identifier for the product
 * that created the iCalendar object.
 * <p>
 * The {@code PRODID} property is required and must appear exactly once in an iCalendar object. It
 * identifies the software product that created the calendar data, which is useful for:
 * <ul>
 *   <li>Debugging and troubleshooting calendar issues</li>
 *   <li>Understanding the source of calendar data</li>
 *   <li>Providing software compatibility information</li>
 *   <li>Supporting product-specific features or extensions</li>
 * </ul>
 * <p>
 * The product identifier should be a globally unique identifier that includes the vendor name and
 * product name. Common formats include:
 * <ul>
 *   <li>-//Company Name//Product Name//EN</li>
 *   <li>-//Company Name//Product Name Version//EN</li>
 *   <li>Company Name Product Name</li>
 * </ul>
 * <p>
 * Examples of product identifiers:
 * <ul>
 *   <li>-//Microsoft Corporation//Outlook 16.0 MIMEDIR//EN</li>
 *   <li>-//Google Inc//Google Calendar 70.9054//EN</li>
 *   <li>-//Apple Inc.//Mac OS X 10.15.7//EN</li>
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
public final class ProductIdentifier implements CalendarProperty {

    /**
     * The product identifier value. This should be a globally unique identifier for the product
     * that created the iCalendar object.
     */
    private final String value;

    /**
     * Constructs a new {@code ProductIdentifier} instance with the specified value.
     *
     * @param value the product identifier value
     */
    private ProductIdentifier(String value) {
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a {@code ProductIdentifier}.
     *
     * @return a new {@code ProductIdentifierBuilder} instance
     */
    public static ProductIdentifierBuilder builder() {
        return new ProductIdentifierBuilder();
    }

    /**
     * Builder class for constructing {@code ProductIdentifier} instances.
     * <p>
     * This builder provides a simple factory method for creating {@code ProductIdentifier}
     * instances with the specified identifier value.
     */
    public static class ProductIdentifierBuilder {

        /**
         * Private constructor to enforce use of the factory method.
         */
        private ProductIdentifierBuilder() {
        }

        /**
         * Creates a new ProductIdentifier instance with the specified value.
         *
         * @param value the product identifier value
         * @return a new ProductIdentifier instance
         */
        public ProductIdentifier build(String value) {
            return new ProductIdentifier(value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this product identifier property.
     * <p>
     * The format follows the iCalendar specification: {@code PRODID:value}
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "PRODID:" + value;
    }
}
