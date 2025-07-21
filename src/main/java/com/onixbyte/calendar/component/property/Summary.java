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
 * Represents the iCalendar {@code SUMMARY} property, which provides a short, one-line summary or
 * subject for a calendar component.
 * <p>
 * This property defines a brief description or title of the calendar component. It is commonly used
 * in events, to-do items, and journal entries to provide a concise overview that can be displayed
 * in calendar views or summaries.
 * <p>
 * The {@code SUMMARY} property supports optional parameters for language specification and
 * alternate text representation, making it suitable for internationalised calendar applications.
 * <p>
 * Unlike the {@code DESCRIPTION} property, which can contain multiple lines of detailed text, the
 * {@code SUMMARY} should be a single line of text that captures the essence of the
 * calendar component.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Summary implements ComponentProperty {

    /**
     * Optional parameter specifying an alternate text representation for the summary. This can be
     * used to reference external resources containing the summary text.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * Optional parameter specifying the language of the summary text. This helps with
     * internationalisation and localisation of calendar data.
     */
    private final Language language;

    /**
     * The actual summary text value. This contains the brief title or subject of the
     * calendar component.
     */
    private final String value;

    /**
     * Constructs a new Summary instance with the specified parameters.
     *
     * @param alternateTextRepresentation optional alternate text representation parameter
     * @param language                    optional language parameter
     * @param value                       the summary text value
     */
    private Summary(
            AlternateTextRepresentation alternateTextRepresentation,
            Language language,
            String value
    ) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a {@code Summary}.
     *
     * @return a new {@code SummaryBuilder} instance
     */
    public static SummaryBuilder builder() {
        return new SummaryBuilder();
    }

    /**
     * Builder class for constructing {@code Summary} instances.
     * <p>
     * This builder follows the builder pattern and allows for the optional configuration
     * of alternate text representation and language parameters before creating the
     * final Summary instance.
     */
    public static class SummaryBuilder {
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
        private SummaryBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for the summary.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public SummaryBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for the summary.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public SummaryBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Creates a new {@code Summary} instance with the specified value and
         * configured parameters.
         *
         * @param value the summary text value
         * @return a new {@code Summary} instance
         */
        public Summary build(String value) {
            return new Summary(alternateTextRepresentation, language, value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this summary property.
     * <p>
     * The format follows the iCalendar specification: {@code SUMMARY[;parameters]:value} where
     * parameters may include language and alternate text representation if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("SUMMARY");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(alternateTextRepresentation);
        paramAppender.append(language);
        builder.append(":").append(value);
        return builder.toString();
    }
}
