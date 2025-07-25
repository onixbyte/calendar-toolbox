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
 * Represents the iCalendar DESCRIPTION property, which provides a detailed description of a
 * calendar component.
 * <p>
 * This property defines a more complete description of the calendar component than that provided by
 * the {@code SUMMARY} property. The description can include multiple lines of text and supports
 * optional parameters for language and alternate text representation.
 * <p>
 * The {@code DESCRIPTION} property is commonly used in events, to-do items, and journal entries
 * to provide comprehensive details about the calendar component.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Description implements ComponentProperty {

    /**
     * Optional parameter specifying an alternate text representation for the description. This can
     * be used to reference external resources containing the description text.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * Optional parameter specifying the language of the description text. This helps with
     * internationalisation and localisation of calendar data.
     */
    private final Language language;

    /**
     * The actual description text value. This contains the detailed description of the
     * calendar component.
     */
    private final String value;

    /**
     * Constructs a new Description instance with the specified parameters.
     *
     * @param alternateTextRepresentation optional alternate text representation parameter
     * @param language                    optional language parameter
     * @param value                       the description text value
     */
    private Description(
            AlternateTextRepresentation alternateTextRepresentation,
            Language language,
            String value
    ) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a {@code Description}.
     *
     * @return a new {@code DescriptionBuilder} instance
     */
    public static DescriptionBuilder builder() {
        return new DescriptionBuilder();
    }

    /**
     * Builder class for constructing {@code Description} instances.
     * <p>
     * This builder follows the builder pattern and allows for the optional configuration of
     * alternate text representation and language parameters before creating the final
     * {@code Description} instance.
     */
    public static class DescriptionBuilder {
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
        private DescriptionBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for the description.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public DescriptionBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for the description.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public DescriptionBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Creates a new Description instance with the specified value and configured parameters.
         *
         * @param value the description text value
         * @return a new Description instance
         */
        public Description build(String value) {
            return new Description(alternateTextRepresentation, language, value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this description property.
     * <p>
     * The format follows the iCalendar specification: DESCRIPTION[;parameters]:value
     * where parameters may include language and alternate text representation if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var composer = PropertyComposer.of("DESCRIPTION")
                .append(alternateTextRepresentation)
                .append(language);
        return composer.end(value);
    }
}