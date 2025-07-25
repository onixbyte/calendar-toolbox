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

import com.onixbyte.calendar.parameter.CommonName;
import com.onixbyte.calendar.util.PropertyComposer;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This property specifies the owner of a {@link PrimaryCalendar primary calendar}.
 *
 * @author siujamo
 */
public final class Owner implements CalendarProperty {

    /**
     * Common name parameter.
     */
    private final CommonName commonName;

    /**
     * The owner uri.
     */
    private final URI value;

    /**
     * Private constructor prevent from being instantiated from non-standard operations.
     *
     * @param commonName common name of the owner
     * @param value      owner uri
     */
    private Owner(CommonName commonName, URI value) {
        this.commonName = commonName;
        this.value = value;
    }

    /**
     * Get a builder instance of owner.
     *
     * @return the builder instance of a owner
     */
    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    /**
     * Builder of owner.
     */
    public static class OwnerBuilder {
        /**
         * Common name parameter.
         */
        private CommonName commonName;

        /**
         * Constructs a new builder instance.
         */
        private OwnerBuilder() {
        }

        /**
         * Sets the common name of the owner.
         *
         * @param commonName common name of the owner
         * @return the builder instance
         */
        public OwnerBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        /**
         * Build an owner with specified value.
         *
         * @param value the owner
         * @return the built owner
         */
        public Owner build(URI value) {
            return new Owner(commonName, value);
        }

        /**
         * Build an owner with specified value.
         *
         * @param value the owner
         * @return the built owner
         * @throws IllegalArgumentException if the given value does not match the URI syntax
         */
        public Owner build(String value) {
            try {
                var _value = new URI(value);
                return new Owner(commonName, _value);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Value syntax error.");
            }
        }
    }

    /**
     * Output this property in <code>ics</code> format.
     *
     * @return <code>ics</code>-formatted string
     */
    @Override
    public String formatted() {
        var propertyComposer = PropertyComposer.of("X-OWNER");
        propertyComposer.append(commonName);
        return propertyComposer.end(value);
    }
}
