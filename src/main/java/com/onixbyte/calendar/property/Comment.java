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

import com.onixbyte.calendar.parameter.AlternateTextRepresentation;
import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.ParamAppender;

/**
 * Represents the iCalendar COMMENT property, which specifies non-processing 
 * information intended to provide additional context or notes about a calendar component.
 * <p>
 * The COMMENT property provides a way to add explanatory or supplementary information
 * to calendar components without affecting their processing or behaviour. Comments
 * are typically used for:
 * <ul>
 *   <li>Additional notes about an event or task</li>
 *   <li>Instructions for attendees</li>
 *   <li>Internal documentation</li>
 *   <li>Metadata that doesn't fit other properties</li>
 * </ul>
 * <p>
 * Unlike DESCRIPTION, which provides the main descriptive text, COMMENT is intended
 * for auxiliary information that may not be displayed prominently to users but
 * provides useful context.
 * <p>
 * The COMMENT property supports optional parameters:
 * <ul>
 *   <li>LANGUAGE - specifies the language of the comment text</li>
 *   <li>ALTREP - provides an alternate text representation</li>
 * </ul>
 * <p>
 * Multiple COMMENT properties can be specified for a single component to provide
 * different types of commentary or comments in different languages.
 * <p>
 * Examples of comment usage:
 * <ul>
 *   <li>"Please bring your laptop for the presentation"</li>
 *   <li>"This is a recurring meeting, but may be cancelled during holidays"</li>
 *   <li>"Created automatically by the booking system"</li>
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
public final class Comment implements ComponentProperty {

    /**
     * Optional parameter specifying an alternate text representation for the comment.
     * This can be used to reference external resources containing the comment text.
     */
    private final AlternateTextRepresentation alternateTextRepresentation;

    /**
     * Optional parameter specifying the language of the comment text.
     * This helps with internationalisation and localisation of calendar data.
     */
    private final Language language;

    /**
     * The actual comment text value.
     * This contains the non-processing information about the calendar component.
     */
    private final String value;

    /**
     * Constructs a new Comment instance with the specified parameters.
     *
     * @param alternateTextRepresentation optional alternate text representation parameter
     * @param language optional language parameter
     * @param value the comment text value
     */
    private Comment(AlternateTextRepresentation alternateTextRepresentation, Language language, String value) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new builder instance for constructing a Comment.
     *
     * @return a new CommentBuilder instance
     */
    public static CommentBuilder builder() {
        return new CommentBuilder();
    }

    /**
     * Builder class for constructing Comment instances.
     * <p>
     * This builder follows the builder pattern and allows for the optional configuration
     * of alternate text representation and language parameters before creating the
     * final Comment instance.
     */
    public static class CommentBuilder {
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
        private CommentBuilder() {
        }

        /**
         * Sets the alternate text representation parameter for the comment.
         *
         * @param alternateTextRepresentation the alternate text representation parameter
         * @return this builder instance for method chaining
         */
        public CommentBuilder withAlternateTextRepresentation(
                AlternateTextRepresentation alternateTextRepresentation
        ) {
            this.alternateTextRepresentation = alternateTextRepresentation;
            return this;
        }

        /**
         * Sets the language parameter for the comment.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public CommentBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Creates a new Comment instance with the specified value and configured parameters.
         *
         * @param value the comment text value
         * @return a new Comment instance
         */
        public Comment build(String value) {
            return new Comment(alternateTextRepresentation, language, value);
        }
    }

    /**
     * Returns the formatted iCalendar representation of this comment property.
     * <p>
     * The format follows the iCalendar specification: COMMENT[;parameters]:value
     * where parameters may include language and alternate text representation if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("COMMENT");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(alternateTextRepresentation);
        paramAppender.append(language);

        builder.append(":").append(value);
        return builder.toString();
    }
}