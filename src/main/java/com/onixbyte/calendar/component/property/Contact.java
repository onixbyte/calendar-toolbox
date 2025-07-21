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
import com.onixbyte.calendar.util.ParamAppender;

/**
 * Represents the {@code CONTACT} property in an iCalendar component.
 * <p>
 * This property is used to specify contact information for the calendar component. It provides a
 * way to include contact details for someone associated with the calendar event, such as an
 * organiser or other relevant person.
 * <p>
 * The property supports optional parameters for alternate text representation and language
 * specification to enhance accessibility and internationalisation.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Contact implements ComponentProperty {

    /**
     * The optional alternate text representation parameter for this contact information.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * The optional language parameter for this contact information.
     */
    private final Language language;

    /**
     * The contact information text value.
     */
    private final String value;

    /**
     * Constructs a new {@code Contact} instance with the specified parameters.
     *
     * @param alternateTextRepresentation the optional alternate text representation parameter
     * @param language                    the optional language parameter
     * @param value                       the contact information text value
     */
    private Contact(
            AlternateTextRepresentation alternateTextRepresentation,
            Language language,
            String value
    ) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code Contact} instances.
     *
     * @return a new {@code ContactBuilder}
     */
    public static ContactBuilder builder() {
        return new ContactBuilder();
    }

    /**
     * Builder class for creating {@code Contact} instances with optional parameters.
     */
    public static class ContactBuilder {
        /**
         * The optional alternate text representation parameter.
         */
        private AlternateTextRepresentation alternateTextRepresentation;

        /**
         * The optional language parameter.
         */
        private Language language;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private ContactBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for this contact information.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public ContactBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for this contact information.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public ContactBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Builds a new {@code Contact} instance with the specified contact information value.
         *
         * @param value the contact information text value
         * @return a new {@code Contact} instance
         */
        public Contact build(String value) {
            return new Contact(alternateTextRepresentation, language, value);
        }
    }

    /**
     * Returns the formatted string representation of this contact property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code CONTACT} property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("CONTACT");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(alternateTextRepresentation);
        paramAppender.append(language);

        builder.append(":").append(value);
        return builder.toString();
    }

}
