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
 * Represents the {@code URL} property in an iCalendar component.
 * <p>
 * This property defines a Uniform Resource Locator (URL) associated with the iCalendar object. It
 * provides a way to associate a location where additional information about the calendar component
 * can be found.
 * <p>
 * The property value is a URL string that points to a resource on the internet or a local network.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class UniformResourceLocator implements ComponentProperty {

    /**
     * The URL string value.
     */
    private final String value;

    /**
     * Constructs a new {@code UniformResourceLocator} instance with the specified URL.
     *
     * @param value the URL string
     */
    private UniformResourceLocator(String value) {
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code UniformResourceLocator} instances.
     *
     * @return a new {@code UniformResourceLocatorBuilder}
     */
    public static UniformResourceLocatorBuilder builder() {
        return new UniformResourceLocatorBuilder();
    }

    /**
     * Builder class for creating {@code UniformResourceLocator} instances.
     */
    public static class UniformResourceLocatorBuilder {

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private UniformResourceLocatorBuilder() {
        }

        /**
         * Builds a new {@code UniformResourceLocator} instance with the specified URL value.
         *
         * @param value the URL string
         * @return a new {@code UniformResourceLocator} instance
         */
        public UniformResourceLocator build(String value) {
            return new UniformResourceLocator(value);
        }
    }

    /**
     * Returns the formatted string representation of this URL property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications.
     *
     * @return the formatted {@code URL} property string
     */
    @Override
    public String formatted() {
        return "URL:" + value;
    }
}
