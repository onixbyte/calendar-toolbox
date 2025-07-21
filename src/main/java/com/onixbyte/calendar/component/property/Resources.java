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

import java.util.List;

/**
 * Represents the {@code RESOURCES} property in an iCalendar component.
 * <p>
 * This property defines the equipment or resources anticipated for an activity specified by a
 * calendar component. It can contain multiple resource values separated by commas.
 * <p>
 * The property supports optional parameters for alternate text representation and language
 * specification to enhance accessibility and internationalisation.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Resources implements ComponentProperty {

    /**
     * The optional alternate text representation parameter for this resources list.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * The optional language parameter for this resources list.
     */
    private final Language language;

    /**
     * The list of resource names.
     */
    private final List<String> values;

    /**
     * Constructs a new {@code Resources} instance with the specified parameters.
     *
     * @param alternateTextRepresentation the optional alternate text representation parameter
     * @param language                    the optional language parameter
     * @param values                      the list of resource names
     */
    private Resources(
            AlternateTextRepresentation alternateTextRepresentation,
            Language language,
            List<String> values
    ) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.values = values;
    }

    /**
     * Creates a new builder for constructing {@code Resources} instances.
     *
     * @return a new {@code ResourcesBuilder}
     */
    public static ResourcesBuilder builder() {
        return new ResourcesBuilder();
    }

    /**
     * Builder class for creating {@code Resources} instances with optional parameters.
     */
    public static class ResourcesBuilder {
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
        private ResourcesBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for this resources list.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public ResourcesBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for this resources list.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public ResourcesBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Builds a new {@code Resources} instance with the specified resource names.
         *
         * @param values the resource names as varargs
         * @return a new {@code Resources} instance
         */
        public Resources build(String... values) {
            return new Resources(alternateTextRepresentation, language, List.of(values));
        }
    }

    /**
     * Returns the formatted string representation of this resources property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters. Multiple
     * resource names are joined with commas.
     *
     * @return the formatted {@code RESOURCES} property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("RESOURCES");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(alternateTextRepresentation);
        paramAppender.append(language);

        builder.append(":").append(String.join(",", values));
        return builder.toString();
    }
}
