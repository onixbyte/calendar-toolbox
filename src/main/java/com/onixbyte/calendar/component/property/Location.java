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

import com.onixbyte.calendar.parameter.AlternateTextRepresentation;
import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.PropertyComposer;

/**
 * Represents the iCalendar {@code LOCATION} property, which defines the intended venue for a
 * calendar component.
 * <p>
 * This property specifies the physical location where an event is taking place, or where a to-do
 * item should be completed. The location can be a simple text description like "Conference Room A"
 * or a more detailed address.
 * <p>
 * The {@code LOCATION} property supports optional parameters for language specification and
 * alternate text representation, making it suitable for internationalised calendar applications.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Location implements ComponentProperty {

    /**
     * Optional parameter specifying an alternate text representation for the location. This can be
     * used to reference external resources containing the location information.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * Optional parameter specifying the language of the location text. This helps with
     * internationalisation and localisation of calendar data.
     */
    private final Language language;

    /**
     * The actual location text value. This contains the venue or location description for the
     * calendar component.
     */
    private final String value;

    /**
     * Constructs a new Location instance with the specified parameters.
     *
     * @param alternateTextRepresentation optional alternate text representation parameter
     * @param language                    optional language parameter
     * @param value                       the location text value
     */
    private Location(
            AlternateTextRepresentation alternateTextRepresentation,
            Language language,
            String value
    ) {
        this.value = value;
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
    }

    /**
     * Creates a new builder instance for constructing a Location.
     *
     * @return a new LocationBuilder instance
     */
    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    /**
     * Builder class for constructing Location instances.
     * <p>
     * This builder follows the builder pattern and allows for the optional configuration of
     * alternate text representation and language parameters before creating the final
     * {@code Location} instance.
     */
    public static class LocationBuilder {
        /**
         * Optional alternate text representation parameter.
         */
        private AlternateTextRepresentation alternateTextRepresentation;

        /**
         * Optional language parameter.
         */
        private Language language;

        /**
         * Private constructor to enforce use of the factory method.
         */
        private LocationBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for the location.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public LocationBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for the location.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public LocationBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Creates a new Location instance with the specified value and configured parameters.
         *
         * @param value the location text value
         * @return a new Location instance
         */
        public Location build(String value) {
            return new Location(alternateTextRepresentation, language, value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this location property.
     * <p>
     * The format follows the iCalendar specification: {@code LOCATION[;parameters]:value} where
     * parameters may include language and alternate text representation if specified.
     *
     * @return the formatted iCalendar property string
     */
    public String formatted() {
        var composer = PropertyComposer.of("LOCATION")
                .append(alternateTextRepresentation)
                .append(language);
        return composer.end(value);
    }
}
